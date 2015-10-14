package com.example.dai.tictactoe;


import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TableLayout gameTable;
    ImageButton[][] buttons = new ImageButton[3][3];
    TextView status;
    int[][] game = new int[3][3];
    int totalSteps = 0;
    Button resetBtn;
    boolean gameOngoing = true;
    int player = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.dai.tictactoc.R.layout.activity_main);

        status = (TextView) findViewById(com.example.dai.tictactoc.R.id.status);
        gameTable = (TableLayout) findViewById(com.example.dai.tictactoc.R.id.gameTable);
        resetBtn = (Button) findViewById(com.example.dai.tictactoc.R.id.resetBtn);

        View.OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOngoing) {
                    Toast.makeText(getApplicationContext(), "Please hit reset to start a new game!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = getResources().getResourceName(v.getId());
                int ind = name.charAt(name.length() - 1) - '1';
                int i = ind / 3;
                int j = ind % 3;
                if (game[i][j] != 0) {
                    return;
                }

                ++totalSteps;
                game[i][j] = player;
                buttons[i][j].setImageResource(player == 1 ? com.example.dai.tictactoc.R.drawable.x : com.example.dai.tictactoc.R.drawable.o);
                if (totalSteps >= 5) {
                    checkWinner(player, i, j);
                }
                player = 3 - player;
                if (gameOngoing) {
                    status.setText("Player " + (player == 1 ? "X" : "O")+" playing"+totalSteps);
                }


            }
        };

        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) gameTable.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = (ImageButton) row.getChildAt(j);
                buttons[i][j].setImageResource(com.example.dai.tictactoc.R.drawable.empty);
                buttons[i][j].setScaleType(ImageView.ScaleType.FIT_XY);
                buttons[i][j].setAdjustViewBounds(true);
                buttons[i][j].setOnClickListener(listener);
            }
        }

    }


    private void checkWinner(int player, int i, int j) {

        int cnt = 0;
        // check horizontal
        for (int x = 0; x < 3; x++) {
            if (game[i][x] != player) {
                break;
            }
            ++cnt;
        }

        if (cnt == 3) {
            showWinner(player);
            return;
        }

        cnt = 0;
        // check vertical
        for (int x = 0; x < 3; x++) {
            if (game[x][j] != player) {
                break;
            }
            ++cnt;
        }

        if (cnt == 3) {
            showWinner(player);
            return;
        }

        //check diagonal


        if (game[1][1] == player &&
                (game[0][0] == player && game[2][2] == player ||
                game[0][2] == player && game[2][0] == player)) {
            showWinner(player);
            return;
        }

        if (totalSteps > 8) {
            gameOngoing = false;
            status.setText("You tied");
        }

    }

    private void showWinner(int player) {
        gameOngoing = false;
        status.setText("Player " + (player == 1 ? "x" : "o") + " won!");

    }

    public void resetGame(View view) {
        gameOngoing = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setImageResource(com.example.dai.tictactoc.R.drawable.empty);
                game[i][j] = 0;
            }
        }
        player = 1;
        totalSteps = 0;
        status.setText("Player X playing");
    }

}
