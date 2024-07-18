package sk.stuba.fei.uim.oop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    private MyFrame myFrame;
    private MazeMovement move;

    public MyKeyListener(MyFrame myFrame){
        this.myFrame = myFrame;
        this.myFrame.addKeyListener(this);
        this.move = new MazeMovement(this.myFrame.getMyPanel());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                this.move.playerMove(0,-this.myFrame.getMyPanel().getHeightCell());
                break;
            case KeyEvent.VK_DOWN:
                this.move.playerMove(0,this.myFrame.getMyPanel().getHeightCell());
                break;
            case KeyEvent.VK_LEFT:
                this.move.playerMove(-this.myFrame.getMyPanel().getWidthCell(),0);
                break;
            case KeyEvent.VK_RIGHT:
                this.move.playerMove(this.myFrame.getMyPanel().getWidthCell(),0);
                break;
        }
    }
}
