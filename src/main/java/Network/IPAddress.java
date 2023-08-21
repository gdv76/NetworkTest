package Network;

import java.io.Serializable;

public class IPAddress implements Serializable {
    public String ip;

    public IPAddress(String ip) {
        this.ip = ip;
    }

    public IPAddress() {
        this.ip = "0.0.0.0";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "IPAddress{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
