package com.example.dai.tictactoc;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    TableLayout gameTable;
    ImageButton[][] buttons = new ImageButton[3][3];
    int[][] game = new int[3][3];
    boolean player;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameTable = (TableLayout) findViewById(R.id.gameTable);

        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) gameTable.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = (ImageButton) row.getChildAt(j);
                buttons[i][j].setImageResource(R.drawable.empty);
            }
        }

        



    }


}
