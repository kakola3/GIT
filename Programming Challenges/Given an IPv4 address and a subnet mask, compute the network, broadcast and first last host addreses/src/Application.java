import java.net.*;
import java.util.Enumeration;

public class Application
{
    public static void main(String[] args) {
        String ip;
        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if(iface.isLoopback() || !iface.isUp()){
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()){
                    InetAddress addr = addresses.nextElement();

                    // if you want filter kinds of addresses
                    //if(addr instanceof Inet6Address) continue;
                    //if(addr instanceof Inet4Address) continue;

                    ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                }
            }
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }
}
