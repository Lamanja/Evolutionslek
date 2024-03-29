package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/*
 * Called by: IsDead
 *
 * Purpose: Scan two other individuals' breeding qr-codes, and return their child
 *
 * Possible future improvements:
 */

public class Scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    Animals parent = new Animals();
    Animals djur = new Animals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        parent.maxHealth=0;
        scan();
    }

    @Override
    public void handleResult(Result result){
        String data = result.getText();
        String[] data2 = data.split(",");
        double a = Double.parseDouble(data2[0]);
        double b = Double.parseDouble(data2[1]);
        double c = Double.parseDouble(data2[2]);
        double d = Double.parseDouble(data2[3]);
        double e = Double.parseDouble(data2[4]);
        double f = Double.parseDouble(data2[5]);
        double g = Double.parseDouble(data2[6]);
        boolean h = Boolean.parseBoolean(data2[7]);
        String i = data2[8];
        int j = Integer.parseInt(data2[9]);


        if(parent.maxHealth==0){ // parent max health is set to 0 in onCreate()

            parent.mass = a;
            parent.horns = b;
            parent.speed = c;
            parent.defense = d;
            parent.maxHealth = e;
            parent.claws = f;
            parent.attack = g;
            parent.species = i;
            parent.winpoints = j;
            scan();
        }
        else{
            if(!i.equals(parent.species)){
                finish();
            }
            djur.mass = newStats(parent.mass, a);
            djur.horns = newStats(parent.horns, b);
            djur.speed = newStats(parent.speed, c);
            djur.defense= newStats(parent.defense, d);
            djur.maxHealth = newStats(parent.maxHealth, e);
            djur.health = djur.maxHealth;
            djur.claws = newStats(parent.claws, f);
            djur.attack = newStats(parent.attack, g);
            djur.herbivore = h;
            djur.species = i;
            djur.winpoints = Math.min(parent.winpoints, j);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", djur);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
    public double newStats(double stat1, double stat2){
        double random1 = Math.random();
        double random3 = Math.random();
        double random2 = Math.random()*0.4 + 0.8; //creates random between 0.8 - 1.2
        double result;
        result = (stat1*random1 + stat2*random3)/(random1+random3)*random2;
        return result;
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