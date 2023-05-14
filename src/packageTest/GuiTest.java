package packageTest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GuiTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void test1(Stage stage, Group root){
        Text noirs = new Text();
        noirs.setText("Noirs");
        Text blancs = new Text();
        blancs.setText("Blancs"); blancs.setX(50); blancs.setY(50);
        blancs.setFont(Font.font("Ubuntu",30));
        blancs.setTextAlignment(TextAlignment.RIGHT);

        // Constructeur : x, y, width, height
        Rectangle rectangle = new Rectangle(100, 300, 100, 100);

        Image image = new Image("packageTest/test.jpg",100f,100f,false,false);
        ImageView imageView = new ImageView(image);
        imageView.setX(60);
        imageView.setY(60);


        root.getChildren().add(blancs);
        root.getChildren().add(rectangle);
        root.getChildren().add(imageView);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root,800,600, Color.WHITE);

        stage.setTitle("JChess");
        stage.setScene(scene);
        stage.show();
    }
}