package space.aureg.seedbox.stil4m.transmission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import space.aureg.seedbox.stil4m.transmission.api.TransmissionRpcClient;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcClient;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcConfiguration;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcException;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PortTestIntegrationTest extends IntegrationTest {

    private TransmissionRpcClient rpcClient;

    @Before
    public void before() throws RpcException, MalformedURLException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RpcConfiguration rpcConfiguration = new RpcConfiguration();
        rpcConfiguration.setHost(URI.create("http://localhost:9091/transmission/rpc"));
        RpcClient client = new RpcClient(rpcConfiguration, objectMapper);
        rpcClient = new TransmissionRpcClient(client);
        pause();
    }

    @Test
    public void testPort() throws RpcException {
        MatcherAssert.assertThat(rpcClient.testPort().getOpen(), is(true));
    }
}
