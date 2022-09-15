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

        views.put(0, findViewById(R.id.piece0));
        views.put(1, findViewById(R.id.piece1));
        views.put(2, findViewById(R.id.piece2));
        views.put(3, findViewById(R.id.piece3));
        views.put(4, findViewById(R.id.piece4));
        views.put(5, findViewById(R.id.piece5));
        views.put(6, findViewById(R.id.piece6));
        views.put(7, findViewById(R.id.piece7));
        views.put(8, findViewById(R.id.piece8));

    }

    public void animateEntrance(ImageView spot) {

        spot.setImageResource(player ? R.drawable.red : R.drawable.yellow);
        spot.animate().rotation(3600).setDuration(500);

    }

}