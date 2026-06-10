
/* 
    File: Leledash
    Author: Leo
    Date Created: June 2, 2026
    Date Last Modified: June 10, 2026
 */
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
import java.util.ArrayList;

public class HelloFX extends Application {

    AnimationTimer gameLoop;
    int[] attempts = {1};
    double[] percentage = {0};
    // menu screen method
    private Scene menuScreen(Stage stage) {
        VBox menuLay = new VBox(20); //use VBox as the root
        Scene scene = new Scene(menuLay, 800, 500); //create the scene
        Text title = new Text("Leledash"); //create text
        title.setFont(Font.font("Arial", 50)); //change size and font of the text
        menuLay.setAlignment(Pos.CENTER); //position everything in the center of the screen with VBox

        Button startBtn = new Button("Start Game");// add buttons on the menu screen
        startBtn.setOnAction(e -> stage.setScene(gameScreen(stage)));// switch to game screen when start button is pressed
        Button instructionsBtn = new Button("How to play");//switch to instructions screen when pressed
        instructionsBtn.setOnAction(e -> stage.setScene(instructionsScreen(stage)));
        Button leaderboardBtn = new Button("Leaderboard");
        menuLay.getChildren().addAll(title, startBtn, instructionsBtn, leaderboardBtn); // adds all nodes to the scene
        
        return scene;
    }

    private Scene deathScreen(Stage stage) { //death screen method
        VBox deathLay = new VBox(20); //VBox as root
        Scene scene = new Scene(deathLay, 800, 500);
        Text attemptsTxt = new Text("Attempts: " + attempts[0]); //add text
        Button retryBtn = new Button("Retry");
        retryBtn.setOnAction(e -> stage.setScene(gameScreen(stage))); //restart when button is clicked
        deathLay.setAlignment(Pos.CENTER);
        deathLay.getChildren().addAll(retryBtn,attemptsTxt);//add nodes
        
        return scene;
    }

    private Scene winScreen(Stage stage) { //win screen method
        VBox winLay = new VBox(20);
        Scene scene = new Scene(winLay, 800, 500);
        Text winMsg = new Text("LEVEL COMPLETE");
        Button retryBtn = new Button("Play Again");

        Text attemptsTxt = new Text("Attempts: " + attempts[0]); //show attempts on the screen
        winLay.getChildren().addAll(winMsg,retryBtn,attemptsTxt);
        winLay.setAlignment(Pos.CENTER);//center it
        return scene;
    }

    private Scene instructionsScreen(Stage stage) { //instructions screen method
        VBox insLay = new VBox(20);
        Scene scene = new Scene(insLay, 800, 500);
        Text insTxt = new Text("How to play?\nPress spacebar to jump. Time your jumps to avoid the red killbricks AKA spikes.\nMake it to the end of the level to win.\nHave fun!");
        Button menuBtn = new Button("Main Menu");
        menuBtn.setOnAction(e -> stage.setScene(menuScreen(stage)));//back to menu when button pressed
        insLay.getChildren().addAll(insTxt,menuBtn);
        insLay.setAlignment(Pos.CENTER);
        return scene;
    }

    private Scene gameScreen(Stage stage) { //game screen method
        Pane gameLay = new Pane(); //pane root for easier positioning of elements 
        Scene scene = new Scene(gameLay,800,500);
        gameLay.setStyle("-fx-background-color: ADD8E6");//change colour of the pane
        Text attemptsTxt = new Text("Attempt: " + attempts[0]);
        Text percentageTxt = new Text("Percent: " + percentage[0]);
        attemptsTxt.setX(20);//position attempts at top left
        attemptsTxt.setY(30);
        percentageTxt.setX(600);//position percentage at the top middle
        percentageTxt.setY(30);
        
        //player setup
        Rectangle player = new Rectangle(50,50, Color.BLACK); //create the player 
        player.setX(100);//position player near the left of the screen
        player.setY(300);

        Rectangle ground = new Rectangle(1920,250, Color.GREEN); //create the ground
        ground.setX(0);
        ground.setY(400);

        gameLay.getChildren().addAll(player,ground,attemptsTxt,percentageTxt);

        double groundY = 350;
        double[] velocityY = {0.0}; 
        boolean[] onGround = {true};
        double gravity = 0.4;
        
        //jump when spacebar is clicked and player is on the ground or platform
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && (onGround[0])) {
                velocityY[0] = -10.0;
                onGround[0] = false;
            }
        });
        

        ArrayList<Rectangle> spikes = new ArrayList<>();
        ArrayList<Rectangle> platforms = new ArrayList<>();
        
        Rectangle spike1 = new Rectangle(40,40,Color.RED);
        Rectangle spike2 = new Rectangle(80,40,Color.RED);
        Rectangle spike3 = new Rectangle(100,40,Color.RED);
        Rectangle spike4 = new Rectangle(40,40,Color.RED);
        Rectangle spike5 = new Rectangle(40,40,Color.RED);
        Rectangle spike6 = new Rectangle(40,40,Color.RED);
        Rectangle spike7 = new Rectangle(100,40,Color.RED);
        Rectangle spike8 = new Rectangle(100,40,Color.RED);
        Rectangle spike9 = new Rectangle(100,40,Color.RED);
        Rectangle spike10 = new Rectangle(1700,40,Color.RED);
        Rectangle spike11 = new Rectangle(40,40,Color.RED);
        Rectangle spike12 = new Rectangle(115,40,Color.RED);
        Rectangle spike13 = new Rectangle(115,40,Color.RED);
        Rectangle spike14 = new Rectangle(115,40,Color.RED);
        Rectangle plat1 = new Rectangle(200,20,Color.GREEN);
        Rectangle plat2 = new Rectangle(200,20,Color.GREEN);
        Rectangle plat3 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat4 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat5 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat6 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat7 = new Rectangle(90,20,Color.GREEN);

        spike1.setX(1000);
        spike2.setX(1400);        
        spike3.setX(1800);        
        spike4.setX(2250);               
        spike5.setX(2500);             
        spike6.setX(2800);        
        spike7.setX(3100);        
        spike8.setX(3600);        
        spike9.setX(4100); 
        spike10.setX(4600);        
        spike11.setX(6200);   
        spike12.setX(6600);   
        spike13.setX(7000);   
        spike14.setX(7500);   

        spike1.setY(360);
        spike2.setY(360);
        spike3.setY(360);
        spike4.setY(360); 
        spike5.setY(320);   
        spike6.setY(280);
        spike7.setY(360);
        spike8.setY(360);
        spike9.setY(360);
        spike10.setY(360);
        spike11.setY(140);
        spike12.setY(360);  
        spike13.setY(360);  
        spike14.setY(360);  

        plat1.setX(2500);
        plat2.setX(2800);       
        plat3.setX(4600);       
        plat4.setX(4950);      
        plat5.setX(5300);        
        plat6.setX(5650);        
        plat7.setX(6000); 

        plat1.setY(350);
        plat2.setY(320);
        plat3.setY(350);
        plat4.setY(320);
        plat5.setY(290);
        plat6.setY(260);
        plat7.setY(230);
        

        spikes.add(spike1);
        spikes.add(spike2);
        spikes.add(spike3);
        spikes.add(spike4);
        spikes.add(spike5);
        spikes.add(spike6);
        spikes.add(spike7);
        spikes.add(spike8);
        spikes.add(spike9);
        spikes.add(spike10);
        spikes.add(spike11);
        spikes.add(spike12);
        spikes.add(spike13);
        spikes.add(spike14);
        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
        platforms.add(plat4);
        platforms.add(plat5);
        platforms.add(plat6);
        platforms.add(plat7);
        
        

        
        
        
        for (Rectangle i : spikes) {
            gameLay.getChildren().add(i);
        }

        for (Rectangle i : platforms) {
            gameLay.getChildren().add(i);
        }
        gameLoop = new AnimationTimer() { //game loop runs 60 times/second to constantly check and update player position 
            @Override //replace handle method
            public void handle(long now) {
                velocityY[0] += gravity; //apply gravity, moves player down every frame

                player.setY(player.getY() + velocityY[0]); //translate players y position
                if (player.getY() >= groundY) {
                    player.setY(groundY);
                    velocityY[0] = 0;
                    onGround[0] = true;
                }

                for (Rectangle i : spikes) {
                    i.setX(i.getX() - 5);
                    
                }
                for (Rectangle i : platforms) {
                    i.setX(i.getX() - 5);
                }

                for (Rectangle i : spikes) {
                    if (player.getBoundsInParent().intersects(i.getBoundsInParent())) {
                        gameLoop.stop(); 
                        stage.setScene(deathScreen(stage));
                        attempts[0]++;
                        percentage[0] = 0;
                    }
                }

                for (Rectangle j : platforms) {
                    if (player.getBoundsInParent().intersects(j.getBoundsInParent()) && velocityY[0] > 0) {
                        player.setY(j.getY() - player.getHeight());
                        velocityY[0] = 0;
                        onGround[0] = true;
                    }
                }

                percentage[0] += 0.05;
                percentageTxt.setText("Percent: " + Math.round(percentage[0]) + "%");

                if (spike14.getX() <= -100) {
                    gameLoop.stop();
                    stage.setScene(winScreen(stage));

                }
            }
        };
    
        
        gameLoop.start();
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