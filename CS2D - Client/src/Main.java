
public class Main {
	
  private static Renderer render;
  private static GameState gamestate;
  private static InputHandle input;
  private static Map map;

  
	
	public static void main(String[] args) {
		//Create input handle and give it to gamestate constuctor as arg
		input = new InputHandle();
		map = new Map();
		map.loadMap();
		gamestate = new GameState(input, map);
		render = new Renderer(gamestate, input, map);
		gamestate.addPlayer(new Player(200,200,25,1,1));
		render.run();
	}
	
	public static void setGameState(GameState argGameState) {
		gamestate.setEqualTo(argGameState);
	}
	public static GameState getGameState() {
		return gamestate;
	}
	
}


//Notes for aidan
//Added bullet class
//Made inputhandle class take arrow keys
//added bullet arraylist and other getters/setters
//added drawBullet function
