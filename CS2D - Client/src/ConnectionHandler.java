import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnectionHandler extends Thread{
	private String remoteHost;
	private int port;
	private DatagramSocket socket;
	private ConnectionSendingHandler connSend;
	private ConnectionReceiveHandler connRec;
	private InputHandle input;
	public ConnectionHandler(String remoteHost, int port, InputHandle input) {
		super();
		this.remoteHost = remoteHost;
		this.port = port;
		this.input = input;
	}
	
	public void run() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connSend = new ConnectionSendingHandler(socket, port, input, this.remoteHost);
		connRec = new ConnectionReceiveHandler(socket);
		connSend.start();
		connRec.start();
		
	}
	
}
