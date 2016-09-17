package com.app2.sgame.squareout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Game_Levels extends AppCompatActivity
{

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__levels);


        b1 = (Button) findViewById(R.id.btlevel1);
        b2 = (Button) findViewById(R.id.btlevel2);


        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent level1switch= new Intent(Game_Levels.this, Game_Instructions.class);
                startActivity(level1switch);
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent level2switch= new Intent(Game_Levels.this, Game_Instructions2.class);
                startActivity(level2switch);
            }
        });


    }
}
