import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ConnectionReceiveHandler extends Thread{
	private DatagramSocket socket;
	private ObjectInputStream objIn;
	private ByteArrayInputStream baIn;

	public ConnectionReceiveHandler(DatagramSocket socket) {
		super();
		this.socket = socket;
	}
	
	public void run() {
		DatagramPacket packet;
		GameState recGameState = Main.getGameState();
		byte[] bytes;
		while(true) {
			bytes = new byte[1024];
			packet = new DatagramPacket(bytes, bytes.length);
			try {
				socket.receive(packet);
				System.out.println("packet received");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			baIn = new ByteArrayInputStream(packet.getData());
			try {
				objIn = new ObjectInputStream(baIn);
				recGameState = (GameState) objIn.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.setGameState(recGameState);
		}
	}
}
