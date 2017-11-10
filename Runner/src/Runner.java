import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;

import static edu.princeton.cs.introcs.StdDraw.*;

public class Runner {
    private static boolean spaceBarPressed = false;
    private static boolean EnterPressed = false;
    private static int canvasWidth = 1400;
    private static int canvasHeight = 800;
    private static String background2 = "Space.jpg";
    private static String background1 = "clouds.jpg";
    private static String background = "Shenandoah-Valley-Region.jpg";
    public static int points = 0;
    public static int highScore = 0;



    public static void main(String[] args) {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, 1.0);
        StdDraw.setYscale(0, 1.0);
        eagle bWCEagle = new eagle(0.5, 0.5, true);
        Font font = new Font("Arial", Font.BOLD, 70);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.5, "Attack of The Jim");
        Font smallFont = new Font("Arial", Font.PLAIN, 40);
        StdDraw.setFont(smallFont);
        StdDraw.text(0.5, 0.3, "Press Space to start");
        Enemy HiveMind[] = new Enemy[5];
        StdDraw.enableDoubleBuffering();

        while (true) {
            if (getSpaceBarPress()) {

                for(int i = 0 ; i < HiveMind.length ; i ++){
                    boolean leftJim = false;
                    if(Math.random() > .15){
                        leftJim = true;
                    }
                    HiveMind[i] = new Enemy( 0.1, Math.random(), leftJim);
                }
                boolean collided = false;
                while (!collided) {
                   int numberOfJims = 1;
                    if(points > 300){ numberOfJims+=1;}
                    if(points > 700){numberOfJims+=1;}
                    if(points > 2000){numberOfJims+=1;}
                    if(points > 4000){numberOfJims+=1;}
                    if(points > 6000){numberOfJims+=1;}
                    for(int i =0; i<numberOfJims; i++){
                        HiveMind[i].moveLoop();
                        collided =  HiveMind[i].collided(bWCEagle);
                        if(collided){
                           break;
                        }
                    }


                    if (getSpaceBarPress()) {
                        bWCEagle.moveUp();
                        //Jim.moveRight();


                    } else {
                        bWCEagle.moveDown();
                        //Jim.moveLeft();

                    }

                    pause(20);
                    BackgroundScene();
                    points = points + 1;
                    StdDraw.text(0.8, 0.9, "Score: " + points);

                }
                Enemy.Loss();
                while(!getEnterPress()){}
            }
        }
    }



    /**
     * These methods fired when a key is pressed don't worry about them for now.
     */

    public static void BackgroundScene(){
        StdDraw.show();
        if(points < 1000){
            StdDraw.picture(0.3, 0.45, background);
        }else if(points < 2500){
            StdDraw.picture(0.5, 0.6, background1);
        }else{ StdDraw.picture(0.25, 0.75, background2);}
    }

    public static boolean getEnterPress() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
            Runner.EnterPressed = false;
            return true;
        }
        return false;
    }


    public static boolean getSpaceBarPress(){
        if(Runner.spaceBarPressed  || StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
            Runner.spaceBarPressed  = false;
            return true;
        }
        return false;
    }

}
