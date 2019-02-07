package com.example.android.wiichat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static String chatName;

    //    private TextView number_of_people;
    private static int counter;
    private Button anonButton;
    private LocationManager lm;
    private LocationListener ll;
    private Location local;
    private static String loca;
    private static DatabaseReference myDataBase;
    private static String intLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        number_of_people = (TextView) findViewById(R.id.number_of_people);
        anonButton = (Button) findViewById(R.id.anonButton);
        counter = 0;
//        number_of_people.setText(""+ counter);

        loca = "";
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                loca = "Current Location: " + location.getLatitude() + "," + location.getLongitude();
                intLoc = ((int)Math.floor(location.getLatitude()))+ "," + ((int)Math.floor(location.getLongitude()));


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            }
        } else {
            lm.requestLocationUpdates("gps", 500, 0, ll);
        }
        lm.requestLocationUpdates("gps", 500, 0, ll);




    }

    public static void setupDatabase(){
        String test = loca;
        myDataBase = FirebaseDatabase.getInstance().getReference(intLoc);

        //floor longitude and latitude. All those in the same floored longitude and latitude degree are in same CR (ex. 1.2,1.3 is in same chatroom as 1.6,1.6)
        myDataBase.setValue("Hello, World!");
    }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch(requestCode){
                case 10:
                    if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        lm.requestLocationUpdates("gps", 0, 0, ll);
                    }
            }
        }



    public void anonButton(View v)
    {
        chatName = "Anon";
        lm.requestLocationUpdates("gps", 0, 0, ll);
        counter++;
//        number_of_people.setText(counter + "");
        Intent openChatroom = new Intent(this, Chatroom.class);
        startActivity(openChatroom);
        setupDatabase();
    }

    public static String getChatName()
    {
        return chatName;
    }
    public static int getCounter()
    {
        return counter;
    }
    public static void decCounter(){ counter--;}
    public static String getLocation(){return loca;}

    public static DatabaseReference getDatabase(){return myDataBase;}


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void chatEnter(View v){
        EditText message = (EditText) findViewById(R.id.plain_text_input1);
        message.setText("Username");
    }
}
