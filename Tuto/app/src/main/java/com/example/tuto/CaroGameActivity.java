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

import model.Cell;
import model.Player;
import network.Client;

public class CaroGameActivity extends AppCompatActivity {

    private static final int BOARD_SIZE = 9;
    private Button[][] buttons;
    Client c = new Client();
    public Cell publicCell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caro_game);

        createBoard();
        Player opponentModel = null;
        if(getIntent().hasExtra("Opponent")){
            opponentModel = getIntent().getParcelableExtra("Opponent");
            System.out.println(opponentModel.getName());
        }
        publicCell = new Cell();
        publicCell.setRowIndex(-1);
        publicCell.setColIndex(-1);

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
                publicCell.setRowIndex(row);
                publicCell.setColIndex(col);
            }
        });

        return button;
    }

//    private void onCellClick(int row, int col) {
//
//        String instruction = c.receiveInstruction();
//        if(instruction.equals("ack")){
//            int imageSource = R.drawable.opng;
//            buttons[row][col].setBackground(getResources().getDrawable(imageSource));
//        }
//    }
    public void gameLoop() {
        while(true) {
            // receive instruction
            // receive turn
            String instruction = c.receiveInstruction(); // response from server
            while(instruction.equals("your-turn")){
                if(publicCell.getRowIndex() != -1 && publicCell.getColIndex() != -1){
                    c.sendCoordinate(publicCell);
                    String receivedInstruction = c.receiveInstruction();
                    if(receivedInstruction.equals("ack")){
                        int imageSource = R.drawable.opng;
                         buttons[publicCell.getRowIndex()][publicCell.getColIndex()].setBackground(getResources().getDrawable(imageSource));
                         break;
                    }
                    publicCell.setRowIndex(-1);
                    publicCell.setColIndex(-1);
                }

            }
            while(instruction.equals("enemy-turn")){
                Cell receivedObject = c.receiveCoordinate();
                int imageSource = R.drawable.xpng;
                buttons[receivedObject.getRowIndex()][publicCell.getColIndex()].setBackground(getResources().getDrawable(imageSource));
            }
        }
    }
}