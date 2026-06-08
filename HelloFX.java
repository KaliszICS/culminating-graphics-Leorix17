import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    //menu screen method
    private Scene menuScreen(Stage stage) {
        VBox menuLay = new VBox(20);
        Scene scene = new Scene(menuLay,800,500);
        Text title = new Text("Leledash");
        menuLay.setAlignment(Pos.CENTER);
        
        //add buttons on the menu screen
        Button startBtn = new Button("Start Game");
        //switch to game screen when start button is pressed
        startBtn.setOnAction(e -> stage.setScene(gameScreen(stage)));
        Button instructionsBtn = new Button("How to play");
        Button leaderboardBtn = new Button("Leaderboard");
        menuLay.getChildren().addAll(title,startBtn,instructionsBtn,leaderboardBtn); //adds all nodes to the scene
        title.setFont(Font.font("Arial", 50));
        return scene;
    }

    private Scene gameScreen(Stage stage) { //game screen method
        Pane gameLay = new Pane(); //pane root for easier positioning of elements 
        Scene scene = new Scene(gameLay,800,500);
        Rectangle player = new Rectangle(50,50, Color.RED);
        player.setX(100);
        player.setY(300);
        gameLay.getChildren().add(player);

        Double[] velocityY = {0.0}; 
        Double gravity = 1.0;
        
        player.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.SPACE) {
                        velocityY[0] = -12.0;
                    }
        AnimationTimer gameLoop = new AnimationTimer() { //game loop to constantly check and update player position 
            @Override //replace handle method
            public void handle(long now) {
                velocityY[0] += gravity;
                player.setY(player.getY() + velocityY[0]);
                }
            };
        gameLoop.start();
        });
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