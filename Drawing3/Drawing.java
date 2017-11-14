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

	private Button cls = new Button("Clear");
	private char[][] log;
	private boolean currVal = true; //  true for x.
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

		MMouseEvent handler = new MMouseEvent();
		Cleaner cleans = new Cleaner();
		
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
		cls.addEventHandler(ActionEvent.ACTION, cleans);
		
		layout.setLeft(cls);
		layout.setCenter(canvas);
		
		pStage.setScene(pScene);
		pStage.show();
	}
	
	public boolean isValid( int x, int y ) {
		return x >= 0 && x < log.length 
					  && y >= 0 
					  && y < log.length
					  && log[x][y] == '\0';
	}
	
	public void drawAt(int x, int y) {
		int dx = x * TILE_SIZE + TILE_SIZE / 2;
		int dy = y * TILE_SIZE + TILE_SIZE / 2;
		
		pBoard.setFill(Color.BLACK);

		if(currVal) {
			pBoard.fillText("X", dx, dy);
			log[x][y] = 'X';
		}
		else {
			pBoard.fillText("O", dx, dy);
			log[x][y] = 'O';
		}
	}	
	
	//  *** LISTENERS ***  //
	
	private class Cleaner implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			for ( int i = 0; i < log.length; i++ ) {
				for ( int j = 0; j < log.length; j++ ) {
					log[i][j] = '\0';
				}
			}
			createBoard(pBoard, log.length);
		}
	}
	
	private class MMouseEvent implements EventHandler<MouseEvent> {

	
		@Override
		public void handle(MouseEvent event) {
			int x = (int) ( event.getX() / TILE_SIZE );
			int y = (int) ( event.getY() / TILE_SIZE );
			if ( isValid (x, y) ) {
				drawAt(x, y);
				currVal = !currVal;
			}
		}
	}
}

