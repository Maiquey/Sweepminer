package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import ca.cmpt276.as3.databinding.ActivityGameBinding;
import ca.cmpt276.as3.model.Cell;
import ca.cmpt276.as3.model.GameManager;
import ca.cmpt276.as3.model.OptionsData;

public class GameActivity extends AppCompatActivity {

    private int numRows;
    private int numCols;
    private OptionsData optionsData;
    private GameManager gameManager;
    private TextView mineCount;
    private TextView numOfScans;

    private Button buttons[][];

    private AppBarConfiguration appBarConfiguration;
    private ActivityGameBinding binding;

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        optionsData = OptionsData.getInstance();

        gameManager = new GameManager(optionsData.getRows(),
                                        optionsData.getColumns(),
                                        optionsData.getNumOfMines());

        numRows = optionsData.getRows();
        numCols = optionsData.getColumns();

        mineCount = findViewById(R.id.text_mines_found);
        numOfScans = findViewById(R.id.text_num_of_scans);

        buttons = new Button[numRows][numCols];

        populateGrid();
        updateTexts();
    }

    private void populateGrid() {

        TableLayout table = (TableLayout) findViewById(R.id.table_cell_grid);

        for (int row = 0; row < numRows; row++){

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < numCols; col++){

                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                Cell gameCell = gameManager.getCell(row, col);
                if (gameCell.isScanned()){
                    button.setText("" + gameCell.getNearbyHidden());
                }

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;

                if (gameCell.isMineRevealed()){
                    button.setBackgroundResource(R.drawable.ic_baseline_auto_graph_24);
                }

            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        gameManager.cellClicked(col, row);
        updateGrid();
    }

    private void updateGrid() {
        if (gameManager.gameWon()){
            gameManager.revealGrid();
            Toast.makeText(this, "Game won", Toast.LENGTH_SHORT).show();
        }
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){

                Button button = buttons[row][col];

                Cell gameCell = gameManager.getCell(row, col);

                if (gameCell.isScanned()){
                    button.setText("" + gameCell.getNearbyHidden());
                }

                if (gameCell.isMineRevealed()){
                    button.setBackgroundResource(R.drawable.ic_baseline_auto_graph_24);
                }

            }
        }
        updateTexts();
    }

    private void updateTexts() {
        mineCount.setText("Found " + gameManager.getNumOfMinesRevealed() + " of " + gameManager.getTotalMines() + " mines");
        numOfScans.setText("Number of scans used: " + gameManager.getNumOfScans());
    }


}