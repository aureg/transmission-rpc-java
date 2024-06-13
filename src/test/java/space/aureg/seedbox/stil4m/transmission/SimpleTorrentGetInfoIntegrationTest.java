package space.aureg.seedbox.stil4m.transmission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import space.aureg.seedbox.stil4m.transmission.api.TransmissionRpcClient;
import space.aureg.seedbox.stil4m.transmission.api.domain.Constants;
import space.aureg.seedbox.stil4m.transmission.api.domain.TorrentGetRequestInfo;
import space.aureg.seedbox.stil4m.transmission.api.domain.TorrentInfoCollection;
import space.aureg.seedbox.stil4m.transmission.api.domain.ids.OmittedIds;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcClient;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcConfiguration;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcException;

import java.net.MalformedURLException;
import java.net.URI;

public class SimpleTorrentGetInfoIntegrationTest extends IntegrationTest {

    private TransmissionRpcClient rpcClient;

    @Before
    public void before() throws RpcException, MalformedURLException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RpcConfiguration rpcConfiguration = new RpcConfiguration();
        rpcConfiguration.setHost(URI.create("http://127.0.0.1:9091/transmission/rpc"));
        rpcConfiguration.setAuthInfo("admin", "admin");
        RpcClient client = new RpcClient(rpcConfiguration, objectMapper);
        rpcClient = new TransmissionRpcClient(client);
        TorrentInfoCollection result = rpcClient.getAllTorrentsInfo();
    }

    @After
    public void after() throws RpcException, InterruptedException {
    }

    @Test
    public void testGetTorrentInfoForAllTorrents() throws RpcException {
        TorrentInfoCollection result = rpcClient.getTorrentInfo(new TorrentGetRequestInfo(new OmittedIds(), Constants.TORRENT_INFO_FIELDS));

        result.getTorrents().forEach(torrentInfo -> {
            System.err.println("Torrent info: " + torrentInfo.getName() + ", " + torrentInfo.getUploadRatio());
        });
    }

}

