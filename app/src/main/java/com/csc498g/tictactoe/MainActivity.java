package com.csc498g.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    final int[] board = new int[9]; // The board of the game
    int[][] win = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; // The winning states
    boolean player; // true is player 1 (yellow) and false is player 2 / ai (red)
    final HashMap<Integer, ImageView> views = new HashMap<>(); // The images
    boolean ai = true; // Whether 2 Players are playing
    MiniMax algorithm = new MiniMax(0, 1, -1, board, win); // The minimax algorithm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}