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

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage pStage) throws Exception {
		int numBlocks = DEFAULT_SIZE;
		
		pStage.setTitle("Section 11");
		BorderPane layout = new BorderPane();
		Scene pScene = new Scene( layout, TILE_SIZE * ( numBlocks + 1 ), TILE_SIZE * numBlocks); 
		
		Canvas canvas = new Canvas(TILE_SIZE * numBlocks, TILE_SIZE * numBlocks);
		pBoard = canvas.getGraphicsContext2D();
		
		layout.setLeft(cls);
		layout.setCenter(canvas);
		
		pStage.setScene(pScene);
		pStage.show();
	}
}

