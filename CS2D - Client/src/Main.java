
public class Main {
	
	private static Renderer render;
  	private static GameState gamestate;
  	private static InputHandle input;
  	private static Map map;
  	private static ConnectionHandler conn;

  
	
	public static void main(String[] args) {
		//Create input handle and give it to gamestate constuctor as arg
		map = new Map();
		map.loadMap();
		input = new InputHandle();
		gamestate = new GameState(map);
		render = new Renderer(gamestate, input, map);
		conn = new ConnectionHandler("10.2.23.137", 62036, input);
		System.out.println("threads running");
		conn.start();
		System.out.println("threads running");
		render.start();
;
	}
	
	public static void setGameState(GameState argGameState) {
		gamestate.setEqualTo(argGameState);
		System.out.println("updating gamestate");
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
