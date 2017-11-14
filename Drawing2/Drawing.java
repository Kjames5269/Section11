import javafx.application.Application;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

@SuppressWarnings("restriction")

public class Drawing extends Application {
	
	private char[][] log;
	private GraphicsContext pBoard;
	
	public final static int TILE_SIZE = 50;
	public final static int DEFAULT_SIZE = 3;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void createBoard(GraphicsContext board, int numBlocks) {

		board.setFill(Color.WHITE);
		board.fillRect(0, 0, TILE_SIZE * numBlocks, TILE_SIZE * numBlocks);
		
		board.setFill(Color.BLACK);
		for (int i = 1; i < numBlocks; i++) {
			board.strokeLine(0, i * TILE_SIZE, TILE_SIZE * numBlocks, i * TILE_SIZE );
		}
		for (int i = 1; i < numBlocks; i++) {
			board.strokeLine(i * TILE_SIZE, 0, i * TILE_SIZE, TILE_SIZE * numBlocks);
		}
	}
	
	@Override
	public void start(Stage pStage) throws Exception {
		int numBlocks = DEFAULT_SIZE;
		
		log = new char[numBlocks][numBlocks];
		
		pStage.setTitle("Section 11");
		BorderPane layout = new BorderPane();
		Scene pScene = new Scene( layout, TILE_SIZE * ( numBlocks + 1 ), TILE_SIZE * numBlocks); 
		
		Canvas canvas = new Canvas(TILE_SIZE * numBlocks, TILE_SIZE * numBlocks);
		pBoard = canvas.getGraphicsContext2D();
		
		createBoard(pBoard, numBlocks);
		
		Button cls = new Button("Clear");
		layout.setLeft(cls);
		layout.setCenter(canvas);
		
		pStage.setScene(pScene);
		pStage.show();
	}
}

