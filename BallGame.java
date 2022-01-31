package canvas;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class BallGame extends JPanel implements Runnable {


    static boolean pauseFlag;

    Rectangle paddle = new Rectangle();


    int starWidth = 60;
    int meteorWidth = 67;
    int ufoWidth = 67;
    int starHeight = 60;
    int meteorHeight = 67;
    int ufoHeight = 60;
    int starX = (int) Math.floor(Math.random()*(768-0+1)+0);
    int starY = (int) Math.floor(Math.random()*(520-80+1)+80); // random int value between min:80 and max:680
    int meteorX = (int) Math.floor(Math.random()*(768-0+1)+0);
    int meteorY = (int) Math.floor(Math.random()*(520-80+1)+80);
    int ufoX = (int) Math.floor(Math.random()*(768-0+1)+0);
    int ufoY = (int) Math.floor(Math.random()*(520-80+1)+80);
    Rectangle starWrapper = new Rectangle(starX, starY -starHeight, starWidth, starHeight);
    Rectangle meteorWrapper = new Rectangle(meteorX, meteorY -meteorHeight, meteorWidth, meteorHeight);
    Rectangle ufoWrapper = new Rectangle(ufoX, ufoY -ufoHeight, ufoWidth, ufoHeight);

    private BufferedImage star;
    private BufferedImage ufo;
    private BufferedImage meteor;

    Thread animator;
    Setup canvas;

    Ball ball = new Ball(20, 20, 4, 1, 20);


    float width;
    float height;

    // Ball Size
    float radius = 20;
    float diameter = radius * 2;

    // to place ball each animation iteration
    float X = 100;
    float Y = 100;

    int delay = 100;

    static int amountR = 0;
    static int amountL = 0;
    static boolean play;




        @Override
        public void addNotify() {
            super.addNotify();

            animator = new Thread(this);
            animator.start();
        }

        @Override
        public void run(){

            paddle.setBounds(1024 / 2 - 120 / 2, 560 - 40,120,10);
            readImages();

            int time = 0;

            while (true) {

                width = 1024;
                height = 600;

                //// star case

                if(new Rectangle((int)ball.getX(), (int)ball.getY(), (int)radius, (int)radius).intersects(starWrapper)){
                    respawnWrapper(starWrapper);
                    starX = (int) starWrapper.getX();
                    starY = (int) starWrapper.getY();
                    Setup.scoreAmount += 1;
                    Setup.score.setText("Score: " + Setup.scoreAmount);
                }
                if(new Rectangle((int)ball.getX(), (int)ball.getY(), (int)radius, (int)radius).intersects(meteorWrapper)){
                    respawnWrapper(meteorWrapper);
                    meteorX = (int) meteorWrapper.getX();
                    meteorY = (int) meteorWrapper.getY();
                    ball.setDeltaY((float) (1.2*ball.getDeltaY()));
                    ball.setDeltaX((float) (1.2*ball.getDeltaX()));
                }
                if(new Rectangle((int)ball.getX(), (int)ball.getY(), (int)radius, (int)radius).intersects(ufoWrapper)){
                    respawnWrapper(ufoWrapper);
                    ufoX = (int) ufoWrapper.getX();
                    ufoY = (int) ufoWrapper.getY();
                    if(Setup.remainingLives >0) {
                        Setup.remainingLives -= 1;
                        Setup.lives.setText("Lives: " + Setup.remainingLives);
                    }else if(Setup.remainingLives == 0){

                    }
                }

                // ball and paddle collision

                if(new Rectangle((int)ball.getX(), (int)ball.getY(), (int)radius, (int)radius).intersects(paddle)){
                    ball.reverseDeltaY();
                   // ball.move();
                    // continue;
                    Setup.scoreAmount += 1;
                    Setup.score.setText("Score: " + Setup.scoreAmount);
                }

                if (ball.getX() - radius < 0) {
                    ball.reverseDeltaX();

                } else if (ball.getX() + radius > width) {

                    ball.reverseDeltaX();
                }

                if (ball.getY() - radius < 0) {
                    ball.reverseDeltaY();


                } else if (ball.getY()  >= 600) {
                    BallGame.play = false;
                    ball = new Ball(20, 20, 4, 1, 20);

                    if(Setup.remainingLives >0) {
                        Setup.remainingLives -= 1;
                        Setup.lives.setText("Lives: " + Setup.remainingLives);
                    }
               }

                if(Setup.remainingLives == 0){
                    break;
                }

                /*else if(new Rectangle((int)ball.getX(),
                        (int)ball.getY(), (int)radius, (int)radius).intersects(0,600,1024,1)){
                    ball.reverseDeltaY();
                    if(canvas!=null) {
                        canvas.setLives();
                    }
                    System.out.println("HIT BOTTOM WHEN Y=" + ball.getY());
                }*/

                if(BallGame.pauseFlag){
                    ball = new Ball(20, 20, 4, 1, 20);
                    BallGame.pauseFlag=!pauseFlag;
                    ball.setDeltaX(1);
                    ball.setDeltaY(1);
                }


                if (BallGame.amountR == 1) {
                    paddle.setLocation((int)paddle.getX() + 30, (int)paddle.getY());
                    BallGame.amountR = 0;
                }
                if (BallGame.amountL == 1) {
                    paddle.setLocation((int)paddle.getX() - 30, (int)paddle.getY());
                    BallGame.amountL = 0;
                }

                if (BallGame.play) {
                    ball.move();
                    if((60 - time / 1000) > 0) {
                        Setup.remainingTime.setText("Remaining Time: " + (60 - time / 1000));
                    }else{
                        Setup.remainingTime.setText("Remaining Time: " + 60);
                        Setup.levelAmount += 1;
                        Setup.level.setText("Level: " + Setup.levelAmount);
                        ball.setDeltaX((float) (1.5*ball.getDeltaX()));
                        ball.setDeltaY((float) (1.5*ball.getDeltaY()));
                        time = 0;
                    }
                }

                repaint();

                try {
                    time+=5;
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }


            }
        }

        // change objectX,Y and its wrappers X and Y
        public void respawnWrapper(Rectangle wrapper){
            wrapper.setBounds((int) Math.floor(Math.random()*(768-0+1)+0),(int) Math.floor(Math.random()*(520-80+1)+80), (int) wrapper.getWidth(), (int) wrapper.getHeight());
        }

        public void readImages() {

            try {
                meteor = ImageIO.read(new File("C:\\Users\\Asus\\Desktop\\project\\meteorite.png"));
                star = ImageIO.read(new File("C:\\Users\\Asus\\Desktop\\project\\star.png"));
                ufo = ImageIO.read(new File("C:\\Users\\Asus\\Desktop\\project\\ufoship.png"));

                starY -= star.getHeight();
                meteorY -= meteor.getHeight();
                ufoY -= ufo.getHeight();


            } catch (Exception ex) {

            }
        }


        public void paintComponent (Graphics g){
            super.paintComponent(g);
            g.setColor(Color.RED);
            drawPaddle(g);
            g.fillOval((int) (ball.getX() - radius), (int) (ball.getY() - radius), (int) diameter, (int) diameter);
            g.drawImage(meteor, meteorX, meteorY, this);
            g.drawImage(star, starX, starY, this);
            g.drawImage(ufo, ufoX, ufoY, this);
            paddle.setBounds((int) paddle.getX(), (int) paddle.getY(), 120, 10);
        }


        public void drawPaddle(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.fillRect((int)paddle.getX(), (int)paddle.getY(), paddle.width, paddle.height);
        }


        public void init() {

            canvas = new Setup();
            JButton startButton = new JButton("Start");
            startButton.setFocusable(false);
            startButton.setBounds(512 - 95 / 2, 690, 95, 30);
            startButton.addActionListener(e -> {
                this.play = !this.play;
                BallGame.pauseFlag = true;
                repaint();
            });

            canvas.mainFrame.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                        amountR = 1;
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                        amountL = 1;
                    }

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

            canvas.mainFrame.add(startButton);
            canvas.gamePanel.add(new BallGame());
        }

    }