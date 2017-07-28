package com.tqrg.physalia.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class ScrollingActivity extends AppCompatActivity {

    public static final boolean interactive = false;
    int fabClicks = 0;
    int button1Clicks = 0;
    int button2Clicks = 0;
    int button3Clicks = 0;
    int backButtonPresses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(interactive){
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fabClicks++;
                    Snackbar.make(view, "Fab clicked " + fabClicks + " times.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            Button button1 = (Button) findViewById(R.id.button_1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button1Clicks++;
                    Snackbar.make(view, "Button1 clicked " + button1Clicks + " times.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            Button button2 = (Button) findViewById(R.id.button_2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button2Clicks++;
                    Snackbar.make(view, "Button2 clicked " + button2Clicks + " times.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            Button button3 = (Button) findViewById(R.id.button_3);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button3Clicks++;
                    Snackbar.make(view, "Button3 clicked " + button3Clicks + " times.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(interactive){
            backButtonPresses++;
            View view = getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(view, "Back button pressed " + backButtonPresses + " times.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

}
