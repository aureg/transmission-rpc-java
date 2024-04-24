package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.api.domain.TorrentAction;
import space.aureg.seedbox.stil4m.transmission.api.domain.ids.Ids;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

import java.util.HashMap;
import java.util.Map;

public class TorrentActionCommand extends RpcCommand<Ids, Object> {

    private final Map<TorrentAction, String> torrentActionMap = new HashMap<>();
    private final String method;

    {
        torrentActionMap.put(TorrentAction.START, "torrent-start");
        torrentActionMap.put(TorrentAction.START_NOW, "torrent-start-now");
        torrentActionMap.put(TorrentAction.STOP, "torrent-stop");
        torrentActionMap.put(TorrentAction.VERIFY, "torrent-verify");
        torrentActionMap.put(TorrentAction.REANNOUNCE, "torrent-reannounce");
    }

    public TorrentActionCommand(Long tag, TorrentAction action) {
        super(tag);
        method = torrentActionMap.get(action);
    }

    @Override
    public String getMethod() {
        return method;
    }
}
