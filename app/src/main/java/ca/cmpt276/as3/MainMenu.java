package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as3.databinding.ActivityMainMenuBinding;

public class MainMenu extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainMenuBinding binding;

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

        setUpNavButtons();
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        this.finishAffinity();
    }
}