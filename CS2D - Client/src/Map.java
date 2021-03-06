
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Map implements Serializable {

	private int mapHeight;
	private int mapWidth;
	
	private int blockSize;
	
	private Block[][] block;
	
	
	
	public Map() {
		this.mapHeight = 25;
		this.mapWidth = 25;
		this.blockSize = 30;
		block = new Block[mapWidth][mapHeight];
	}
	public Map(int width, int height) {
		this.mapHeight = height;
		this.mapWidth = width;
		this.blockSize = 30;
		block = new Block[mapWidth][mapHeight];
	}
	
	public void createDefaultMap() {
		
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block(x, y, 30, 0);
			}
		}
	}
	
	public void loadMap() {
		Save save = new Save(this, blockSize);
		InputStream in = Map.class.getClassLoader().getResourceAsStream("save1.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		block = save.loadSave(reader);
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}


	public Block[][] getBlock() {
		return block;
	}

	public boolean collides(Player p) {
		
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if(block[y][x].getHitbox().intersects(p.getPlayerHitbox()) && block[y][x].getBlockID() != 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean bulletCollides(Bullet b) {
		
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if(block[y][x].getHitbox().intersects(b.getBulletHitbox()) && block[y][x].getBlockID() != 0) {
					return true;
				}
			}
		}
		return false;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	

	
	
	
	
}
