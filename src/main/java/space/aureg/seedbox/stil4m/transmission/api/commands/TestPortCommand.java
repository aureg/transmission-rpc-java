package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.api.domain.PortCheckResult;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

public class TestPortCommand extends RpcCommand<Void, PortCheckResult> {

    public TestPortCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "port-test";
    }
}
