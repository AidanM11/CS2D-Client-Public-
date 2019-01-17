
public class Main {
	
	private static Renderer render;
  	private static GameState gamestate;
  	private static InputHandle input;
  	private static Map map;
  	private static ConnectionHandler conn;

  
	
	public static void main(String[] args) {
		//Create input handle and give it to gamestate constuctor as arg
		input = new InputHandle();
		gamestate = new GameState(input, map);
		render = new Renderer(gamestate, input, map);
		conn = new ConnectionHandler("10.2.23.137", 61447, input);
		System.out.println("threads running");
		conn.start();
		System.out.println("threads running");
		render.start();
;
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
