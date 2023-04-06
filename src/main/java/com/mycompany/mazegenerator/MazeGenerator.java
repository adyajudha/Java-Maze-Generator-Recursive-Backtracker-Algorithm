/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mazegenerator;

import java.util.*;
/**
 *
 * @author adity
 */
public class MazeGenerator {
    private int width;
    private int height;
    private int[][] maze;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new int[height][width];
        generateMaze(0, 0);
    }

    private void generateMaze(int row, int col) {
        maze[row][col] = 1;
        List<int[]> neighbors = getNeighbors(row, col);
        while (!neighbors.isEmpty()) {
            int[] randomNeighbor = neighbors.remove(new Random().nextInt(neighbors.size()));
            int nRow = randomNeighbor[0];
            int nCol = randomNeighbor[1];
            if (maze[nRow][nCol] == 0) {
                maze[(nRow + row) / 2][(nCol + col) / 2] = 1;
                generateMaze(nRow, nCol);
            }
            neighbors = getNeighbors(row, col);
        }
    }

    private List<int[]> getNeighbors(int row, int col) {
        List<int[]> neighbors = new ArrayList<>();
        if (row - 2 >= 0 && maze[row - 2][col] == 0) {
            neighbors.add(new int[]{row - 2, col});
        }
        if (col - 2 >= 0 && maze[row][col - 2] == 0) {
            neighbors.add(new int[]{row, col - 2});
        }
        if (row + 2 < height && maze[row + 2][col] == 0) {
            neighbors.add(new int[]{row + 2, col});
        }
        if (col + 2 < width && maze[row][col + 2] == 0) {
            neighbors.add(new int[]{row, col + 2});
        }
        Collections.shuffle(neighbors);
        return neighbors;
    }

    public void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
    MazeGenerator maze = new MazeGenerator(10, 10);
    maze.printMaze();
    }
}
