package packageView;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import packageModel.Board;
import packageModel.GameHandler;

public class BoardView extends Application {

    final int WINDOW_WIDTH = 800;
    final int WINDOW_HEIGHT = 800;

    final int ROW_WIDTH = WINDOW_WIDTH/8;
    final int ROW_HEIGHT = WINDOW_HEIGHT/8;

    private Board board = new Board();
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param root le group dans lequel le tableau va être
     */
    public void emptyGrid(Group root){

        // On reset le group
        root.getChildren().clear();

        boolean isBlack = false;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){

                Rectangle rect = new Rectangle(
                        j * ROW_WIDTH,
                        i * ROW_HEIGHT,
                        ROW_WIDTH,
                        ROW_HEIGHT
                );
                if(isBlack) rect.setFill(Color.GREY);
                else rect.setFill(Color.WHITE);
                isBlack = !isBlack;
                root.getChildren().add(rect);

            }
            isBlack = !isBlack;
        }

    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_WIDTH, Color.WHITE);

        emptyGrid(root);



        stage.setTitle("JChess");
        stage.setScene(scene);
        stage.show();
    }
}
