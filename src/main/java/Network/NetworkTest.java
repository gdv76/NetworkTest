package Network;

import RouteProvider.Provider;
import RouteProvider.RouteNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class NetworkTest {
    public static void main(String[] args) throws ErrorCreatePathElementException, IOException, ClassNotFoundException {
       Network network = new Network("СК");

       PathElement networkDevice1 = network.add(new Router("Zyxel Keenetic Giga3",1D,1D,new ArrayList<IPAddress>(Arrays.asList(new IPAddress("192.168.0.1"),new IPAddress("0.0.0.0")))));
       PathElement networkDevice2 = network.add(new Switch("Dlink 8p 1Gb",1D,1D,8,null));
       PathElement networkDevice3 = network.add(new Switch("Tlink 16p 1Gb",1D,1D,16,null));
       PathElement networkDevice4 = network.add(new Switch("3Com 8p 1Gb",1D,1D,8,null));
       PathElement networkDevice5 = network.add(new Switch("Switch 5p 1Gb",1D,1D,5,null));
       PathElement networkDevice6 = network.add(new Switch("Switch 5p 1Gb",1D,1D,5,null));

       PathElement networkDevice7 = network.add(new Pc("ColibriAdm1",new IPAddress("192.168.0.20")));
       PathElement networkDevice8 = network.add(new Pc("ColibriAdm2",new IPAddress("192.168.0.21")));
       PathElement networkDevice9 = network.add(new Pc("Сервер",new IPAddress("192.168.0.35")));
       PathElement networkDevice10 = network.add(new Pc("Сервер КТ",new IPAddress("192.168.0.11")));
       PathElement networkDevice11 = network.add(new Pc("Сервер 3D",new IPAddress("192.168.0.12")));
       PathElement networkDevice12 = network.add(new Pc("Рабочая станция КТ",new IPAddress("192.168.0.13")));
       PathElement networkDevice13 = network.add(new Pc("Lenovo-PC",new IPAddress("192.168.0.22")));
       PathElement networkDevice14 = network.add(new Pc("ColibriHirurg",new IPAddress("192.168.0.23")));
       PathElement networkDevice15 = network.add(new Pc("ColibriUser",new IPAddress("192.168.0.24")));
       PathElement networkDevice16 = network.add(new Pc("ColibriPC4",new IPAddress("192.168.0.25")));
       PathElement networkDevice17 = network.add(new Pc("Ноутбук Acer",new IPAddress("192.168.0.26")));

       PathElement networkDevice18 = network.add(new Pc("Test",new IPAddress("192.168.0.99")));

       try {
          network.addCable(new Cable("1",1D,1D,networkDevice1,networkDevice2));
          network.addCable(new Cable("2",1D,1D,networkDevice2,networkDevice17));
          network.addCable(new Cable("3",1D,1D,networkDevice2,networkDevice16));
          network.addCable(new Cable("4",1D,1D,networkDevice2,networkDevice3));

          network.addCable(new Cable("5",1D,1D,networkDevice3,networkDevice7));
          network.addCable(new Cable("6",1D,1D,networkDevice3,networkDevice8));
          network.addCable(new Cable("7",1D,1D,networkDevice3,networkDevice9));
          network.addCable(new Cable("8",1D,1D,networkDevice3,networkDevice4));
          network.addCable(new Cable("9",1D,1D,networkDevice3,networkDevice5));

          network.addCable(new Cable("10",1D,1D,networkDevice5,networkDevice15));
          network.addCable(new Cable("11",1D,1D,networkDevice4,networkDevice6));
          network.addCable(new Cable("12",1D,1D,networkDevice4,networkDevice10));
          network.addCable(new Cable("13",1D,1D,networkDevice4,networkDevice11));
          network.addCable(new Cable("14",1D,1D,networkDevice4,networkDevice12));

          network.addCable(new Cable("15",1D,1D,networkDevice6,networkDevice13));
          network.addCable(new Cable("16",1D,1D,networkDevice6,networkDevice14));

       } catch (Exception e) {
          System.out.println("Ошибка создания сети");
          e.printStackTrace();
          return;
       }
// Сериализация Network
       try{
          FileOutputStream writeData = new FileOutputStream("mynetwork.dat");
          ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

          writeStream.writeObject(network);
          writeStream.flush();
          writeStream.close();

       }catch (IOException e) {
          e.printStackTrace();
       }
// Десериализация - востановление объентов
       try{
          FileInputStream readData = new FileInputStream("mynetwork.dat");
          ObjectInputStream readStream = new ObjectInputStream(readData);

          Network network1 = (Network) readStream.readObject();
          readStream.close();

          System.out.println("Network1" + '\n');
          network1.netInfo();

       }catch (Exception e) {
          e.printStackTrace();
       }


// отражаем элементы сети через toString
//       System.out.println(network);



       Provider provider = new Provider();

       System.out.println("Маршрут (" + provider.getClass()  + ")" + '\n');
       LinkedList<PathElement> routeByNet = null;



       try {
          //   routeByNet = provider.findPathToNetworkDevice(network,networkDevice1,networkDevice17);
          routeByNet = (LinkedList<PathElement>) provider.getRoute(networkDevice1.getID(), networkDevice18.getID(), network);

          if (routeByNet == null) {
             System.out.println("Маршрут не найден. Устройство не подключено в сеть");
          } else {
             for(PathElement pe:routeByNet) {
                System.out.println(pe);
             }
          }

       } catch (RouteNotFoundException ex) {
          System.out.println("Маршрут не найден. Устройство не подключено в сеть [RouteNotFoundException]");
       }  catch (IllegalArgumentException ex) {
          System.out.println("Некорректные агрументы метода поиска маршрута");
       }


    }
}
