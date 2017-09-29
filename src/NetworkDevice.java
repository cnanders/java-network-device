import java.io.*;
import java.net.*;

/**
 * Check if a network device (host / port) is reachable
 * @author Chris Anderson &lt;cnanderson@lbl.gov&gt;
 */
public final class NetworkDevice {

  private String host;
  private int port;
  private int timeout;// ms

  private static final int DEFAULT_TIMEOUT = 1000;
  private static final int DEFAULT_PORT = 80;

  // Constructor (default port, default timeout) 
  public NetworkDevice(String host) 
  {
    this(host, NetworkDevice.DEFAULT_PORT, NetworkDevice.DEFAULT_TIMEOUT);
  }
  // Constructor (default timeout) 
  public NetworkDevice(String host, int port) 
  {
    this(host, port, NetworkDevice.DEFAULT_TIMEOUT);
  }

  // Constructor
  public NetworkDevice(String host, int port, int timeout) 
  {
    this.host = host;
    this.port = port;
    this.timeout = timeout;
  }

  public boolean isReachable() 
  throws UnknownHostException 
  {
    InetAddress address = InetAddress.getByName(this.host);
    //System.out.println(address);

    // Use try with references to close socket if connect method
    // throws IOException
    try 
    (
        Socket s = new Socket()
    ) 
    {
      s.connect(new InetSocketAddress(address, this.port), this.timeout);
      // If it has not thrown IO exception by now, return true
      
      // First close the socket
      s.close();
      
      return true;
    } 
    catch (IOException ex) 
    {
      return false;
    }

  }


  public String getHost()
  {
    return this.host;
  }
  public int getPort()
  {
    return this.port;
  }
  public int getTimeout()
  {
    return this.timeout;
  }
}