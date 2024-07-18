package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.Random;

public class PlayerInitializer {
    private MyPanel myPanel;
    private Random random;

    public PlayerInitializer(MyPanel myPanel) {
        this.myPanel = myPanel;
        this.random = new Random();
        this.initPlayer();
    }

    private void initPlayer(){

        int rowStart = this.random.nextInt(this.myPanel.getPanelMaze().getWidth());
        int colStart = this.random.nextInt(this.myPanel.getPanelMaze().getHeight());
        int point = this.myPanel.getPanelMaze().getMaze()[rowStart][colStart];
        while(point != 0){
            rowStart = this.random.nextInt(this.myPanel.getPanelMaze().getWidth());
            colStart = this.random.nextInt(this.myPanel.getPanelMaze().getHeight());
            point = this.myPanel.getPanelMaze().getMaze()[rowStart][colStart];
        }
        this.myPanel.setStart(new Rectangle(rowStart*this.myPanel.getWidthCell()+1,colStart*this.myPanel.getHeightCell()+1,this.myPanel.getWidthCell()-1,this.myPanel.getHeightCell()-1));
        this.myPanel.setPlayer(new Rectangle(rowStart*this.myPanel.getWidthCell()+10,colStart*this.myPanel.getHeightCell()+10,this.myPanel.getWidthCell()/2,this.myPanel.getHeightCell()/2));
        int rowFinish = this.random.nextInt(this.myPanel.getPanelMaze().getWidth());
        int colFinish = this.random.nextInt(this.myPanel.getPanelMaze().getHeight());
        point = this.myPanel.getPanelMaze().getMaze()[rowFinish][colFinish];
        if(rowFinish == rowStart && colFinish == colStart){
            point = 1;
        }
        while(point != 0){
            rowFinish = this.random.nextInt(this.myPanel.getPanelMaze().getWidth());
            colFinish = this.random.nextInt(this.myPanel.getPanelMaze().getHeight());
            point = this.myPanel.getPanelMaze().getMaze()[rowFinish][colFinish];
            if(rowFinish == rowStart && colFinish == colStart){
                point = 1;
            }
        }
        this.myPanel.setFinish(new Rectangle(rowFinish*this.myPanel.getWidthCell()+1,colFinish*this.myPanel.getHeightCell()+1,this.myPanel.getWidthCell()-1,this.myPanel.getHeightCell()-1));

    }
}
