package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyButton extends JButton implements ActionListener {
    private final int buttonWidth = 100;
    private final int buttonHeight = 40;
    private ArrayList<JButton> myButtons;
    private MyFrame myFrame;
    private MazeMovement move;

    public MyButton(MyFrame myFrame){
        this.myButtons = new ArrayList<>();
        this.myFrame = myFrame;
        this.setUpButtons();
        this.myFrame.setMyButtons(myButtons);
        this.move = new MazeMovement(this.myFrame.getMyPanel());
    }

    public void setUpButtons(){
        this.myButtons.add(new JButton("RESET"));
        this.myButtons.get(0).setPreferredSize(new Dimension(this.buttonWidth,this.buttonHeight));
        this.myButtons.get(0).setFocusable(false);
        this.myButtons.get(0).addActionListener(this);
        this.myButtons.add(new JButton("UP"));
        this.myButtons.get(1).setPreferredSize(new Dimension(this.buttonWidth,this.buttonHeight));
        this.myButtons.get(1).setFocusable(false);
        this.myButtons.get(1).addActionListener(this);
        this.myButtons.add(new JButton("DOWN"));
        this.myButtons.get(2).setPreferredSize(new Dimension(this.buttonWidth,this.buttonHeight));
        this.myButtons.get(2).setFocusable(false);
        this.myButtons.get(2).addActionListener(this);
        this.myButtons.add(new JButton("LEFT"));
        this.myButtons.get(3).setPreferredSize(new Dimension(this.buttonWidth,this.buttonHeight));
        this.myButtons.get(3).setFocusable(false);
        this.myButtons.get(3).addActionListener(this);
        this.myButtons.add(new JButton("RIGHT"));
        this.myButtons.get(4).setPreferredSize(new Dimension(this.buttonWidth,this.buttonHeight));
        this.myButtons.get(4).setFocusable(false);
        this.myButtons.get(4).addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.myButtons.get(0)){
            this.myFrame.getMyPanel().setMazesWon(0);
            this.myFrame.getMyPanel().newPaint();
        }
        else if(e.getSource() == this.myButtons.get(1)){
            this.move.playerMove(0,-this.myFrame.getMyPanel().getHeightCell());
        }
        else if(e.getSource() == this.myButtons.get(2)){
            this.move.playerMove(0,this.myFrame.getMyPanel().getHeightCell());
        }
        else if(e.getSource() == this.myButtons.get(3)){
            this.move.playerMove(-this.myFrame.getMyPanel().getWidthCell(),0);
        }
        else if(e.getSource() == this.myButtons.get(4)){
            this.move.playerMove(this.myFrame.getMyPanel().getWidthCell(),0);
        }
    }
}
