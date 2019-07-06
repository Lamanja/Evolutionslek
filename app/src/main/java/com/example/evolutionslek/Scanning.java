package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.Random;
import java.lang.Math;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    Animals animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        scan();
    }

    @Override
    public void handleResult(Result result){
        String data = result.getText();
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        String[] data2 = data.split(",");
        Animals djur = new Animals();
        double a = Integer.parseInt(data2[0]);
        double b = Integer.parseInt(data2[1]);
        double c = Integer.parseInt(data2[2]);
        double d = Integer.parseInt(data2[3]);
        double e = Integer.parseInt(data2[4]);
        double f = Integer.parseInt(data2[5]);
        double g = Integer.parseInt(data2[6]);
        boolean h = Boolean.parseBoolean(data2[7]);

        Intent intent = getIntent();
        animal = intent.getParcelableExtra("djur");
        Random r = new Random();
        djur.mass = (int) Math.round((r.nextGaussian()+4)*(a+animal.mass)/4);
        djur.horns =(int) Math.round((r.nextGaussian()+4)*(b+animal.horns)/4);
        djur.speed =(int) Math.round((r.nextGaussian()+4)*(c+animal.speed)/4);
        djur.defense=(int) Math.round((r.nextGaussian()+4)*(d+animal.defense)/4);
        djur.maxHealth =(int) Math.round((r.nextGaussian()+4)*(e+animal.maxHealth)/4);
        djur.claws =(int) Math.round((r.nextGaussian()+4)*(f+animal.claws)/4);
        djur.attack =(int) Math.round((r.nextGaussian()+4)*(g+animal.attack)/4);
        djur.herbivore = h;
        Toast.makeText(getApplicationContext(), Integer.toString(animal.food), Toast.LENGTH_SHORT).show();
        djur.food = animal.food;
        //Toast.makeText(getApplicationContext(), djur.food, Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", djur);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }
    public void scan(){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
}
