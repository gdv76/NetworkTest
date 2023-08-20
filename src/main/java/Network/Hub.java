package Network;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;

public class Hub implements PassiveElement{
    private String description;
    private Integer cntPorts;
    private ArrayList<PathElement> connections;
    private Double timeDelay;
    private Double cost;

    private Integer id;

    public Hub(String description, Integer cntPorts, Double timeDelay, Double cost) throws ErrorCreatePathElementException{
        this.description = description;
        this.cntPorts = cntPorts;
        this.timeDelay = timeDelay;
        this.cost = cost;
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
    public Double getTimeDelay() {
        return this.timeDelay;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public Collection<PathElement> getConnections() {
        return connections;
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

        return "Hub{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", cntPorts=" + cntPorts +
                ", timeDelay=" + timeDelay +
                ", cost=" + cost +
                ", connections=[" + conDevices + "]"+
                '}';
    }
}
