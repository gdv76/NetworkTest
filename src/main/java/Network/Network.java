package Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Network implements Serializable {
    private String description;

    public LinkedHashMap<Integer,PathElement> net;

    public Network(String description){
        this.description = description;
        net = new LinkedHashMap<Integer,PathElement>();
    }

/*    Поиск ближайших элементу networkDevice сети узлов*/
public ArrayList<PathElement> findListNearestNetworkDevice(PathElement networkDevice){
        ArrayList<PathElement> foundList = new ArrayList<>();

        if (networkDevice != null && networkDevice.getConnections() != null) {

            for(PathElement pe:net.values()) {
                if (pe instanceof Cable) {
                    PathElement bNetElement = null;
                    PathElement eNetElement = null;

                    if (pe.getConnections() == null) {
                        return foundList;
                    }

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

/*    Добавляем элемент сети Cabel.
    Перед добавлением идет проверка на существование элемента в сети +
    идет заполнение списка connections в узлах сети */
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

    public void netInfo() {
  // Выводим узел и ближайшие к нему элементы
        for(PathElement p:net.values()) {
            if (!(p instanceof Cable)) {
                System.out.println(p.toString() + '\n');
                ArrayList<PathElement> nearest = findListNearestNetworkDevice(p);
                if (nearest != null) {
                    for(PathElement c: nearest) {
                        System.out.println("     - " + c.toString() + '\n');
                    }
                }
            }
        }
    }
}
