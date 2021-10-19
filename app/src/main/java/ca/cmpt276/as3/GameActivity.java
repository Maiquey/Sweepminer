package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import ca.cmpt276.as3.databinding.ActivityGameBinding;
import ca.cmpt276.as3.model.Cell;
import ca.cmpt276.as3.model.GameManager;
import ca.cmpt276.as3.model.OptionsData;

/*
    Game UI class
    draws dynamic gameboard
    animates scanning for gems
 */

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

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;

            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        lockButtonSize();
        Cell clickedCell = gameManager.getCell(row, col);
        if (!gameManager.revealsMine(clickedCell) && !clickedCell.isScanned()){
            wobbleScan(col, row);
        }
        gameManager.cellClicked(col, row);
        updateGrid();
    }

    private void wobbleScan(int col, int row) {
        Button button;
        Cell cell;
        for (int i = 0; i < numCols; i++){
            button = buttons[row][i];
            cell = gameManager.getCell(row, i);
            if(!cell.isMineRevealed()) {
                button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.wobble));
            }
        }

        for (int i = 0; i < numRows; i++){
            button = buttons[i][col];
            cell = gameManager.getCell(i, col);
            if(!cell.isMineRevealed()) {
                button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.wobble));
            }
        }
    }

    private void updateGrid() {
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){

                Button button = buttons[row][col];

                Cell gameCell = gameManager.getCell(row, col);

                if (gameCell.isScanned()){
                    button.setText("" + gameCell.getNearbyHidden());
                }

                if (gameCell.isMineRevealed()){
                    revealMine(button);
                }

            }
        }
        updateTexts();
        if (gameManager.gameWon()){
            FragmentManager manager = getSupportFragmentManager();
            GameOverFragment gameOver = new GameOverFragment();
            gameOver.show(manager, "GameOverDialogue");
        }
    }

    private void revealMine(Button button) {
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gemstone);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void updateTexts() {
        mineCount.setText(getString(R.string.mine_count_found) +
                " " +
                gameManager.getNumOfMinesRevealed() +
                " " +
                getString(R.string.mine_count_of) +
                " " +
                gameManager.getTotalMines() +
                " " +
                getString(R.string.mine_count_gemstones));
        numOfScans.setText(getString(R.string.num_of_scans_used) +
                " " +
                gameManager.getNumOfScans());
    }

    private void lockButtonSize() {
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

}