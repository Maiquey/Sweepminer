package ca.cmpt276.as3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.ui.AppBarConfiguration;
import ca.cmpt276.as3.databinding.ActivityMainBinding;
import android.widget.Button;

/*
    Activity for welcome screen
 */

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Button MainMenuButton = (Button) findViewById(R.id.button_main_menu);

        MainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainMenu.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

}