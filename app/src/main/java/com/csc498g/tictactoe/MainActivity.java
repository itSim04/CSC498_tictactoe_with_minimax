package com.csc498g.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int[] board = new int[9]; // The board of the game
    int[][] win = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; // The winning states
    boolean player; // true is player 1 (yellow) and false is player 2 / ai (red)
    final ArrayList<ImageView> views = new ArrayList<>(); // The images
    boolean ai = true; // Whether 2 Players are playing
    MiniMax algorithm = new MiniMax(0, 1, -1, board, win); // The minimax algorithm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        views.add((ImageView) findViewById(R.id.piece0));
        views.add((ImageView) findViewById(R.id.piece1));
        views.add((ImageView) findViewById(R.id.piece2));
        views.add((ImageView) findViewById(R.id.piece3));
        views.add((ImageView) findViewById(R.id.piece4));
        views.add((ImageView) findViewById(R.id.piece5));
        views.add((ImageView) findViewById(R.id.piece6));
        views.add((ImageView) findViewById(R.id.piece7));
        views.add((ImageView) findViewById(R.id.piece8));

        start();

    }

    public void start(View v) {

        start();

    }

    public void ai(View v) {

        ai = !((Switch)v).isChecked();
        start();

    }

    public void start() {

        player = true;
        for(int i = 0; i < this.board.length; i++) {
            board[i] = -1;
        }
        views.forEach(t -> t.setImageResource(0));

        if(ai) {

            Random rd = new Random();
            int move = rd.nextInt(9);
            board[move] = player ? 0 : 1;
            animateEntrance(views.get(move));

            player = !player;
        }

    }

    public void tick(View v) {


        ImageView spot = (ImageView) v;
        int position = Integer.parseInt(spot.getTag().toString());

        if(board[position] == -1 && algorithm.isWin() == -1) {

            board[position] = player ? 0 : 1;
            animateEntrance(spot);
            player = !player;

            if(ai) {
                int move = algorithm.findBestMove();
                board[move] = player ? 0 : 1;
                animateEntrance(views.get(move));
                player = !player;
            }

            if(algorithm.isWin() != -1) {
                Toast.makeText(getApplicationContext(), "Player " + (algorithm.isWin() + 1) + " wins", Toast.LENGTH_LONG).show();
            } else if(Arrays.stream(board).noneMatch(t -> t == -1)) {
                Toast.makeText(getApplicationContext(), "Tie", Toast.LENGTH_LONG).show();
            }

        }



    }

    public void animateEntrance(ImageView spot) {

        spot.setImageResource(player ? R.drawable.red : R.drawable.yellow);
        spot.animate().rotation(3600).setDuration(500);

    }

}