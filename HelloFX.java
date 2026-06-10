
/* 
    File: Leledash
    Author: Leo
    Date Created: June 2, 2026
    Date Last Modified: June 10, 2026
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class HelloFX extends Application {
    //class variables that can be used across all methods
    AnimationTimer gameLoop;
    int[] attempts = {1};
    double[] percentage = {0};
    ArrayList<String> leaderboard = new ArrayList<>();

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
        leaderboardBtn.setOnAction(e -> stage.setScene(leaderboardScreen(stage)));
        menuLay.getChildren().addAll(title, startBtn, instructionsBtn, leaderboardBtn); // adds all nodes to the scene
        
        return scene;
    }

    private Scene deathScreen(Stage stage) { //death screen method
        VBox deathLay = new VBox(20); //VBox as root
        Scene scene = new Scene(deathLay, 800, 500);
        Text attemptsTxt = new Text("Attempts: " + attempts[0]); //add text
        Text percentTxt = new Text("Percentage: " + Math.round(percentage[0]) + "%");
        Button retryBtn = new Button("Retry");
        retryBtn.setOnAction(e -> stage.setScene(gameScreen(stage))); //restart when button is clicked
        Button menuBtn = new Button("Main Menu");
        menuBtn.setOnAction(e -> stage.setScene(menuScreen(stage)));
        deathLay.setAlignment(Pos.CENTER);
        deathLay.getChildren().addAll(retryBtn,menuBtn,attemptsTxt,percentTxt);//add nodes
        
        return scene;
    }

    private Scene leaderboardScreen(Stage stage) { //leaderboard screen method
        VBox boardLay = new VBox(20);
        Scene scene = new Scene(boardLay, 800, 500);
        Text title = new Text("Leaderboard Scores");
        Button menuBtn = new Button("Main Menu");
        menuBtn.setOnAction(e -> stage.setScene(menuScreen(stage)));
        boardLay.getChildren().addAll(title,menuBtn);

        for (String i : leaderboard) {
            Text scoreTxt = new Text(i);
            boardLay.getChildren().add(scoreTxt);
        }
        return scene;

    }
    private Scene winScreen(Stage stage) { //win screen method
        VBox winLay = new VBox(20);
        Scene scene = new Scene(winLay, 800, 500);
        Text winTxt = new Text("LEVEL COMPLETE");
        Text percentTxt = new Text("Percent Completed: 100%");
        Button menuBtn = new Button("Main Menu");
        menuBtn.setOnAction(e -> stage.setScene(menuScreen(stage)));
        Text attemptsTxt = new Text("Attempts: " + attempts[0]); //show attempts on the screen
        winLay.getChildren().addAll(winTxt,attemptsTxt,percentTxt,menuBtn);
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
                velocityY[0] = -10.0;//setting velocity to -10 makes the player fly up
                onGround[0] = false;//prevent double jumping
            }
        });
        

        ArrayList<Rectangle> spikes = new ArrayList<>();//store nodes in arraylists
        ArrayList<Rectangle> platforms = new ArrayList<>();
        
        //create the level using platforms and spikes
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
        Rectangle spike15 = new Rectangle(10,80,Color.RED);
        Rectangle spike16 = new Rectangle(10,80,Color.RED);
        Rectangle spike17 = new Rectangle(10,80,Color.RED);
        Rectangle spike18 = new Rectangle(10,80,Color.RED);
        Rectangle spike19 = new Rectangle(1000,20,Color.RED);
        Rectangle plat1 = new Rectangle(200,20,Color.GREEN);
        Rectangle plat2 = new Rectangle(200,20,Color.GREEN);
        Rectangle plat3 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat4 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat5 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat6 = new Rectangle(100,20,Color.GREEN);
        Rectangle plat7 = new Rectangle(90,20,Color.GREEN);
        Rectangle plat8 = new Rectangle(60,80,Color.GREEN);
        Rectangle plat9 = new Rectangle(60,80,Color.GREEN);
        Rectangle plat10 = new Rectangle(60,80,Color.GREEN);
        Rectangle plat11 = new Rectangle(60,80,Color.GREEN);

        //Manually place spikes and platforms
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
        spike15.setX(7900);   
        spike16.setX(8150);   
        spike17.setX(8400);   
        spike18.setX(8650);   
        spike19.setX(7940);   

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
        spike15.setY(330);  
        spike16.setY(270);  
        spike17.setY(210);  
        spike18.setY(150); 
        spike19.setY(370);  

        plat1.setX(2500);
        plat2.setX(2800);       
        plat3.setX(4600);       
        plat4.setX(4950);      
        plat5.setX(5300);        
        plat6.setX(5650);        
        plat7.setX(6000); 
        plat8.setX(7910); 
        plat9.setX(8160); 
        plat10.setX(8410); 
        plat11.setX(8660); 

        plat1.setY(350);
        plat2.setY(320);
        plat3.setY(350);
        plat4.setY(320);
        plat5.setY(290);
        plat6.setY(260);
        plat7.setY(230);
        plat8.setY(330);
        plat9.setY(270);
        plat10.setY(210);
        plat11.setY(150);
        
        //add the nodes to the arraylists
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
        spikes.add(spike15);
        spikes.add(spike16);
        spikes.add(spike17);
        spikes.add(spike18);
        spikes.add(spike19);
        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
        platforms.add(plat4);
        platforms.add(plat5);
        platforms.add(plat6);
        platforms.add(plat7);
        platforms.add(plat8);
        platforms.add(plat9);
        platforms.add(plat10);
        platforms.add(plat11);
        
        for (Rectangle i : spikes) {//iterates through the arraylist and adds the rectangles to the screen
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

                for (Rectangle i : spikes) {//moves spikes to the left every frame
                    i.setX(i.getX() - 5);
                    
                }
                for (Rectangle i : platforms) { //moves platforms to the left every frame
                    i.setX(i.getX() - 5);
                }

                for (Rectangle i : spikes) {//check player position if they touched a spike every frame
                    if (player.getBoundsInParent().intersects(i.getBoundsInParent())) {
                        leaderboard.add("Attempt: " + attempts[0] + " - " + Math.round(percentage[0]) + "%");//add attempt and % to leaderboard
                        gameLoop.stop(); 
                        stage.setScene(deathScreen(stage));//switch scene to death screen
                        attempts[0]++;
                        percentage[0] = 0;
                    }
                }

                for (Rectangle j : platforms) { //check player position if they are on a platform every frame
                    if (player.getBoundsInParent().intersects(j.getBoundsInParent()) && velocityY[0] > 0) {
                        player.setY(j.getY() - player.getHeight()); //collision to stop them from falling through the platform
                        velocityY[0] = 0;
                        onGround[0] = true;
                    }
                }

                percentage[0] += 0.056; //increases percentage every frame
                percentageTxt.setText("Percent: " + Math.round(percentage[0]) + "%");//changes percentage on game screen every frame

                if (spike18.getX() <= -200) { //checks when the last spike passes the player 
                    leaderboard.add("Attempt: " + attempts[0] + " - " + Math.round(percentage[0]) + "%");
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

        stage.setTitle("Leledash");//change title of the stage

        Image icon = new Image("Drawing.png");//change icon of the stage
        stage.getIcons().add(icon);
        Scene menu = menuScreen(stage);//create menu scene
        stage.setScene(menu);
        stage.setResizable(false);//stops the stage from being resizeable
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}