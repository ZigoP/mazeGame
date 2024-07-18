package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
public class MyPanel extends JPanel {
    private int widthCell = 40;
    private int heightCell = 40;
    private int mazesWon = 0;
    private JLabel label;
    private Maze panelMaze;
    private Rectangle start;
    private Rectangle finish;
    private Rectangle player;
    private Rectangle highlightBorder;
    private ArrayList<Rectangle> highlightedPath;
    private boolean isHighlighted = false;
    private boolean isClickable = true;


    public MyPanel(){
        this.highlightedPath = new ArrayList<>();
        this.panelMaze = new Maze();
        new PlayerInitializer(this);
        JPanel p = new JPanel(new FlowLayout());
        this.label = new JLabel("Mazes Won: "+this.getMazesWon());
        p.add(label);
        this.add(p);
        this.addMouseListener(new MyMouseListener(this));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        this.paintMaze(g2);
        this.paintPlayer(g2);
        this.label.setText("Mazes Won: "+this.getMazesWon());
        this.paintHighlighted(g2);
        g2.dispose();
    }

    private void paintMaze(Graphics2D g2){
        for(int row = 0; row < this.panelMaze.getWidth(); row++){
            for(int col = 0; col < this.panelMaze.getHeight(); col++){
                int currCell = this.panelMaze.getMaze()[row][col];
                if(currCell == 1){
                    g2.setColor(Color.BLACK);
                    g2.fillRect(row*this.widthCell,col*this.heightCell,this.widthCell,this.heightCell);
                }
                else if(currCell == 0){
                    g2.setColor(Color.WHITE);
                    g2.fillRect(row*this.widthCell,col*this.heightCell,this.widthCell,this.heightCell);
                }
            }
        }
        g2.setColor(Color.BLACK);
        for(int row = 0; row < this.panelMaze.getWidth(); row++){
            g2.drawLine(0,row*this.heightCell,(this.panelMaze.getWidth()-1)*this.widthCell,row*this.heightCell);
        }
        for(int col = 0; col < this.panelMaze.getHeight(); col++){
            g2.drawLine(col*this.widthCell,0,col*this.widthCell,(this.panelMaze.getHeight()-1)*this.heightCell);
        }
    }
    private void paintPlayer(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fill(this.start);
        g2.setColor(Color.BLUE);
        g2.fill(this.player);
        g2.setColor(Color.GREEN);
        g2.fill(this.finish);
    }

    private void paintHighlighted(Graphics2D g2){
        if(isHighlighted){
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(3));
            for(Rectangle rect : this.highlightedPath){
                g2.draw(rect);
            }
            if(this.highlightBorder!=null){
                g2.setColor(Color.yellow);
                g2.draw(this.highlightBorder);
            }
        }
        else{
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            for(Rectangle rect : this.highlightedPath){
                if(rect.x-10 == this.start.x && rect.y-10 == this.start.y){
                    g2.setColor(Color.RED);
                }
                if(rect.x-10 == this.finish.x && rect.y-10 == this.finish.y){
                    g2.setColor(Color.GREEN);
                }
                g2.draw(rect);
            }
        }
    }

    public void newPaint(){
        this.panelMaze = new Maze();
        new PlayerInitializer(this);
        this.isHighlighted = false;
        this.highlightedPath.clear();
        this.isClickable = true;
        repaint();
    }

}
