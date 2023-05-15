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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import packageModel.*;
import packageModel.chessPiece.NonEmpty;

import java.io.IOException;

public class BoardView extends Application {

    @FXML
    private TextField fenNormal;
    @FXML
    private TextField fenMagic;
    @FXML
    private TextField newPcid;

    private final int WINDOW_WIDTH = 800;

    private final String fenStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private final String fenStartMagic = "rniwkinr/pppppppp/8/8/8/8/PPPPPPPP/RNIWKINR w KQkq - 0 1";

    private final int WINDOW_HEIGHT = 800;

    private final int ROW_WIDTH = WINDOW_WIDTH/8;
    private final int ROW_HEIGHT = WINDOW_HEIGHT/8;
    private boolean isChanged = false;

    private Stage stage;
    private Coord dep;
    private Coord arr;

    private Scene scene;
    private static PcId pcid = PcId.EMPTY ;

    private static GameHandler handler = new GameHandler();

    private Group grid = new Group();
    private Parent chom;


    public static void mainGUI(String[] args) throws Exception{
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
        handler.getGame().printBoard();

        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                Piece aff = handler.getGame().getPiece(i,j);
                if(aff.isEmpty()) continue;
                NonEmpty piece = (NonEmpty) aff;
                try{
                Image img = new Image(piece.getImgPath(), 100, 100, false, false);
                ImageView imgView = new ImageView(img);
                imgView.setX(i * ROW_WIDTH);
                imgView.setY(j * ROW_HEIGHT);
                    grid.getChildren().add(imgView);} catch (Exception e){};
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
        try {list.getAllDest().forEach( tile -> {
            Rectangle rect = new Rectangle(
                    tile.x() * ROW_WIDTH,
                    tile.y() * ROW_HEIGHT,
                    ROW_WIDTH,
                    ROW_HEIGHT
                    );
            rect.setFill(Color.ORANGE);
            rect.setOpacity(0.3);
            grid.getChildren().add(rect);
        });} catch (Exception e){return;} // Errors come from empty list so we ignore them

    }

    private void endGame(){
        Rectangle rect = new Rectangle(100,100,600,600);
        rect.setFill(Color.BLACK);
        rect.setOpacity(0.8);
        Text text = new Text("Game ended !");
        text.setX(350);
        text.setY(400);
        text.setFill(Color.WHITE);
        grid.getChildren().add(rect);
        grid.getChildren().add(text);

        // No save when the game is over
        scene.setOnKeyPressed(null);
    }

    private void makeMove(){
        handler.setPromote(pcid);
        handler.play(dep,arr);
        pcid = PcId.EMPTY;
        printGrid();
        if(handler.getGameState() > 1) {
            endGame();
        }
    }

    private void loadGame(Node e){
        printGrid();
        stage = (Stage)e.getScene().getWindow();
        scene = new Scene(grid);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private static boolean firstMove = true;
            private static Coord lastClick;
            @Override
            public void handle(MouseEvent mouseEvent) {

                Coord click = getMouseCoord(mouseEvent);
                firstif:
                if(firstMove){
                    int current = (handler.getGame().getPiece(click).isWhite()) ? 1 : -1;
                    if(handler.getTurn() !=  current) return;
                    colorPossibleMoves(click);
                } else {
                    boolean need = handler.needPromotion(lastClick,click);
                    System.out.println(need);
                    /*if(need){
                        try {
                            chom = FXMLLoader.load(getClass().getResource("ChangePiece.fxml"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        scene.setRoot(chom);
                        stage.show();
                        dep = lastClick;
                        arr = click;
                        break firstif;
                    }*/
                    dep = lastClick;
                    arr = click;
                    makeMove();

                }
                firstMove = !firstMove;
                lastClick = click;

            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.W){
                    System.out.println("FEN code of the game : "+handler.toFen());
                    System.exit(0);
                }
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


    public void checkNew(ActionEvent e){
        handler = new GameHandler();
        pcid =  PcId.values()[Integer.parseInt(newPcid.getText())];
        loadGame((Node)e.getSource());
        makeMove();
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
        handler.setGamemode(0);
        changeToGame(e,fenStartMagic);
    }

    public void changeMenuToMagicFEN(ActionEvent e){
        handler.setGamemode(0);
        String fen = fenMagic.getText();
        if(!changeToGame(e,fen)){
            fenNormal.setText("");
        }
    }

    public void changeMenuToKOTH(ActionEvent e){
        handler.setGamemode(1);
        changeToGame(e,fenStart);
    }
}
