package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import androidx.navigation.ui.AppBarConfiguration;
import ca.cmpt276.as3.databinding.ActivityMainMenuBinding;
import ca.cmpt276.as3.model.OptionsData;

public class MainMenu extends AppCompatActivity {

    private static final String OPTION_1_DIMENSIONS = "4 x 6";
    private static final String OPTION_2_DIMENSIONS = "5 x 10";
    private static final String OPTION_3_DIMENSIONS = "6 x 15";
    private static final int OPTION_1_ROWS = 4;
    private static final int OPTION_1_COLS = 6;
    private static final int OPTION_2_ROWS = 5;
    private static final int OPTION_2_COLS = 10;
    private static final int OPTION_3_ROWS = 6;
    private static final int OPTION_3_COLS = 15;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainMenuBinding binding;
    private OptionsData optionsData;

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, MainMenu.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        optionsData = OptionsData.getInstance();

        setUpNavButtons();

        updateGameSettings();
    }

    private void setUpNavButtons() {

        Button PlayGameButton = (Button) findViewById(R.id.play_game_button);
        Button OptionsButton = (Button) findViewById(R.id.options_button);
        Button HelpButton = (Button) findViewById(R.id.help_button);

        PlayGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = GameActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });

        OptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Options.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });

        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = HelpScreen.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    private void updateGameSettings() {
        String dimensions = Options.getDimensions(this);
        int mines = Options.getMines(this);
        updateDimensions(dimensions);
        updateMines(mines);
    }

    private void updateDimensions(String dimensions) {

        switch (dimensions){
            case OPTION_1_DIMENSIONS:
                optionsData.setRows(OPTION_1_ROWS);
                optionsData.setColumns(OPTION_1_COLS);
                break;
            case OPTION_2_DIMENSIONS:
                optionsData.setRows(OPTION_2_ROWS);
                optionsData.setColumns(OPTION_2_COLS);
                break;
            case OPTION_3_DIMENSIONS:
                optionsData.setRows(OPTION_3_ROWS);
                optionsData.setColumns(OPTION_3_COLS);
                break;
        }
    }

    private void updateMines(int mines) {
        optionsData.setNumOfMines(mines);
    }

    @Override
    protected void onResume() {
        updateGameSettings();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}