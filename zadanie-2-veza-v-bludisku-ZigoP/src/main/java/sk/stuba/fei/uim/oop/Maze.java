package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze {
    private int[][] maze;
    private final int height = 13;
    private final int width = 13;
    private int[] wallX;
    private int[] wallY;
    private Random rand;

    public Maze(){
        this.rand = new Random();
        this.maze = new int[this.height][this.width];
        generateMaze(this.maze);
        findWalls();
    }

    public int[][] getMaze() {
        return this.maze;
    }

    public int[] getWallX() {
        return wallX;
    }

    public int[] getWallY() {
        return wallY;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void generateMaze(int[][] maze) {

        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                maze[i][j] = 1;


        int row = this.rand.nextInt(this.height);
        while (row % 2 == 0) {
            row = this.rand.nextInt(this.height);
        }
        int col = this.rand.nextInt(this.width);
        while (col % 2 == 0) {
            col = this.rand.nextInt(this.width);
        }
        maze[row][col] = 0;
        recursion(row, col);

    }

    public void recursion(int row, int col) {
        Integer[] randDirs = generateRandomDirections();
        for (Integer randDir : randDirs) {

            switch (randDir) {
                case 1:
                    if (row - 2 <= 0)
                        continue;
                    if (this.maze[row - 2][col] != 0) {
                        this.maze[row - 2][col] = 0;
                        this.maze[row - 1][col] = 0;
                        recursion(row - 2, col);
                    }
                    break;
                case 2:
                    if (col + 2 >= this.width - 1)
                        continue;
                    if (this.maze[row][col + 2] != 0) {
                        this.maze[row][col + 2] = 0;
                        this.maze[row][col + 1] = 0;
                        recursion(row, col + 2);
                    }
                    break;
                case 3:
                    if (row + 2 >= this.height - 1)
                        continue;
                    if (this.maze[row + 2][col] != 0) {
                        this.maze[row + 2][col] = 0;
                        this.maze[row + 1][col] = 0;
                        recursion(row + 2, col);
                    }
                    break;
                case 4:
                    if (col - 2 <= 0)
                        continue;
                    if (this.maze[row][col - 2] != 0) {
                        this.maze[row][col - 2] = 0;
                        this.maze[row][col - 1] = 0;
                        recursion(row, col - 2);
                    }
                    break;
            }
        }

    }

    public Integer[] generateRandomDirections() {
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            randoms.add(i + 1);
        Collections.shuffle(randoms);

        return randoms.toArray(new Integer[4]);
    }

    public void findWalls(){
        int numOfWalls = 0;
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                if(maze[i][j] == 1){
                    numOfWalls++;
                }
        this.wallX = new int[numOfWalls];
        this.wallY = new int[numOfWalls];
        int currWall = 0;
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                if(maze[i][j] == 1){
                    this.wallX[currWall] = i;
                    this.wallY[currWall] = j;
                    currWall++;
                }
    }

}
