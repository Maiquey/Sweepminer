package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.navigation.ui.AppBarConfiguration;
import ca.cmpt276.as3.databinding.ActivityHelpScreenBinding;

public class HelpScreen extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHelpScreenBinding binding;

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, HelpScreen.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHelpScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        //derived from https://www.geeksforgeeks.org/how-to-add-hyperlink-to-textview-in-android/
        TextView link1 = findViewById(R.id.text_website_link);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = findViewById(R.id.text_image_link);
        link2.setMovementMethod(LinkMovementMethod.getInstance());
    }


}