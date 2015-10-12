package com.example.dai.tictactoc;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.ImageView;
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

        View.OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getResources().getResourceName(v.getId());
                int ind = name.charAt(name.length() - 1) - '1';
                int i = ind / 3;
                int j = ind % 3;
                if (game[i][j] != 0) {
                    return;
                }

                game[i][j] = player ? 2 : 1;
                buttons[i][j].setImageResource(player ? R.drawable.o : R.drawable.x);

                player = !player;
            }
        };

        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) gameTable.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = (ImageButton) row.getChildAt(j);
                buttons[i][j].setImageResource(R.drawable.empty);
                buttons[i][j].setScaleType(ImageView.ScaleType.FIT_XY);
                buttons[i][j].setAdjustViewBounds(true);
                buttons[i][j].setOnClickListener(listener);
            }
        }



    }


}
