package com.example.tuto;
// MainActivity.java
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CaroGameActivity extends AppCompatActivity {

    private static final int BOARD_SIZE = 9;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caro_game);

        createBoard();


    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setColumnCount(BOARD_SIZE);
        gridLayout.setRowCount(BOARD_SIZE);

        buttons = new Button[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col] = createButton(row, col);
                gridLayout.addView(buttons[row][col]);
            }
        }
    }

    private Button createButton(final int row, final int col) {
        Button button = new Button(this);

        // Calculate the size of each button based on the screen size and number of rows/columns
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        int buttonSize = Math.min(screenWidth, screenHeight) / BOARD_SIZE;

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = buttonSize;
        params.height = buttonSize;
        button.setLayoutParams(params);

        button.setText("");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCellClick(row, col);
            }
        });

        return button;
    }

    private void onCellClick(int row, int col) {

        // Handle the click event for the cell at position (row, col)
        // You can implement your game logic here

        // For example, you can set a mark (X or O) on the clicked cell
        int imageSource = R.drawable.opng;
        buttons[row][col].setBackground(getResources().getDrawable(imageSource));

        // Check for game completion, winner, etc.

        // Replace "X" with your game logic, and implement the complete game accordingly.
    }
    public void gameLoop() {
        while(true) {
            // receive instruction
            // receive turn
            String instruction = ""; // response from server
            while(instruction.equals("your-turn")){
                // o

            }
            while(instruction.equals("enemy-turn")){
                // x
            }
        }
    }
}