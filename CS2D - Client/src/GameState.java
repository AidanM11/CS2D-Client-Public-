import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
public class GameState implements Serializable {

	private ArrayList<Player> players;
	private ArrayList<Bullet> bullets;
	private Map map;
	
	
	
	

	
	public GameState(Map map) {
		super();
		this.players = new ArrayList<Player>();
		this.bullets = new ArrayList<Bullet>();
		this.map = map;
		
		
	}
	public void update(HashMap<SocketAddress, boolean[]> keys) {
		
		for(int i = 0; i < this.players.size(); i++) {
			Player p = getPlayers().get(i);
			Player p1;
			boolean[] currKeys = keys.get(p.getAddress());
			int pY = getPlayers().get(i).getY();
			int pX = getPlayers().get(i).getX();
			if(currKeys[0] == true) {
				p1 = new Player(p);
				p1.setY(pY - 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY - 2);
				}
				
			
			}
			if(currKeys[1] == true) {
				p1 = new Player(p);
				p1.setX(pX - 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX - 2);
				}
				
			}
			if(currKeys[2] == true) {
				p1 = new Player(p);
				p1.setY(pY + 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY + 2);
				}
				
			}
			if(currKeys[3] == true) {
				p1 = new Player(p);
				p1.setX(pX + 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX + 2);
				}
			}
			
			if(currKeys[4] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 0, -12, 5, p));
			}
			else if(currKeys[5] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 0, 12, 5, p));
			}
			else if(currKeys[6] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), -12, 0, 5, p));
			}
			else if(currKeys[7] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 12, 0, 5, p));
			}		
			
			p.setPlayerHitbox();
		}
	
		
			
			
			
		for(int i = 0; i < this.bullets.size(); i++) {
			Bullet b = this.bullets.get(i);
			b.setX(b.getX() + b.getVelX());
			b.setY(b.getY() + b.getVelY());
			if(map.bulletCollides(b)) {
				this.bullets.remove(b);
			}
			for(int pInd = 0; pInd < players.size(); i++) {
				Player p = players.get(pInd);
				if(p.getPlayerHitbox().intersects(b.getBulletHitbox()) && b.getBulletID() != p) {
					this.bullets.remove(b);
					this.playerHit(p);
				}
			}
		}
		
		for( int i = 0; i < this.players.size(); i++) {
			Player p = players.get(i);
			if(p.getHealth() <= 0) {
				p.setX(200);
				p.setY(200);
				p.setHealth(10);
			}
		}
			
			
			
			
			
			
			
	}	
	
	public void playerHit(Player p) {
		p.setHealth(p.getHealth() - 1);
	}
		
	//LOTS OF WORK NEEDED. TEAMS, CONSISTENT SIZE, SPAWNPOINT
	public void createPlayer(SocketAddress add) {
		this.players.add(new Player(200,200, 30, 0, add, false, false, 0, false));
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public Map getMap() {
		return map;
	}
	
	public void setEqualTo(GameState gs) {
		this.players = gs.players;
		this.bullets = gs.bullets;
		this.map = gs.map;
		System.out.println(this.players.size());
	}
	
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public static byte[] serialize(GameState gamestate) {
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(baOut);
		try {
			dataOut.writeInt(gamestate.players.size());
			for(int i = 0; i < gamestate.players.size(); i++) {
				Player p = gamestate.players.get(i);
				dataOut.writeInt(p.getX());
				dataOut.writeInt(p.getY());
				dataOut.writeInt(p.getTeam());
			}
			dataOut.writeInt(gamestate.bullets.size());
			for(int i = 0; i < gamestate.bullets.size(); i++) {
				Bullet b = gamestate.bullets.get(i);
				dataOut.writeInt(b.getX());
				dataOut.writeInt(b.getY());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return baOut.toByteArray();
	}
	
	public static byte[] serializeMap(GameState gamestate) {
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(baOut);
		try {
			dataOut.writeInt(gamestate.getMap().getMapHeight());
			dataOut.writeInt(gamestate.getMap().getMapWidth());
			for(int y = 0; y < gamestate.getMap().getMapHeight(); y++) {
				for(int x = 0; x < gamestate.getMap().getMapWidth(); x++) {
					dataOut.writeInt(gamestate.getMap().getBlock()[y][x].getBlockID());
				}
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return baOut.toByteArray();
	}
	
	public static GameState deserialize(byte[] data) {
		ByteArrayInputStream baIn = new ByteArrayInputStream(data);
		DataInputStream dataIn = new DataInputStream(baIn);
		GameState newState = new GameState(Main.getGameState().map);
		try {
			//flush packet type
			dataIn.readInt();
			int playerNum = dataIn.readInt();
			System.out.println(playerNum);
			for(int i = 0; i < playerNum; i++) {
				int x = dataIn.readInt();
				int y = dataIn.readInt();
				int team = dataIn.readInt();
				int rotation = dataIn.readInt();
				int money = dataIn.readInt();
				int isMeInt = dataIn.readInt();
				int reloadingInt = dataIn.readInt();
				int deadInt = dataIn.readInt();
				boolean reloading = false;
				if(reloadingInt == 1) {
					reloading = true;
				}
				else if(reloadingInt == 0) {
					reloading = false;
				}
				boolean isMe = false;
				if(isMeInt == 1) {
					isMe = true;
				}
				else if (isMeInt == 0) {
					isMe = false;
				}
				boolean dead = false;
				if(deadInt == 1) {
					dead = true;
				}
				else if (deadInt == 0) {
					dead = false;
				}
				Player p = new Player(x,y,30,team,null,reloading,isMe,money,dead);
				p.setRotation(rotation);
				newState.addPlayer(p);
				System.out.println(x+ " " + y+ " " + team );
			}
			int bulletsNum = dataIn.readInt();
			for(int i = 0; i < bulletsNum; i++) {
				int x = dataIn.readInt();
				int y = dataIn.readInt();
				int size = dataIn.readInt();
				newState.addBullet(new Bullet(x,y, 0, 0, size, null));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return newState;
	}
	public static Map deserializeMap(byte[] data) {
		ByteArrayInputStream baIn = new ByteArrayInputStream(data);
		DataInputStream dataIn = new DataInputStream(baIn);
		Map newMap = new Map();
		newMap.createDefaultMap();
		try {
			//flush packet type
			dataIn.readInt();
			int height = dataIn.readInt();
			int width = dataIn.readInt();
			newMap = new Map(width, height);
			for(int y = 0; y < newMap.getMapHeight(); y++) {
				for(int x = 0; x < newMap.getMapWidth(); x++) {
					newMap.getBlock()[y][x] = new Block(x, y, newMap.getBlockSize(), dataIn.readInt());
				}
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return newMap;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Player getMe() {
		for(int i = 0; i < this.players.size(); i++) {
			if(this.players.get(i).isMe()) {
				return this.players.get(i);
			}
		}
		return null;
	}
	
	
	
	
	
	
		
		
	
	
}


