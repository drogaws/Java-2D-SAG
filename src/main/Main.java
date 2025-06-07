package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("SAG Simulator");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); 
        window.pack(); // Makes sure winow and gamepanel are in sync

        //window.setLocationRelativeTo(null); // MAC Work
        window.setLocation(-1350, 500); // PC Work
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread(); // Starts thread
    }
}
