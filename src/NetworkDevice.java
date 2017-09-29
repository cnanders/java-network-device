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

  /**
   * Creates a NetworkDevice with specified host, default port (80), and default timeout (1000 ms) 
   * @param host 	a host name or IP address, e.g. "192.168.10.10"
   */
  public NetworkDevice(String host) 
  {
    this(host, NetworkDevice.DEFAULT_PORT, NetworkDevice.DEFAULT_TIMEOUT);
  }
  /**
   * Creates a NetworkDevice with specified host and port, and default timeout (1000 ms)
   * @param host 	a host name or IP address, e.g. "192.168.10.10"
   * @param port 	a port, e.g., 80
   */
  public NetworkDevice(String host, int port) 
  {
    this(host, port, NetworkDevice.DEFAULT_TIMEOUT);
  }

  /**
   * Creates a NetworkDevice with specified host, port, and timeout.
   * @param host 	host name or IP address, e.g. "192.168.10.10"
   * @param port 	port, e.g., 80
   * @param timeout timeout in ms
   */
  public NetworkDevice(String host, int port, int timeout) 
  {
    this.host = host;
    this.port = port;
    this.timeout = timeout;
  }

  /**
   * Returns a boolean if the device is reachable within the timeout.  Implementation
   * creates a java.net.Socket and tries to connect it to a java.net.InetSocketAddress 
   * @return Boolean
   * @throws UnknownHostException
   */
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


  /**
   * Returns the host of the NetworkDevice
   * @return host
   */
  public String getHost()
  {
    return this.host;
  }
  /**
   * Returns the port of the NetworkDevice
   * @return port 
   */
  public int getPort()
  {
    return this.port;
  }
  /**
   * Returns the timeout (ms) of the NetworkDevice
   * @return timeout (ms)
   */
  public int getTimeout()
  {
    return this.timeout;
  }
}