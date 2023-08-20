package Network;

import java.util.ArrayList;
import java.util.Collection;

public class Switch implements ActiveElement{
    private String description;
    private ArrayList<PathElement> connections;
    private Double timeDelay;
    private Double cost;

    private Integer cntPorts;

    private ArrayList<IPAddress> ipList;

    private Integer id;

//    Коммутаторы могут как иметь ip адрес так и нет, но количество портов всегда ограничено
    public Switch(String description, Double timeDelay, Double cost, Integer cntPorts, ArrayList<IPAddress> ipList) throws ErrorCreatePathElementException {
        this.description = description;
        this.timeDelay = timeDelay;
        this.cost = cost;
        this.ipList = ipList;
        this.id = hashCode();

        try {
            connections = new ArrayList<>(cntPorts);
        } catch (Exception ex) {
            throw new ErrorCreatePathElementException();
        }
    }

    public void connect(PathElement device) throws ErrorNoPossibilityConnectPathElementException {
        try {
            for(PathElement pe: connections) {
                if (pe == device) {
                    return; // сетевое устройство уже присутствует в списке
                }
            }
            connections.add(device);
        } catch (Exception ex) {
            throw new ErrorNoPossibilityConnectPathElementException();
        }
    }

    @Override
    public Collection<IPAddress> getIP() {
        return this.ipList;
    }

    @Override
    public Double getTimeDelay() {
        return this.timeDelay;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public Collection<PathElement> getConnections() {
        return this.connections;
    }

    @Override
    public String getInfo() {
        return this.description;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String toString() {
        String conDevices = "";
        for (PathElement pe:connections) {
            conDevices += "class='" + pe.getClass().getSimpleName() + '\'' + ", description='" + pe.getInfo() + '\'' +  ", id=" + pe.getID() + "; ";
        }

        return "Switch{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", timeDelay=" + timeDelay +
                ", cost=" + cost +
                ", cntPorts=" + cntPorts +
                ", ipList=" + ipList +
                ", connections=[" + conDevices + "]" +
                '}';
    }
}
