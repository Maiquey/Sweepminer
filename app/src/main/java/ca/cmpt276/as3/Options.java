package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.navigation.ui.AppBarConfiguration;
import ca.cmpt276.as3.databinding.ActivityOptionsBinding;

/*
    Option settings activity
    saves user preference settings
 */

public class Options extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityOptionsBinding binding;

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, Options.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        createGridOptionButtons();
        createMineOptionButtons();

    }

    private void createGridOptionButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_grid_size);

        String[] gridOptions = getResources().getStringArray(R.array.grid_dimensions);
        //create the buttons
        for (int i = 0; i < gridOptions.length; i++){
            String dimensions = gridOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText("" + dimensions);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveDimensions(dimensions);
                }
            });

            group.addView(button);
            button.setTextColor(getColor(android.R.color.white));

            //default button
            if (dimensions.equals(getDimensions(this))){
                button.setChecked(true);
            }
        }
    }

    private void saveDimensions(String dimensions) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Grid Dimensions", dimensions);
        editor.apply();
    }

    static public String getDimensions(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);

        String defaultDimensions = context.getResources().getString(R.string.default_grid_dimensions);

        return prefs.getString("Grid Dimensions", defaultDimensions);
    }

    private void createMineOptionButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_mine_amount);

        int[] mineOptions = getResources().getIntArray(R.array.mine_amounts);
        //create the buttons
        for (int i = 0; i < mineOptions.length; i++){
            int mines = mineOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText("" + mines);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveMines(mines);
                }
            });

            group.addView(button);
            button.setTextColor(getColor(android.R.color.white));

            //default button
            if (mines == getMines(this)){
                button.setChecked(true);
            }
        }
    }

    private void saveMines(int mines) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Mine Amount", mines);
        editor.apply();
    }

    static public int getMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);

        int defaultMines = context.getResources().getInteger(R.integer.default_mine_amount);

        return prefs.getInt("Mine Amount", defaultMines);
    }
}