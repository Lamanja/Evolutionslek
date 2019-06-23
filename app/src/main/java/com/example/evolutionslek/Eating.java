package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.evolutionslek.Ingame.ANIMAL;

public class Eating extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    boolean done = false;
    private ZXingScannerView zXingScannerView;
    Intent intent = getIntent();
    Animals djur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating);
        Intent intent = getIntent();
        djur = intent.getParcelableExtra(ANIMAL);
    }
        public void scan(View view){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    protected void onPause(){
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result){
        String data = result.getText();
        String[]data2 = data.split(" ");
        //add function of stats
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",data2[1]);
        done = true;
        setResult(Activity.RESULT_OK,returnIntent);

    }
    public void finish(View view){
        if (done == false){
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
