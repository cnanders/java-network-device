import java.net.*;

public class TestNetworkDevice {
  public static void main(String[] args) {
    TestNetworkDevice.testGoogle();
    TestNetworkDevice.testApple();
    TestNetworkDevice.testAlsCns();
  }

  private static void testGoogle()
  {
    NetworkDevice networkDevice = new NetworkDevice("google.com");
    TestNetworkDevice.printIsReachable(networkDevice);
  }

  private static void testAlsCns()
  {
    NetworkDevice networkDevice = new NetworkDevice("cns.als.lbl.gov", 8888);
    TestNetworkDevice.printIsReachable(networkDevice);
  }

  private static void testApple()
  {
    NetworkDevice networkDevice = new NetworkDevice("apple.com");
    TestNetworkDevice.printIsReachable(networkDevice);
  }

  private static void printIsReachable(NetworkDevice networkDevice)
  {
    try 
    {
      if (networkDevice.isReachable()) 
      {
        System.out.println(String.format("%s (port %s) is reachable", 
          networkDevice.getHost(),
          networkDevice.getPort()));
      } 
      else 
      {
        System.out.println(String.format("%s (port %s) is not reachable", 
        networkDevice.getHost(),
        networkDevice.getPort()));
      }
    } catch (UnknownHostException ex) {
      System.out.println("there was an error");
    }
  }
}