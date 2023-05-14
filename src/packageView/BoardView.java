package packageView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import packageModel.Board;
import packageModel.Coord;
import packageModel.GameHandler;
import packageModel.Piece;
import packageModel.chessPiece.NonEmpty;

public class BoardView extends Application {

    private final int WINDOW_WIDTH = 800;

    private final String fenStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private final int WINDOW_HEIGHT = 800;

    private final int ROW_WIDTH = WINDOW_WIDTH/8;
    private final int ROW_HEIGHT = WINDOW_HEIGHT/8;

    private Stage stage;

    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param gameRoot le group dans lequel le tableau va être
     */

    private void emptyGrid(Group gameRoot){


        // On reset le group
        gameRoot.getChildren().clear();

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
                gameRoot.getChildren().add(rect);

            }
            isBlack = !isBlack;
        }

    }

    private void printGrid(Group gridView, Board board){
        emptyGrid(gridView);

        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                Piece aff = board.getPiece(i,j);
                if(aff.isEmpty()) continue;
                NonEmpty piece = (NonEmpty) aff;
                try {
                    Image img = new Image(piece.getImgPath(), 100, 100, false, false);
                    ImageView imgView = new ImageView(img);
                    imgView.setX(i * ROW_WIDTH);
                    imgView.setY(j * ROW_HEIGHT);

                    gridView.getChildren().add(imgView);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Group grid = new Group();
        Parent menu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(menu,WINDOW_WIDTH,WINDOW_WIDTH, Color.WHITE);
        Board board = new Board();
        GameHandler gh = new GameHandler();

        scene.setRoot(grid);

        gh.setBoard(board);
        gh.loadFen(fenStart);
        gh.getGame().printBoard();
        printGrid(grid,gh.getGame());

        stage.setTitle("JChess");
        stage.setScene(scene);
        stage.show();
    }
}
