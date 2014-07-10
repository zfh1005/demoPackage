package functionDemo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZFH
 */
import java.util.Enumeration;
import java.net.*;

public class SystemInformationGetDemo {
    private static final int PCNAMELENGH = 12;  
    public static void InetAddressScoket(String[] args)
    {
        try
        {
               InetAddress HostName = InetAddress.getLocalHost();//電腦名稱
               String tempHostname = HostName.toString();
               if(tempHostname.length() >= PCNAMELENGH)
               {   
                    tempHostname = tempHostname.substring(0,PCNAMELENGH) ;                    
                }
                else
                {
                    int addLengh = PCNAMELENGH - tempHostname.length();
                    int tempLengh ;
                    for(tempLengh = addLengh; tempLengh > 0; tempLengh--) 
                    {
                        tempHostname = tempHostname.concat("-");
                    }
                }
                System.out.println("PC Name:" + tempHostname);
        }
        catch(UnknownHostException e)
        {
                System.out.println("Unable to Get PC name.");
        }
       try
        {
                Enumeration<NetworkInterface> interfaceList =NetworkInterface.getNetworkInterfaces();
                if(interfaceList == null){
                System.out.println("--No interface found--");            
        }
        else
        {
                while(interfaceList.hasMoreElements())
                {
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println("PCI Name:" + iface.getDisplayName());//網卡名稱                   
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    if(!addrList.hasMoreElements())
                    {
                        System.out.println("\t(No address for this interface)");                         
                    }
                    while(addrList.hasMoreElements())
                    {
                        InetAddress address = addrList.nextElement();
                        /*System.out.println("\tAddress "
                              + ((address instanceof Inet4Address ? "(IPv4)"
                              : (address instanceof Inet6Address ? "(IPv6)" : "(?)"))));                           
                        */
                        System.out.println("IP: " + address.getHostAddress());//IP地址
                    }
                }               
            }
        }
       catch(SocketException se)
       {
            System.out.println("Error getting network interface:" + se.getMessage()); 
       }
       for(String host : args)
       {
           try
           {
               InetAddress HostName = InetAddress.getLocalHost();//電腦名稱
               System.out.println(HostName + "\r\n");
               InetAddress[] addressList = InetAddress.getAllByName(host);                  
               for(InetAddress address : addressList)
               {
                   System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
               }               
           }
           catch(UnknownHostException e)
           {
               System.out.println("\t Unable to find address for ");
           }
       }
    }     
}