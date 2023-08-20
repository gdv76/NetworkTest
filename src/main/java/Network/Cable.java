package Network;

import java.util.ArrayList;
import java.util.Collection;

public class Cable implements PassiveElement{
    private Double len;
    private PathElement b;
    private PathElement e;
    private Double timeDelay;

    private Integer id;

    private String description;

    public Cable(String description,Double timeDelay,Double cost, PathElement b, PathElement e) {
        this.len = cost;
        this.b = b;
        this.e = e;
        this.timeDelay = timeDelay;
        this.description = description;
        this.id = hashCode();
    }
    public Boolean isDublicatCabel(Cable newCable) {
        PathElement bNetElement = null;
        PathElement eNetElement = null;
        for(PathElement c: newCable.getConnections()) {
            if (bNetElement == null) {
                bNetElement = c;
            } else if (eNetElement == null) {
                eNetElement = c;
            }
        }
        return (this.b == bNetElement && this.e == eNetElement ) || (this.e == bNetElement && this.b == eNetElement);
    }

    @Override
    public Double getTimeDelay() {
        return this.timeDelay;
    }

    @Override
    public Double getCost() {
        return this.len;
    }

    @Override
    public Collection<PathElement> getConnections() {
        ArrayList<PathElement> connections = new ArrayList<>();
        connections.add(b);
        connections.add(e);
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
    public void connect(PathElement device) throws ErrorNoPossibilityConnectPathElementException {
        if (device == null) {
            throw new ErrorNoPossibilityConnectPathElementException();
        }
        if (b == null) {
            b = device;
        } else if (e == null) {
            e = device;
        }
    }

    @Override
    public String toString() {
        return "Cable{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", len=" + len +
                ", b=[" + b.toString() + "]" +
                ", e=[" + e.toString() + "]" +
                ", timeDelay=" + timeDelay +
                '}';
    }
}
