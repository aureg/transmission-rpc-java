package nl.stil4m.transmission.api.domain.ids;

import java.util.List;

public class ShaListIds extends Ids {

    private final List<String> ids;

    public ShaListIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

    @Override
    public Object theObject() {
        return ids;
    }
}
