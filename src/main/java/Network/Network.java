package Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Network {
    private String description;

    public LinkedHashMap<Integer,PathElement> net;

    public Network(String description){
        this.description = description;
        net = new LinkedHashMap<Integer,PathElement>();
    }

    ArrayList<PathElement> findListNearestNetworkDevice(PathElement networkDevice){
        ArrayList<PathElement> foundList = new ArrayList<>();

        if (networkDevice != null) {
            for(PathElement pe:net.values()) {
                if (pe instanceof Cable) {
                    PathElement bNetElement = null;
                    PathElement eNetElement = null;
                    for(PathElement c: pe.getConnections()) {
                        if (bNetElement == null) {
                            bNetElement = c;
                        } else if (eNetElement == null) {
                            eNetElement = c;
                        }
                    }
                    if (bNetElement.equals(networkDevice)) {
                        foundList.add(eNetElement);
                    } else if (eNetElement.equals(networkDevice)) {
                        foundList.add(bNetElement);
                    }
                }
            }
        }

        return foundList;
    }

    public void addCable(Cable cable) throws ErrorNoPossibilityConnectPathElementException {
        Boolean findRes = false;
        for(PathElement netElement: net.values()) {
            if (netElement instanceof Cable) {
                findRes = findRes || ((Cable) netElement).isDublicatCabel(cable);
            }
        }
        if (!findRes) {
            net.put(cable.getID(), cable);

            for(PathElement netElement:cable.getConnections()) {
                netElement.connect(cable);
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PathElement add(PathElement pathElement) {
        net.put(pathElement.getID(), pathElement);
        return pathElement;
    }
    public HashMap<Integer,PathElement> getPathElements() {
        return this.net;
    }

    @Override
    public String toString() {
        String text = "Network{" + "description='" + description + '\'' + '\n';

        for(PathElement pe: net.values()) {
           text += pe.toString() + '\n';
        }

        return text + '}';
    }
}
