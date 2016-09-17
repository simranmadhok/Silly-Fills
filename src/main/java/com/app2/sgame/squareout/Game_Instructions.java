package com.app2.sgame.squareout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Game_Instructions extends AppCompatActivity
{
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__instructions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b2=(Button)findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent switch1=new Intent(Game_Instructions.this,MainActivity.class);
                startActivity(switch1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.game_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.gitem1:

                Intent switch2=new Intent(Game_Instructions.this,Splash_Screen.class);
                startActivity(switch2);

                return true;

            case R.id.gitem2:

                finish();
                System.exit(0);

                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
