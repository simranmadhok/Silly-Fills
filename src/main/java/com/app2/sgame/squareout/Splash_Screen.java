package com.app2.sgame.squareout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Splash_Screen extends AppCompatActivity
{
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        b1=(Button)findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent switch1=new Intent(Splash_Screen.this,Game_Levels.class);
                startActivity(switch1);
            }
        });
    }
}
