package Route;

import Network.Network;
import Network.PathElement;
import RouteProvider.RouteProvider;
import RouteProvider.RouteNotFoundException;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

public class Route {
    public static void main(String[] args) {
        String netName;
        String providerName;

        Network network = null;

        if (args.length == 4) {
            netName = args[0].trim() + ".dat";
            providerName = args[1].trim();

            try{
                FileInputStream readData = new FileInputStream(netName);
                ObjectInputStream readStream = new ObjectInputStream(readData);

                network = (Network) readStream.readObject();
                readStream.close();

            }catch (Exception e) {
                System.out.println("Ошибка доступа к информации о сети ["+ netName + "]");
                e.printStackTrace();
            }
// 1818402158 1013423070,392292416 1500056228
            try {
                Class clazz = Class.forName(providerName);
                RouteProvider routeProvider = (RouteProvider)clazz.newInstance();

                Integer firstID = Integer.parseInt(args[2]);
                Integer secondID = Integer.parseInt(args[3]);

                List<PathElement> routeByNet = routeProvider.getRoute(firstID,secondID, network);

                for (PathElement pe : routeByNet) {
                    System.out.println(pe);
                }
            } catch (RouteNotFoundException ex) {
                System.out.println("Маршрут между узлами не найден");
            } catch (IllegalArgumentException ex) {
                System.out.println("Некорректные агрументы метода поиска маршрута");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Незаданы аргументы команды route");
            System.out.println("Формат команды:");
            System.out.println("route <network> <provider> <id1> <id2>");
        }
    }
}
