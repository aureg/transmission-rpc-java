package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.api.domain.SetTorrentInfo;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

public class SetTorrentCommand extends RpcCommand<SetTorrentInfo, Void> {

    public SetTorrentCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-set";
    }
}