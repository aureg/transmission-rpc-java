package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

public class FreeSpaceCommand extends RpcCommand<FreeSpacePath, FreeSpaceResult> {

    public FreeSpaceCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "free-space";
    }

}
