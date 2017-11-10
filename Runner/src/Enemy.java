import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Enemy {
    private double xPosition;
    private double yPosition;
    private double xCollisionRange = 0.075;
    private double yCollisionRange = 0.15;
    private final double rate =0.02;
    private boolean up;
    private static String monster = "s_monster.png";
    private static boolean leftMover;

    public Enemy(double x, double y, boolean inLeftMover){

        xPosition = x;
        yPosition =  y;
        leftMover  = inLeftMover;
        drawJim();

    }

    public double getxPosition(){
        return xPosition;
    }

    public double getyPosition(){
        return yPosition;
    }

    public void  drawJim(){ StdDraw.picture(getxPosition(), getyPosition(), monster);
    }

    public  boolean collided(eagle hawk){
        //Do we have close x values
       boolean xCollide = Math.abs(xPosition - hawk.getxPosition()) < xCollisionRange;
       boolean yCollide =  Math.abs(yPosition - hawk.getyPosition()) < yCollisionRange;
       if( xCollide && yCollide) {
           return true;
       }else{return false;}
    }

    public void moveRightLoop(){
        if(xPosition < 0.9 ){
            xPosition += 0.01;
        }else {xPosition = 0.0;
        yPosition = Math.random();
        }
        drawJim();
    }




    public void moveLeftLoop(){
        if(xPosition > 0.1 ){
            xPosition -= 0.02;
        }else {xPosition = 1.0;
            yPosition = Math.random();
        }
        drawJim();
    }
    public void moveLeftLoopFast(){
        if(xPosition > 0.1 ){
            xPosition -= 0.03;
        }else {xPosition = 1.0;
            yPosition = Math.random();
        }
        drawJim();
    }

    public void moveRight(){
        if(xPosition < 0.9 ) {
            xPosition += 0.02;
        }
        drawJim();
    }

    public void moveLeft(){
        if(xPosition > 0.1 ){
            xPosition -= 0.03;
        }
        drawJim();
    }
    public static void Loss(){
        if(Runner.points > Runner.highScore) Runner.highScore = Runner.points;
        StdDraw.clear(Color.LIGHT_GRAY);
        Font smallFont = new Font("Arial", Font.PLAIN, 40);
        StdDraw.setFont(smallFont);
        StdDraw.text(0.5, 0.6, "Score: " + Runner.points);
        StdDraw.text(0.5, 0.5, "High Score: " + Runner.highScore);
        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.8, "You have been Consumed...");
        StdDraw.text(0.5, 0.2, "Press Enter then Space to restart");
        Runner.points = 0;
        StdDraw.show();


    }

    public void moveLoop() {
        if(leftMover){
            moveLeftLoop();
        }else{
            moveRightLoop();
        }
    }
}

