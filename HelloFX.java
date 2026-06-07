import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.animation.AnimationTimer;


public class HelloFX extends Application {
    private Scene menuScreen(Stage stage) {
        VBox menuLay = new VBox(20);
        Scene scene = new Scene(menuLay,800,500);
        Text title = new Text("Leledash");
        menuLay.setAlignment(Pos.CENTER);
        
        Button startBtn = new Button("Start Game");
        startBtn.setOnAction(e -> stage.setScene(gameScreen(stage)));
        Button instructionsBtn = new Button("How to play");
        Button leaderboardBtn = new Button("Leaderboard");
        menuLay.getChildren().addAll(title,startBtn,instructionsBtn,leaderboardBtn);
        title.setFont(Font.font("Arial", 50));
        return scene;
    }

    private Scene gameScreen(Stage stage) {
        AnimationTimer gameLoop = new AnimationTimer() {

        }
        return scene;
    }   

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Leledash");

        Image icon = new Image("Drawing.png");
        stage.getIcons().add(icon);

        Scene menu = menuScreen(stage);
        stage.setScene(menu);
        stage.show();

       

        



    }

    public static void main(String[] args) {
        launch();
    }

}