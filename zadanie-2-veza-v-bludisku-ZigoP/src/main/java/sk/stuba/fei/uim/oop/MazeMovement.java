package sk.stuba.fei.uim.oop;

import java.awt.*;

public class MazeMovement {
    private MyPanel myPanel;

    public MazeMovement(MyPanel myPanel){
        this.myPanel = myPanel;
    }

    public void playerMove(int x, int y){
        this.myPanel.setClickable(true);
        this.myPanel.getHighlightedPath().clear();
        int newX = this.myPanel.getPlayer().x+x;
        int newY = this.myPanel.getPlayer().y+y;
        if(!isWall(newX, newY)){
            this.myPanel.setPlayer(new Rectangle(newX,newY,this.myPanel.getPlayer().width,this.myPanel.getPlayer().height));
            this.myPanel.repaint();

            if(ifEnd(newX,newY)){
                this.myPanel.setMazesWon(this.myPanel.getMazesWon()+1);
                this.myPanel.newPaint();
            }
        }

    }

    public boolean ifEnd(int x, int y){
        int x1 = this.myPanel.getFinish().x;
        int x2 = x1+this.myPanel.getWidthCell();
        int y1 = this.myPanel.getFinish().y;
        int y2 = y1+this.myPanel.getHeightCell();
        return (x >= x1 && x <= x2) && (y >= y1 && y <= y2);
    }

    public boolean isWall(int xP, int yP){
        for(int i = 0; i < this.myPanel.getPanelMaze().getWallX().length;i++){
            int wallX1 = this.myPanel.getPanelMaze().getWallX()[i]*this.myPanel.getWidthCell();
            int wallX2 = wallX1 + this.myPanel.getWidthCell();
            int wallY1 = this.myPanel.getPanelMaze().getWallY()[i]*this.myPanel.getHeightCell();
            int wallY2 = wallY1 + this.myPanel.getHeightCell();
            if((xP >= wallX1 && xP <= wallX2) && (yP >= wallY1 && yP <= wallY2)){
                return true;
            }

        }
        return false;
    }

}
