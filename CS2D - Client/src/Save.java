import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Save implements Serializable{
	
	private Map map;
	private int blockSize;
	
	public Save(Map map, int blockSize) {
		this.map = map;
		this.blockSize = blockSize;
	}
	public Block[][] loadSave(BufferedReader read) {
		Block[][] block = new Block[map.getMapHeight()][map.getMapWidth()];
			
		try {
			//Scanner loadScanner = new Scanner(loadPath);
			
			if(read.ready()) {
				
			
				
				for(int y = 0; y < map.getMapHeight();y++) {
					for(int x = 0; x < map.getMapWidth();x++) {
						int temp = -1;
						while(temp < 0) {
							if(read.ready() == false) {
								break;
							}
							temp = read.read();
							temp -= 48;
						}
						System.out.print(temp + " ");
						block[y][x] = new Block(x, y, blockSize, temp);
						
					}
				}
				
				
				
			}
			read.close();
			
		} catch (FileNotFoundException e) { System.out.println("Can't load map!");} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(block);
		return block;
			
			
	}
}
