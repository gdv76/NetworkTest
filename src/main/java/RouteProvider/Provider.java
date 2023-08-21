package RouteProvider;

import Network.Network;
import Network.PathElement;

import java.util.*;

public class Provider implements RouteProvider{
    private String description;

    @Override
    public List<PathElement> getRoute(Integer firstID, Integer secondID, Network net) throws RouteNotFoundException,IllegalArgumentException {
        PathElement start, finish;
        LinkedList<PathElement>  res = null;

        start = net.getPathElements().get(firstID);
        finish = net.getPathElements().get(secondID);

        if ( start == null || finish == null) {
            throw new IllegalArgumentException();
        }

        if (start == finish) {
            res = new LinkedList<>();
            res.add(start);
        } else {
            res = findPathToNetworkDevice (net,start,finish);
        }

        if (res == null) {
            throw new RouteNotFoundException();
        }
        return res;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public LinkedList<PathElement> findPathToNetworkDevice (Network net, PathElement start, PathElement finish) {
        if ( start == null || finish == null) {
           return null;
        }
        PathElement v;

        HashMap<PathElement,PathElement> prev = new HashMap<>();
        LinkedList<PathElement> resList = new LinkedList<>();

        // карту используем для хранения информации об обойденных вершинах
        HashMap<PathElement,Boolean> vizited = new HashMap<>();
        Queue<PathElement> queue = new LinkedList<PathElement>();
        queue.add(start);
        vizited.put(start,true);
        prev.put(start,null);

        while (!queue.isEmpty()) {
            v = queue.remove();
            for(PathElement d:net.findListNearestNetworkDevice(v)) {
                if (!vizited.getOrDefault(d,false)) {
                    queue.add(d);
                    vizited.put(d,true);
                    prev.put(d,v);
                    if (d.equals(finish)) {
                        resList.add(d);
                        while(v != null) {
                            resList.add(v);
                            v = prev.get(v);
                        }
                        return resList;
                    }
                }
            }
        }
        return null;
    }
}
