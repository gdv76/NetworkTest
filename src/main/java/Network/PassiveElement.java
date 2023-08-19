package Network;

import java.util.Collection;

public class PassiveElement implements PathElement{
    @Override
    public Double getTimeDelay() {
        return null;
    }

    @Override
    public Double getCost() {
        return null;
    }

    @Override
    public Collection<PathElement> getConnections() {
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public Integer getID() {
        return null;
    }
}
