import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{

	private GameState gamestate;
	private Map map;
	private int blockSize;
	private Image[] tileset_skins;
	private int teamVal;
	
	
	public Panel(GameState gamestate, Map map) {
		super();
		this.gamestate = gamestate;
		this.map = map;
		this.blockSize = 30;
		
		
		tileset_skins = new Image[100];
		
		for(int i = 0; i<tileset_skins.length;i++) {
			InputStream input = Panel.class.getClassLoader().getResourceAsStream("tileset_skins.png");
			try {
				tileset_skins[i] = ImageIO.read(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tileset_skins[i] = createImage(new FilteredImageSource(tileset_skins[i].getSource(), new CropImageFilter(0,25 * i, 25,25)));
			
		}
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		
		drawMap(g, gamestate.getMap());
		
		for(int i = 0; i < gamestate.getPlayers().size(); i++) {
			drawPlayer(g, gamestate.getPlayers().get(i));
		}
		for(int i = 0; i < gamestate.getBullets().size(); i++) {
			drawBullet(g, gamestate.getBullets().get(i));
		}
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void drawMap(Graphics g, Map map) {
		Block block;
		int centerWidth = (this.getWidth()/2) - (map.getMapWidth() * blockSize / 2);
		int centerHeight = (this.getHeight()/2) - (map.getMapHeight() * blockSize / 2);
		for(int y = 0; y < map.getBlock().length; y++) {
			for (int x = 0; x < map.getBlock()[0].length; x++) {
				block = map.getBlock()[y][x];
				
				//g.fillRect(centerWidth + (x * blockSize),centerHeight + (y * blockSize), blockSize, blockSize);
				g.drawImage(tileset_skins[block.getBlockID()], centerWidth + (x * blockSize), centerHeight + (y * blockSize), blockSize, blockSize, null);
				//g.setColor(Color.RED);
				//g.drawRect(centerWidth + (block.getHitbox().x), centerHeight + (block.getHitbox().y), block.getHitbox().width, block.getHitbox().height);
		}
	}
}
	
	
	public void drawPlayer(Graphics g, Player player) { 
	
		
		int centerWidth = (this.getWidth()/2) - (map.getMapWidth() * blockSize / 2);
		int centerHeight = (this.getHeight()/2) - (map.getMapHeight() * blockSize / 2);
		
		
		if(player.getTeam() == 0 && player.getRotation() == 0) {
			teamVal = 5;
		}
		if(player.getTeam() == 0 && player.getRotation() == 1) {
			teamVal = 10;
		}
		if(player.getTeam() == 0 && player.getRotation() == 2) {
			teamVal = 9;
		}
		if(player.getTeam() == 0 && player.getRotation() == 3) {
			teamVal = 8;
		}
		
		
		
		if(player.getTeam() == 1 && player.getRotation() == 0) {
			teamVal = 4;
		}
		if(player.getTeam() == 1 && player.getRotation() == 1) {
			teamVal = 13;
		}
		if(player.getTeam() == 1 && player.getRotation() == 2) {
			teamVal = 12;
		}
		if(player.getTeam() == 1 && player.getRotation() == 3) {
			teamVal = 11;
		}
		
		
		g.drawImage(tileset_skins[teamVal],player.getX() + centerWidth,player.getY() + centerHeight,player.getSize(),player.getSize(),null);
		
		if(player.isReloading()) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.BOLD, 40));
			g.drawString("R", player.getX() + centerWidth, player.getY() + player.getSize() + centerHeight);
		}
		player.setPlayerHitbox();
		//g.setColor(Color.GREEN);
		//g.drawRect(player.getPlayerHitbox().x, player.getPlayerHitbox().y, player.getPlayerHitbox().width, player.getPlayerHitbox().height);
		
	}
	
	public void drawBullet(Graphics g, Bullet bullet) {
		int centerWidth = (this.getWidth()/2) - (map.getMapWidth() * blockSize / 2);
		int centerHeight = (this.getHeight()/2) - (map.getMapHeight() * blockSize / 2);
		
		g.setColor(Color.BLACK);
		g.fillRect(bullet.getX() + centerWidth, bullet.getY() + centerHeight, bullet.getBulletSize(), bullet.getBulletSize());
		
		
	}
	
	
	
	
	


}
