package com.fatihacar.catchthekenny;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText,timeText,textFinish;
    ImageView imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    Button next,newGame;
    int score;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;
    int sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView) findViewById(R.id.imageView);
        imageView1=(ImageView) findViewById(R.id.imageView1);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        imageView3=(ImageView) findViewById(R.id.imageView3);
        imageView4=(ImageView) findViewById(R.id.imageView4);
        imageView5=(ImageView) findViewById(R.id.imageView5);
        imageView6=(ImageView) findViewById(R.id.imageView6);
        imageView7=(ImageView) findViewById(R.id.imageView7);
        imageView8=(ImageView) findViewById(R.id.imageView8);
        next=(Button) findViewById(R.id.nextbtn);
        textFinish=(TextView) findViewById(R.id.textFinish);
        newGame=(Button) findViewById(R.id.button);
        imageArray=new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        score=0;
        next.setVisibility(View.INVISIBLE);
        for(ImageView image:imageArray){
            image.setVisibility(View.INVISIBLE);
        }

    }
public void startButton(final View view){
    hideImages();
    score=0;
    new CountDownTimer(15000, 1000) {
        @Override
        public void onTick(long l) {
            textFinish.setText("");
            timeText=(TextView) findViewById(R.id.textTime);
            timeText.setText("Time: "+l/1000);
            newGame.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onFinish() {
            timeText=(TextView) findViewById(R.id.textTime);
            newGame.setVisibility(View.VISIBLE);
            handler.removeCallbacks(runnable);
            for(ImageView image:imageArray){
                image.setVisibility(View.INVISIBLE);
            }

            sonuc=score;
            if(sonuc >= 10){

                timeText.setText("");
                newGame.setText("Reload New Game");
                next.setVisibility(View.VISIBLE);
            }
            else
            {
                textFinish.setText("Game Over...");
                timeText.setText("");
            }
        }
    }.start();
}
    public void nextGame(View view){
        sonraki();
    }
    public void increaseScore(View view){
        scoreText=(TextView) findViewById(R.id.textScore);
        score++;
        scoreText.setText("Score: "+score);
    }

    public void sonraki(){
        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }
    public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random r=new Random();
                int i=r.nextInt(8-0);
                imageArray[i].setVisibility(View.VISIBLE);
                int x=r.nextInt(1500-500);
                handler.postDelayed(this,x);
            }
        };
        handler.post(runnable);

    }
}
