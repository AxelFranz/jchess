package packageView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import packageModel.*;
import packageModel.chessPiece.NonEmpty;

public class BoardView extends Application {

    @FXML
    private TextField fenNormal;
    @FXML
    private TextField fenMagic;
    @FXML
    private AnchorPane parent;

    private final int WINDOW_WIDTH = 800;

    private final String fenStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private final int WINDOW_HEIGHT = 800;

    private final int ROW_WIDTH = WINDOW_WIDTH/8;
    private final int ROW_HEIGHT = WINDOW_HEIGHT/8;

    private Stage stage;

    private Scene scene;

    private GameHandler handler = new GameHandler();

    private Group grid = new Group();


    public static void main(String[] args) throws Exception{
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

    private void printGrid(){
        emptyGrid(grid);

        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                Piece aff = handler.getGame().getPiece(i,j);
                if(aff.isEmpty()) continue;
                NonEmpty piece = (NonEmpty) aff;
                Image img = new Image(piece.getImgPath(), 100, 100, false, false);
                ImageView imgView = new ImageView(img);
                imgView.setX(i * ROW_WIDTH);
                imgView.setY(j * ROW_HEIGHT);
                    grid.getChildren().add(imgView);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialisation
        Parent menu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(menu,WINDOW_WIDTH,WINDOW_WIDTH, Color.WHITE);

        // Display the menu
        stage = primaryStage;

        stage.setTitle("JChess");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private Coord getMouseCoord(MouseEvent mouseEvent){
        return new Coord((int)mouseEvent.getX()/ROW_WIDTH,(int)mouseEvent.getY()/ROW_HEIGHT);
    }

    private void colorPossibleMoves(Coord click){
        printGrid();
        handler.getGame().genAllMoves(handler.getTurn()==1);
        MoveList list = handler.getGame().getPiece(click).getValidMoves();
        list.getAllDest().forEach( tile -> {
            Rectangle rect = new Rectangle(
                    tile.x() * ROW_WIDTH,
                    tile.y() * ROW_HEIGHT,
                    ROW_WIDTH,
                    ROW_HEIGHT
                    );
            rect.setFill(Color.ORANGE);
            rect.setOpacity(0.3);
            grid.getChildren().add(rect);
        });

    }
    private void loadGame(Node e){
        printGrid();
        stage = (Stage)e.getScene().getWindow();
        scene = new Scene(grid);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Coord click = getMouseCoord(mouseEvent);
                colorPossibleMoves(click);
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private boolean changeToGame(ActionEvent e,String fen){
        if(!GameHandler.fenChecker(fen)){
            return false;
        }
        handler.loadFen(fen);
        loadGame((Node)e.getSource());
        return true;
    }

    public void changeMenuToNormal(ActionEvent e){
        handler.setGamemode(0);
        changeToGame(e,fenStart);
    }

    public void changeMenuToNormalFEN(ActionEvent e){
        handler.setGamemode(0);
        String fen = fenNormal.getText();
        if(!changeToGame(e,fen)){
            fenNormal.setText("");
        }
    }

    public void changeMenuToMagic(ActionEvent e){
        System.out.println("Magic");
    }

    public void changeMenuToMagicFEN(ActionEvent e){
        System.out.println("MagicFEN");
    }

    public void changeMenuToKOTH(ActionEvent e){
        System.out.println("KOTH");
    }
}
