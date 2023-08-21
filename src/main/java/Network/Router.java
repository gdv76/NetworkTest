package Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Router implements ActiveElement,Firewall, Serializable {
    private String description;
    private ArrayList<PathElement> connections;
    private Double timeDelay;
    private Double cost;

    private Integer id;

    private ArrayList<IPAddress> ipAddressesAllowed;
    private ArrayList<IPAddress> ipAddressesNotAllowed;

    private ArrayList<IPAddress> ipList;

    public Router(String description, Double timeDelay, Double cost, ArrayList<IPAddress> ipList) throws ErrorMustBePresentIPAddressElementException {
        this.description = description;
        this.timeDelay = timeDelay;
        this.cost = cost;
        this.id = hashCode();
        this.ipAddressesAllowed = null;
        this.ipAddressesNotAllowed = null;

        if (ipList == null || ipList.size() == 0) {
            throw new ErrorMustBePresentIPAddressElementException();
        } else {
            this.ipList = ipList;
            connections = new ArrayList<>();
        }
    }

//    Задание таблиц для фильтрации маршрутов сети
    public void addEnabledRouteTable(ArrayList<IPAddress> ipTable) {
        ipAddressesAllowed = ipTable;
    }

    public void addDisabledRouteTable(ArrayList<IPAddress> ipTable) {
        ipAddressesNotAllowed = ipTable;
    }

    public void connect(PathElement device) {
        for(PathElement pe: connections) {
            if (pe == device) {
                return; // сетевое устройство уже присутствует в списке
            }
        }
        connections.add(device);
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
        String connectionsInfo = "";
        if (connections != null) {
            for (PathElement pe:connections) {
                connectionsInfo += "class='" + pe.getClass().getSimpleName() + '\'' + ", description='" + pe.getInfo() + '\'' +  ", id=" + pe.getID() + "; ";
            }
        } else {
            connectionsInfo = "not connections";
        }
        return "Router{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", timeDelay=" + timeDelay +
                ", cost=" + cost +
                ", ipList=" + ipList +
               ", connections=[" + connectionsInfo + "]" +
                '}';
    }

    // Проверка на допустипость получения пакетов с определенных адресов (Firewall)
    @Override
    public Boolean isConnectionAllowed(IPAddress ip) {
        Boolean checkResult = true;

        if (ipAddressesNotAllowed != null) {
           for (IPAddress ipAddress:ipAddressesNotAllowed) {
                if (ipAddress == ip) {
                    checkResult = false;
                    break;
                }
           }
        }
        if (ipAddressesAllowed != null) {
            for (IPAddress ipAddress:ipAddressesAllowed)  {
                if (ipAddress == ip) {
                   checkResult  = true;
                   break;
                }
            }
        }
        return checkResult;
    }
}
