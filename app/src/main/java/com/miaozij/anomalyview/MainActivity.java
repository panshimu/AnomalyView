package com.miaozij.anomalyview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private AnomalyView mAnomalyView;
    private Runnable mRunnable;
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnomalyView = findViewById(R.id.anomaly);

        myHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                updateView();
                myHandler.postDelayed(this,1000);
            }
        };
        myHandler.postDelayed(mRunnable,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacks(mRunnable);
    }

    private void updateView(){
        Log.d("TAG","进来了");
        if(mAnomalyView.getCurrentShowType() == AnomalyView.SHOWTYPE.CIRCLE){
            mAnomalyView.updateView(AnomalyView.SHOWTYPE.SQUARE);
        }else if(mAnomalyView.getCurrentShowType() == AnomalyView.SHOWTYPE.SQUARE){
            mAnomalyView.updateView(AnomalyView.SHOWTYPE.TRIANGLE);
        }else if(mAnomalyView.getCurrentShowType() == AnomalyView.SHOWTYPE.TRIANGLE){
            mAnomalyView.updateView(AnomalyView.SHOWTYPE.CIRCLE);
        }
    }
}
