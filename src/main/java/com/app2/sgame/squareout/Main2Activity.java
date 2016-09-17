package com.app2.sgame.squareout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity
{

    RelativeLayout rl1;
    float x=0;
    float y=0;

    Point p = new Point();
    Point newPoint = new Point();
    boolean actionMoveFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rl1=(RelativeLayout)findViewById(R.id.relativelayout2);
        rl1.addView(new CircleTouch(this,null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.item1a:

                Intent switch2 = new Intent(Main2Activity.this, Splash_Screen.class);
                startActivity(switch2);

                return true;

            case R.id.item2a:


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Game has been paused");
                builder.setMessage("Game will resume in 5 seconds");
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();

                dlg.show();
                Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

                vibrator.vibrate(1000);

                final Timer t = new Timer();

                t.schedule(new TimerTask()
                {
                    public void run()
                    {
                        dlg.dismiss();
                        t.cancel();
                    }
                }, 5000);



                return true;

            case R.id.item3a:

                finish();
                startActivity(getIntent());

                return true;

            case R.id.item4a:

                Intent switch3 = new Intent(Main2Activity.this, Game_Instructions2.class);
                startActivity(switch3);

                return true;

            case R.id.item5a:

                /*finish();
                System.exit(0);*/

                Intent switch4 = new Intent(Main2Activity.this, Game_Levels.class);
                startActivity(switch4);

                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public class CircleTouch extends View
    {
        private Paint paint = new Paint();

        public List<Point> points = new ArrayList<>();

        private int[][] boardOccupanceRecordSheet = new int[10][15];
        private float radius=50;

        public CircleTouch(Context context, AttributeSet attrs)
        {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            for(Point p : points)
            {
                canvas.drawCircle(p.x, p.y,radius,paint);

            }

            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            x=event.getX();
            y=event.getY();

            int Total=0;

            paint.setAntiAlias(true);
            paint.setStrokeWidth(6f);
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:

                    boolean incirc = false;
                    p.x=(int)event.getX();
                    p.y=(int)event.getY();

                    for(Point existingPoint : points)
                    {
                        if(existingPoint.x <= p.x && existingPoint.x + 100 >= p.x && existingPoint.y <= p.y && existingPoint.y + 100 >= p.y)
                        {
                            incirc = true;
                        }
                    }

                    if(incirc == false)
                    {
                        points.add(new Point((p.x/100) *100, (p.y/100)*100));

                        points.size();

                        boardOccupanceRecordSheet[p.x/100][p.y/100] = 1;
                    }
                    else
                    {
                        nested_loop:
                        for(int i = 0; i < 10; i++)
                        {
                            for(int j = 0; j < 15; j++)
                            {

                                if(boardOccupanceRecordSheet[i][j] != 1)
                                {
                                    boardOccupanceRecordSheet[i][j] = 1;
                                    points.add(new Point(i*100, j*100));
                                    points.size();
                                    break nested_loop;
                                }
                            }
                        }
                    }


                    boolean flag = false;

                    for(int i = 0; i < 10; i++)
                    {
                        for (int j = 0; j < 15; j++)
                        {

                            if(boardOccupanceRecordSheet[i][j] != 1)
                            {

                                flag = true;
                            }

                        }

                    }


                    if(flag == false)
                    {
                        AlertDBox();
                    }


                    invalidate();
                    actionMoveFlag = false;

                    break;

                case MotionEvent.ACTION_MOVE:

                    actionMoveFlag = true;

                    break;

                case MotionEvent.ACTION_UP:

                    break;

                default:

                    actionMoveFlag = false;

            }

            invalidate();
            return true;
        }
    }

    private void AlertDBox()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Game Finish!");
        builder.setMessage("You have won");
        builder.setCancelable(true);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("Exit Game", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Thread thread = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            sleep(2000);
                        }
                        catch (InterruptedException e){}
                        finally
                        {
                            Intent switch4 = new Intent(Main2Activity.this, Splash_Screen.class);
                            startActivity(switch4);
                        }
                    }

                };
                thread.start();
            }
        });

        final AlertDialog dlg = builder.create();

        dlg.show();
    }


}
