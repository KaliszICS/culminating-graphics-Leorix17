import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root,Color.WHITE);

        stage.setTitle("Leledash");

        Image icon = new Image("Drawing.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        stage.setFullScreen(true);


    }

    public static void main(String[] args) {
        launch();
    }

}