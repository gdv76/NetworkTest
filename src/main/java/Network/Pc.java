package Network;

import java.util.ArrayList;
import java.util.Collection;

public class Pc implements ActiveElement{
    private String description;

    private PathElement pathElement;

    private IPAddress ip;
    private Integer id;

    public Pc(String description, IPAddress ip) throws ErrorMustBePresentIPAddressElementException {
        this.description = description;
        this.id = hashCode();

        if (ip == null) {
            throw new ErrorMustBePresentIPAddressElementException();
        } else {
            this.ip = ip;
        }
    }

    public void connect(PathElement device) throws ErrorNoPossibilityConnectPathElementException {
        if (device != null) {
            if (pathElement == device) {
                return;
            }
            pathElement = device;
        } else {
            throw new ErrorNoPossibilityConnectPathElementException();
        }
    }

    @Override
    public Collection<IPAddress> getIP() {
        ArrayList<IPAddress> ipList = new ArrayList<>();
        ipList.add(ip);
        return ipList;
    }

    @Override
    public Double getTimeDelay() {
        return 0.0D;
    }

    @Override
    public Double getCost() {
        return 0.0D;
    }

    @Override
    public Collection<PathElement> getConnections() {
        ArrayList<PathElement> netPathElements = new ArrayList<PathElement>();
        netPathElements.add(pathElement);
        return netPathElements;
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
        return "Pc{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", pathElement=[" + "class='" + pathElement.getClass().getSimpleName() + '\'' + ", description='" + pathElement.getInfo() + '\'' +  ", id=" + pathElement.getID() +
                "], ip=" + ip +
                '}';
    }
}