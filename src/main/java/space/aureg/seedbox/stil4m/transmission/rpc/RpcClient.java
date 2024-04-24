package space.aureg.seedbox.stil4m.transmission.rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import space.aureg.seedbox.stil4m.transmission.http.InvalidResponseStatus;
import space.aureg.seedbox.stil4m.transmission.http.RequestExecutor;
import space.aureg.seedbox.stil4m.transmission.http.RequestExecutorException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RpcClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);
    private static final Integer STATUS_OK = 200;

    private final RpcConfiguration configuration;
    private final ObjectMapper objectMapper;
    private final Map<String, String> headers;
    private final DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

    private final RequestExecutor requestExecutor;

    public RpcClient(RpcConfiguration configuration, ObjectMapper objectMapper) {
        this.requestExecutor = new RequestExecutor(objectMapper, configuration, defaultHttpClient);
        this.configuration = configuration;
        this.objectMapper = objectMapper;
        headers = new HashMap<>();
    }

    public <T, V> void execute(RpcCommand<T, V> command, Map<String, String> h) throws RpcException {
        try {
            executeCommandInner(command, h);
        } catch (RequestExecutorException | IOException e) {
            throw new RpcException(e);
        } catch (InvalidResponseStatus e) {
            LOGGER.trace("Failed execute command. Now setup and try again", e);
            setup();
            try {
                executeCommandInner(command, h);
            } catch (Exception | RequestExecutorException | InvalidResponseStatus inner) {
                throw new RpcException(inner);
            }
        }
    }

    private <T, V> void executeCommandInner(RpcCommand<T, V> command, Map<String, String> h) throws RequestExecutorException, InvalidResponseStatus, IOException, RpcException {
        requestExecutor.removeAllHeaders();
        for (Map.Entry<String, String> entry : h.entrySet()) {

            requestExecutor.configureHeader(entry.getKey(), entry.getValue());
        }

        RpcRequest<T> request = command.buildRequestPayload();
        RpcResponse<V> response = requestExecutor.execute(request, RpcResponse.class, STATUS_OK);

        Map args = (Map) response.getArguments();
        String stringValue = objectMapper.writeValueAsString(args);
        System.err.println(stringValue);
        response.setArguments((V) objectMapper.readValue(stringValue, command.getArgumentsObject()));
        if (!command.getTag().equals(response.getTag())) {
            throw new RpcException(String.format("Invalid response tag. Expected %d, but got %d", command.getTag(), request.getTag()));
        }
        command.setResponse(response);

        if (!"success".equals(response.getResult())) {
            throw new RpcException("Rpc Command Failed: " + response.getResult(), command);
        }
    }

    private void setup() throws RpcException {
        try {
            HttpPost httpPost = createPost();
            if (configuration.hasAuthInfo()) {
                String auth = "Basic " + configuration.getEncodedAuthInfo();
                httpPost.addHeader("Authorization", auth);
                headers.put("Authorization", auth);
            }
            HttpResponse result = defaultHttpClient.execute(httpPost);
            if (result.getStatusLine().getStatusCode() == 401) {
                throw new IOException("Connection needs authentication");

            }
            putSessionHeader(result);
            EntityUtils.consume(result.getEntity());
        } catch (IOException e) {
            throw new RpcException(e);
        }
    }

    protected HttpPost createPost() {
        return new HttpPost(configuration.getHost());
    }

    protected DefaultHttpClient getClient() {
        return defaultHttpClient;
    }

    public void executeWithHeaders(RpcCommand command) throws RpcException {
        execute(command, headers);
    }

    private void putSessionHeader(HttpResponse result) {
        headers.put("X-Transmission-Session-Id", result.getFirstHeader("X-Transmission-Session-Id").getValue());
    }
}
