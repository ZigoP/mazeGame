package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {
    private JFrame myFrame;
    private MyPanel myPanel;
    private ArrayList<JButton> myButtons;


    public MyFrame() throws HeadlessException {
        this.myFrame = new JFrame();
        this.myFrame.setSize(560,660);
        this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.myPanel = new MyPanel();
        this.myFrame.add("Center",this.myPanel);

        this.myButtons = new ArrayList<>();
        new MyButton(this);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.buttonPlacement(p,c);
        this.myFrame.add(p,BorderLayout.NORTH);

        this.myFrame.setFocusable(true);
        this.myFrame.addKeyListener(new MyKeyListener(this));
        this.myFrame.setVisible(true);
    }

    public void setMyButtons(ArrayList<JButton> myButtons) {
        this.myButtons = myButtons;
    }

    public MyPanel getMyPanel() {
        return myPanel;
    }

    public void buttonPlacement(JPanel p, GridBagConstraints c){
        c.gridx = 0;
        c.gridy = 0;
        p.add(this.myButtons.get(0),c);
        c.gridx = 1;
        c.gridy = 0;
        p.add(this.myButtons.get(1),c);
        c.gridx = 0;
        c.gridy = 1;
        p.add(this.myButtons.get(3),c);
        c.gridx = 1;
        c.gridy = 1;
        p.add(this.myButtons.get(2),c);
        c.gridx = 2;
        c.gridy = 1;
        p.add(this.myButtons.get(4),c);

    }
}
