import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
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
		GameState recGameState;
		Map recMap;
		byte[] bytes;
		while(true) {
			bytes = new byte[9999999];
			packet = new DatagramPacket(bytes, bytes.length);
			try {
				System.out.println("waiting for packet");
				socket.receive(packet);
				System.out.println("packet received");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ByteArrayInputStream baIn = new ByteArrayInputStream(packet.getData());
			DataInputStream dataIn = new DataInputStream(baIn);
			int packetType = 0;
			try {
				packetType = dataIn.readInt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(packetType == 0) {
				recGameState = GameState.deserialize(packet.getData());
				Main.setGameState(recGameState);
			}
			else if(packetType == 1) {
				recMap = GameState.deserializeMap(packet.getData());
				Main.getGameState().setMap(recMap);
			}
		}
	}
}
