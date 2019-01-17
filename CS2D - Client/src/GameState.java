import java.io.Serializable;
import java.util.ArrayList;
public class GameState implements Serializable {

	private InputHandle input;
	private ArrayList<Player> players;
	private ArrayList<Bullet> bullets;
	private Map map;
	
	
	
	

	
	public GameState(InputHandle input, Map map) {
		super();
		this.input = input;
		this.players = new ArrayList<Player>();
		this.bullets = new ArrayList<Bullet>();
		this.map = map;
		
		
	}
	public void update() {
		
	

	
		
			Player p = getPlayers().get(0);
			Player p1;
			int pY = getPlayers().get(0).getY();
			int pX = getPlayers().get(0).getX();
			if(input.getKeys()[0] == true) {
				p1 = new Player(p);
				p1.setY(pY - 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY - 2);
				}
				
			
			}
			if(input.getKeys()[1] == true) {
				p1 = new Player(p);
				p1.setX(pX - 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX - 2);
				}
				
			}
			if(input.getKeys()[2] == true) {
				p1 = new Player(p);
				p1.setY(pY + 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY + 2);
				}
				
			}
			if(input.getKeys()[3] == true) {
				p1 = new Player(p);
				p1.setX(pX + 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX + 2);
				}
			}
			
			if(input.getKeys()[4] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 0, -12, 5, 0));
			}
			else if(input.getKeys()[5] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 0, 12, 5, 0));
			}
			else if(input.getKeys()[6] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), -12, 0, 5, 0));
			}
			else if(input.getKeys()[7] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 12, 0, 5, 0));
			}
			
			
			for(int i = 0; i < this.bullets.size(); i++) {
				Bullet b = this.bullets.get(i);
				b.setX(b.getX() + b.getVelX());
				b.setY(b.getY() + b.getVelY());
				if(map.bulletCollides(b)) {
					this.bullets.remove(b);
				}
			}
			
			
			
			
			
			
			
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
	public InputHandle getInput() {
		return input;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setEqualTo(GameState gs) {
		this.players = gs.players;
		this.bullets = gs.bullets;
		this.map = gs.map;
	}
	
	
	
	
	
		
		
	
	
}


