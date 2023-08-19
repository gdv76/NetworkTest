package Network;

import java.util.Collection;

public interface PathElement {
    public Double getTimeDelay();
    public Double getCost();
    public Collection<PathElement> getConnections();
    public String getInfo();
    public Integer getID();
}
