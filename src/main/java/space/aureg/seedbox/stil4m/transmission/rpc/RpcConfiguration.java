package space.aureg.seedbox.stil4m.transmission.rpc;

import space.aureg.seedbox.stil4m.transmission.http.HostConfiguration;

import java.net.URI;
import java.util.Base64;

public class RpcConfiguration implements HostConfiguration {

    private URI host;
    private String authInfo = null;

    public RpcConfiguration(URI host) {
        this.host = host;
    }

    public RpcConfiguration(URI host, String username, String password) {
        this.host = host;
        setAuthInfo(username, password);
    }

    public RpcConfiguration() {
    }

    public URI getHost() {
        return host;
    }

    public void setHost(URI host) {
        this.host = host;
    }


    public void setAuthInfo(String username, String password) {
        this.authInfo = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }


    public String getEncodedAuthInfo() {
        return authInfo;
    }

    public boolean hasAuthInfo() {
        return authInfo != null;
    }
    //header Authorization: Basic %s:%s
}
