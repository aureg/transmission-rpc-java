package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.api.domain.RemoveTorrentInfo;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

public class RemoveTorentCommand extends RpcCommand<RemoveTorrentInfo, Object> {

    public RemoveTorentCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-remove";
    }
}
