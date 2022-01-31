package canvas;

import javax.swing.*;
import java.awt.*;

public class Setup {

    boolean pause = false;

    public static JLabel lives;
    public static JLabel score;
    public static JLabel level;
    public static JLabel remainingTime;

    JPanel infoPanel;
    JFrame mainFrame;
    JPanel startPanel;
    JPanel gamePanel;

    public static int levelAmount = 1;
    public static int scoreAmount = 0;
    public static int remainingLives = 3;

    public Setup(){
        init();
    }


    public void init(){

        lives = new JLabel();
        lives.setText("Lives: 3");
        lives.setBounds(5,5,100,20);
        score = new JLabel();
        score.setText("Score: 0");
        score.setBounds(5,20,100,20);
        level = new JLabel();
        level.setText("Level: 1");
        level.setBounds(5,35,100,20);
        remainingTime = new JLabel();
        remainingTime.setText("Time Remaining: ");
        remainingTime.setBounds(5,50,200,20);

        JLabel[] jLabels = {lives, score, level, remainingTime};



        //label.setBounds(100, 100, 75, 75);



        //startButton.setVerticalAlignment(JButton.TOP);
        //startButton.setHorizontalAlignment(JButton.LEFT);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.lightGray);
        infoPanel.setBounds(0, 0, 1024, 80);
        infoPanel.setLayout(new BorderLayout());


        /* The main gain animation panel. */
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setBounds(0, 80, 1024, 600);
        gamePanel.setLayout(new BorderLayout());


        startPanel = new JPanel();
        startPanel.setBackground(Color.lightGray);
        startPanel.setBounds(0, 680, 1024, 88);
        startPanel.setLayout(new BorderLayout());


        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setSize(1024,768);
        mainFrame.setVisible(true);
        for(JLabel label : jLabels)
            mainFrame.add(label);
        mainFrame.add(gamePanel);
        mainFrame.add(startPanel);
        mainFrame.add(infoPanel);
        mainFrame.setBackground(Color.GREEN);
    }
}