import java.awt.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;

public class Player implements Serializable{

	private int x, y, size, playerID, rotation;
	private int team;
	private int health;
	private SocketAddress address;
	private Rectangle playerhitbox;
	private boolean reloading;
	private boolean isMe;
	private int money;
	private boolean dead;
	
	public Player(int x, int y, int size, int team, SocketAddress address, boolean reloading, boolean isMe, int money, boolean dead) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.team = team;
		this.address = address;
		this.health = 15;
		this.rotation = 0;
		this.reloading = reloading;
		this.isMe = isMe;
		this.money = money;
		this.dead = dead;
		playerhitbox = new Rectangle(getX(),getY(),size,size);
		
	}
	
	public Player (Player player) {
		this.x = player.getX();
		this.y = player.getY();
		this.size = player.getSize();
		this.playerID = player.getPlayerID();
		this.team = 0;
		this.playerhitbox = new Rectangle(getX(),getY(),size,size);
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public int getSize() {
		return size;
	}
	
	public void setX(int xPos) {
		x = xPos;
	}

	public void setY(int yPos) {
		y = yPos;
	}

	public int getPlayerID() {
		return playerID;
	}
	
	public Rectangle getPlayerHitbox() {
		return playerhitbox;
	}
	
	public void setPlayerHitbox() {
		playerhitbox.setBounds(getX(), getY(), size, size);
		
	}
	public int getTeam() {
		return team;
	}
	public SocketAddress getAddress() {
		return address;
	}
	public Rectangle getPlayerhitbox() {
		return playerhitbox;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isReloading() {
		return reloading;
	}
	public void setReloading(boolean reloading) {
		this.reloading = reloading;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public boolean isMe() {
		return isMe;
	}

	public int getMoney() {
		return money;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	
	
	
	
	
	
	
	
}
