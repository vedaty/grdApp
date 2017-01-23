package com.sayapp.grdapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    private int[] image_resources = {R.drawable.s01, R.drawable.s02, R.drawable.s03, R.drawable.s04,
            R.drawable.s05, R.drawable.s06, R.drawable.s07, R.drawable.s08};
    private String[] isim_resources = {"Bir", "iki", "üç", "dört", "beş",
            "altı", "yedi", "sekiz"};

    public ImageView v1, v2, v3, v4;
    public TextView t1;
    public int position, correct;

    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;


    int min = 1;    // random number minimum
    int max = 4;    // random number maximum


    // Random r = new Random();
    // int i1 = r.nextInt(max - min + 1) + min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        // Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();


        /** test
         */


        t1 = (TextView) findViewById(R.id.txt1);
        correct=1;
        t1.setText (isim_resources[correct-1]);



        // first image click action
        v1 = (ImageView) findViewById(R.id.img1);
        v1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // t1.setText("deneme1");
                if (position == 1) {
                    Toast.makeText(getApplicationContext(), "deneme1234567890", Toast.LENGTH_LONG).show();
                } else {
                    v1.setImageResource(R.drawable.x);
                }

                }
        });


        //  second image click action
        v2 = (ImageView) findViewById(R.id.img2);
        v2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // t1.setText("deneme2");
                // v2.setImageResource(R.drawable.x);
                if (position == 2) {
                    Toast.makeText(getApplicationContext(), "deneme1234567890", Toast.LENGTH_LONG).show();
                } else {
                    v2.setImageResource(R.drawable.x);
                }
            }
        });


        //  third image click action
        v3 = (ImageView) findViewById(R.id.img3);
        v3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // t1.setText("deneme3");
                // v3.setImageResource(R.drawable.x);
                if (position == 3) {
                    Toast.makeText(getApplicationContext(), "deneme1234567890", Toast.LENGTH_LONG).show();
                } else {
                    v3.setImageResource(R.drawable.x);
                }
            }
        });

        //  fourth image click action
        v4 = (ImageView) findViewById(R.id.img4);
        v4.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // t1.setText("deneme4");
                // v4.setImageResource(R.drawable.x);
                 if (position == 4) {
                     Toast.makeText(getApplicationContext(), "deneme1234567890", Toast.LENGTH_LONG).show();
                     } else {
                     v4.setImageResource(R.drawable.x);

                 }
            }
        });


        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                // Do stuff!
                // generate a random number
                Random rndm = new Random();
                position = rndm.nextInt(max - min + 1) + min;
                //t1.setText (isim_resources[position-1]);
                //v1.setImageResource(image_resources[position-1]);
                // Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
                // Toast.makeText(getApplicationContext(), "Random: "+position, Toast.LENGTH_LONG).show();
                v1.setImageResource(image_resources[0]);
                v2.setImageResource(image_resources[1]);
                v3.setImageResource(image_resources[2]);
                v4.setImageResource(image_resources[3]);
                t1.setText (isim_resources[position-1]);
            }
        });

//******************


        v1.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                return true;
            }
            public boolean onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                return true;
            }
            public boolean onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                return true;
            }
            public boolean onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//**************************

    }

//*******************


    // **********************
    @Override
    public void onDestroy() {

        super.onDestroy();



        /* restart
        Intent restartIntent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(restartIntent);

        */

        //finish();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
