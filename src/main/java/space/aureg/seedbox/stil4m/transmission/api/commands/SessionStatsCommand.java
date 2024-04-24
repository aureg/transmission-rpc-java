package space.aureg.seedbox.stil4m.transmission.api.commands;

import space.aureg.seedbox.stil4m.transmission.api.domain.SessionStats;
import space.aureg.seedbox.stil4m.transmission.rpc.RpcCommand;

public class SessionStatsCommand extends RpcCommand<Void, SessionStats> {

    public SessionStatsCommand(Long tag) {
        super(tag);
    }

    @Override
    public String getMethod() {
        return "session-stats";
    }
}
