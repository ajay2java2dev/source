package com.dev.sample.puzzles;

/**
 * Created by AjayMenon on 2/14/2017.
 * <p>
 * A backtracking recursion based algorithm.
 *
 * Maze is one of the common questions asked in interview sessions to check a candidates understanding of a Backtracking
 * implementation. http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
 *
 * A typical maze question, assumptions can be made as follows:
 * 1) Maze is 2 * 2 array matrix to be considered.
 * 2) Either the input is given or needs to constructed.
 * 3) Source can be considered to top-left corner and destination the bottom right corner.
 * 4) A backtracking algorithm is suitable for this problem. Move horizontally and then move vertically if horizontal
 * solution could not be created.
 *
 */
public class Maze {
    final int N = 4; // final destination is (3,3) starting from node (0,0)

    public static void main(String[] args) {
        Maze maze = new Maze();

        int[][] a = {
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        maze.solveMaze(a);
    }

    void printSolution(int sol[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    boolean isSafe(int[][] maze, int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N
                && maze[x][y] == 1);
    }

    boolean solveMazeByBacktracking(int[][] maze, int x, int y, int[][] sol) {
        if (x == N - 1 && y == N - 1) {
            sol[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x, y)) {
            sol[x][y] = 1;

            if (solveMazeByBacktracking(maze, x, y + 1, sol)) {
                return true;
            }

            if (solveMazeByBacktracking(maze, x + 1, y, sol)) {
                return true;
            }

            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    boolean solveMaze(int[][] maze) {
        int sol[][] =
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };

        if (!solveMazeByBacktracking(maze, 0, 0, sol)) {
            System.out.println("Maze.solveMaze : Solution doesn't exists");
            return false;
        }

        printSolution(sol);

        return true;
    }
}
