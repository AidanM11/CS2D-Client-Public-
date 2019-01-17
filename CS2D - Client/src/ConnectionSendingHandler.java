import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectionSendingHandler extends Thread{
	private String remoteHost;
	private int port;
	private boolean connected;
	private ObjectOutputStream objOut;
	private ByteArrayOutputStream baOut;
	private InputHandle input;
	private InetAddress inet;
	private DatagramSocket socket;
	
	public ConnectionSendingHandler(DatagramSocket socket, int port, InputHandle input) {
		super();
		this.remoteHost = remoteHost;
		this.socket = socket;
		this.port = port;
		this.connected = false;
		this.input = input;
	}
	
	public void run() {
		baOut = new ByteArrayOutputStream();
		
		try {
			inet = InetAddress.getByName(remoteHost);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		try {
			objOut = new ObjectOutputStream(baOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DatagramPacket packet;
		byte[] data;
		
		
		while(true) {
			try {
				baOut = new ByteArrayOutputStream();
				objOut = new ObjectOutputStream(baOut);
				objOut.writeObject(input.getKeys());
				data = null;
				data = baOut.toByteArray();
				packet = new DatagramPacket(data, data.length, inet, port);
				socket.send(packet);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
