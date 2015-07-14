
public class GameController
{
	private GameModel gameModel;
	private GameView gameView;
	private Controller controller;
	
	public void setUpGame()
	{
		gameModel.setGameController(this);
		gameModel.setGameView(gameView);
		gameView.setGameModel(gameModel);
		
		gameView.setUp();
	}
	
	public void playGame()
	{
		while (gameModel.isGameRunning())
		{
			gameModel.update();
			gameView.update();
		}
		
		finishGame();
	}
	
	public void finishGame()
	{
		gameView.finish();
		controller.gameOver();
	}
	
	public GameController()
	{
		gameModel = new GameModel();
		gameView = new GameView();
	}
	
	public GameModel getGameModel() {
		return gameModel;
	}
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	public GameView getGameView() {
		return gameView;
	}
	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}