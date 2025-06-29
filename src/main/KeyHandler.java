package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, spacePressed, sideWalking;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_E) {
            ePressed = true;
        }
        if(code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        updateSideWalking();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E) {
            ePressed = false;
        }
        if(code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        updateSideWalking();

        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void updateSideWalking() {
        boolean vertical = upPressed ^ downPressed;

        boolean horizontal = leftPressed ^ rightPressed;

        sideWalking = vertical && horizontal;
    }
}

