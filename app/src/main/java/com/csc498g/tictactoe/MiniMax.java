package com.csc498g.tictactoe;

public class MiniMax {

    final int aiMark;
    final int playerMark;
    final int emptyMark;
    int[] board;
    final int[][] winConditions;


    public MiniMax(int playerMark, int aiMark, int emptyMark, int[] board, int[][] winConditions) {

        this.playerMark = playerMark;
        this.aiMark = aiMark;
        this.emptyMark = emptyMark;
        this.board = board;
        this.winConditions = winConditions;
    }

    public int findBestMove() {

        int bestValue = -1000;
        int bestMove = -1;

        for (int i = 0; i < this.board.length; i++) {
                if (this.board[i] == emptyMark) {

                    this.board[i] = aiMark;
                    int moveValue = generate(0, false);

                    this.board[i] = emptyMark;

                    if (moveValue > bestValue) {
                        bestMove = i;
                        bestValue = moveValue;
                    }
                }
                
        }

        return bestMove;
    }

    private int generate(int depth, boolean isAi) {

        int score = evaluate();

        if (score == 10) {
            return score - depth;
        }
        if (score == -10) {
            return score + depth;
        }

        if (!hasMovesLeft())
            return 0;

        if (isAi) {

            int bestVal = -10;
            for (int i = 0; i < this.board.length; i++) {

                    if (this.board[i] == emptyMark) {
                        this.board[i] = aiMark;
                        bestVal = Math.max(generate(depth + 1, false), bestVal);
                        this.board[i] = emptyMark;
                    }

            }

            return bestVal;

        }
        int bestVal = 10;
        for (int i = 0; i < this.board.length; i++) {

                if (this.board[i] == emptyMark) {
                    this.board[i] = playerMark;
                    bestVal = Math.min(generate(depth + 1, true), bestVal);
                    this.board[i] = emptyMark;
                }

        }

        return bestVal;
    }

    private int evaluate() {

        if (isWin() != emptyMark) {
            return +10;
        } else if (isWin() != emptyMark) {
            return -10;
        } else {
            return 0;
        }

    }

    public int isWin() {

        for (int[] i: this.winConditions) {
            if (this.board[i[0]] == this.board[i[1]] && this.board[i[0]] == this.board[i[2]] && this.board[i[0]] != this.emptyMark)
                return this.board[i[0]];
        }
        return emptyMark;

    }

    private boolean hasMovesLeft() {

        for (int j : this.board) {

            if (j == this.emptyMark) {
                return true;
            }

        }
        return false;

    }




}
