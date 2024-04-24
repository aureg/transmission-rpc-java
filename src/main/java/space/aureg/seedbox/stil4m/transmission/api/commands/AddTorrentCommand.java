package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.api.domain.AddTorrentInfo;
import space.aureg.seedbox.stil4m.transmission.api.domain.AddedTorrentInfo;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

public class AddTorrentCommand extends RpcCommand<AddTorrentInfo, AddedTorrentInfo> {

    public AddTorrentCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "torrent-add";
    }
}
