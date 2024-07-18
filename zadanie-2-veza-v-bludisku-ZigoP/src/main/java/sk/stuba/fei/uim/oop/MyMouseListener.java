package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {
    private MyPanel myPanel;
    private MazeMovement move;

    public MyMouseListener(MyPanel myPanel){
        this.myPanel = myPanel;
        this.move = new MazeMovement(this.myPanel);
        myPanel.addMouseListener(this);
        myPanel.addMouseMotionListener(this);
    }

    private void clickOnPlayer(MouseEvent e){
        if(this.myPanel.isClickable()){
            int x1Player = this.myPanel.getPlayer().x;
            int x2Player = x1Player + this.myPanel.getPlayer().width;
            int y1Player = this.myPanel.getPlayer().y;
            int y2Player = y1Player + this.myPanel.getPlayer().height;
            if((e.getX() > x1Player && e.getX() < x2Player) && (e.getY() > y1Player && e.getY() < y2Player)){
                highlightPath(x1Player-10,y1Player-10);
                this.myPanel.setHighlighted(true);
                this.myPanel.setClickable(false);
            }
        }
    }

    private void moveOnClick(MouseEvent e){
        if(this.myPanel.isHighlighted()){
            this.myPanel.setHighlighted(false);
            int xClick;
            int yClick;
            int cnt;
            int x1Player = this.myPanel.getPlayer().x-10;
            int x2Player = x1Player + this.myPanel.getWidthCell();
            int y1Player = this.myPanel.getPlayer().y-10;
            int y2Player = y1Player + this.myPanel.getHeightCell();
            if(e.getX() > x1Player && e.getX() < x2Player){
                xClick = 0;
                yClick = e.getY();
                cnt = 0;
                if(yClick > y2Player){
                    while(yClick > y2Player){
                        yClick -= this.myPanel.getHeightCell();
                        cnt++;
                    }
                    if (inHighlighted(this.myPanel.getPlayer().x + xClick, this.myPanel.getPlayer().y + (cnt * this.myPanel.getHeightCell()))){
                        this.move.playerMove(xClick,cnt * this.myPanel.getHeightCell());
                    }

                }
                else {
                    while(yClick < y1Player){
                        yClick += this.myPanel.getHeightCell();
                        cnt++;
                    }
                    if (inHighlighted(this.myPanel.getPlayer().x + xClick, this.myPanel.getPlayer().y + (cnt * (-this.myPanel.getHeightCell())))){
                        this.move.playerMove(xClick,cnt * (-this.myPanel.getHeightCell()));
                    }

                }


            }else if(e.getY() > y1Player && e.getY() < y2Player){
                xClick = e.getX();
                yClick = 0;
                cnt = 0;
                if(xClick > x2Player){
                    while(xClick > x2Player){
                        xClick -= this.myPanel.getWidthCell();
                        cnt++;
                    }
                    if (inHighlighted(this.myPanel.getPlayer().x + (cnt * this.myPanel.getWidthCell()), this.myPanel.getPlayer().y + yClick)){
                        this.move.playerMove(cnt * this.myPanel.getWidthCell(),yClick);
                    }

                }
                else{
                    while(xClick < x1Player){
                        xClick += this.myPanel.getWidthCell();
                        cnt++;
                    }
                    if (inHighlighted(this.myPanel.getPlayer().x + (cnt * (-this.myPanel.getWidthCell())), this.myPanel.getPlayer().y + yClick))
                        this.move.playerMove(cnt * (-this.myPanel.getWidthCell()),yClick);
                }
            }
        }
        this.move.playerMove(0,0);
    }

    public void highlightPath(int xPlayer, int yPlayer){
        int x,y;
        for(int direction = 0; direction < 4; direction++){
            switch (direction){
                case 0:
                    x = xPlayer - this.myPanel.getWidthCell();
                    y = yPlayer;
                    while(!this.move.isWall(x+1, y+1)){

                        this.myPanel.getHighlightedPath().add(new Rectangle(x+10,y+10,this.myPanel.getPlayer().width,this.myPanel.getPlayer().height));
                        x -= this.myPanel.getWidthCell();
                    }
                    break;
                case 1:
                    x = xPlayer + this.myPanel.getWidthCell();
                    y = yPlayer;
                    while(!this.move.isWall(x+1,y+1)){

                        this.myPanel.getHighlightedPath().add(new Rectangle(x+10,y+10,this.myPanel.getPlayer().width,this.myPanel.getPlayer().height));
                        x += this.myPanel.getWidthCell();
                    }
                    break;
                case 2:
                    x = xPlayer;
                    y = yPlayer - this.myPanel.getHeightCell();
                    while(!this.move.isWall(x+1,y+1)){

                        this.myPanel.getHighlightedPath().add(new Rectangle(x+10,y+10,this.myPanel.getPlayer().width,this.myPanel.getPlayer().height));
                        y -= this.myPanel.getHeightCell();
                    }
                    break;
                case 3:
                    x = xPlayer;
                    y = yPlayer + this.myPanel.getHeightCell();
                    while(!this.move.isWall(x+1,y+1)){

                        this.myPanel.getHighlightedPath().add(new Rectangle(x+10,y+10,this.myPanel.getPlayer().width,this.myPanel.getPlayer().height));
                        y += this.myPanel.getHeightCell();
                    }
                    break;
            }
        }
        this.myPanel.repaint();
    }
    public boolean inHighlighted(int x, int y){
        for(Rectangle rect : this.myPanel.getHighlightedPath()){
            int x1 = rect.x-10;
            int x2 = x1 + this.myPanel.getWidthCell();
            int y1 = rect.y-10;
            int y2 = y1 + this.myPanel.getHeightCell();
            if((x >= x1 && x <= x2) && (y >= y1 && y <= y2)){
                return true;
            }
        }

        return false;
    }

    private void highlightCursor(MouseEvent e){
        if(this.myPanel.isHighlighted() && inHighlighted(e.getX(),e.getY())){
            for(Rectangle rect : this.myPanel.getHighlightedPath()){
                int x1 = rect.x-10;
                int x2 = x1 + this.myPanel.getWidthCell();
                int y1 = rect.y-10;
                int y2 = y1 + this.myPanel.getHeightCell();
                if((e.getX() > x1 && e.getX() < x2) && (e.getY() > y1 && e.getY() < y2)){
                    this.myPanel.setHighlightBorder(new Rectangle(x1,y1,this.myPanel.getWidthCell(),this.myPanel.getHeightCell()));
                    break;
                }
            }

        }
        else{
            this.myPanel.setHighlightBorder(new Rectangle());
        }
        this.myPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        clickOnPlayer(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        moveOnClick(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        highlightCursor(e);
    }
}
