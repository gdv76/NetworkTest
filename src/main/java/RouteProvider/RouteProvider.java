package RouteProvider;

import Network.Network;
import Network.PathElement;

import java.util.List;

public interface RouteProvider {
    public List<PathElement> getRoute(Integer firstID, Integer secondID, Network net) throws RouteNotFoundException;
    public String getDescription();
}
