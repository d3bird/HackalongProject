package e.dogbi.boardgame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class PlannedEvents extends AppCompatActivity {

    private static final String FILE = "Save.text";

    Party partise[];

    Button Load;
    Button Save;
    TextView Info;
    ScrollView Scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        partise = new Party[20];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planned_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Load = findViewById(R.id.load);
        Save = findViewById(R.id.save);
        Info = findViewById(R.id.infotext);
        Scroll = findViewById(R.id.scroll);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportParties();
            }
        });

        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadParties();
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void sendTextBlast(Party p){

      String  phoneNo = "";
      String  message = "";

      for(int i =0;i<p.getNames().length;i++) {
          phoneNo = p.getPhone()[i];


          if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

              if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

              } else {
                 // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);

              }
          } else {
              SmsManager smsManager = SmsManager.getDefault();
              smsManager.sendTextMessage(phoneNo, null, message, null, null);
          }
      }
    }


    private void loadParties(){
        FileInputStream fis =null;

        try {
            fis=openFileInput(FILE);
            InputStreamReader isr =new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addParty(){


    }

    public void removeParty(){

    }

    public void exportParties(){
        String output="";
        FileOutputStream fos = null;
        if(partise.length!=0) {
        try {
            fos = openFileOutput(FILE, MODE_PRIVATE);

            for (int i = 0; i < partise.length; i++) {
                output += partise[i].getEvent() + ";";
                output += partise[i].getDate() + ";";
                output += partise[i].getDurration() + ";";

                if(partise[i].getGames().length !=0){
                    for(int x=0; x<partise[i].getGames().length;x++){
                        output +=partise[i].getGames()[x]+"\\";
                    }
                    output +=";";
                }

                if(partise[i].getNames().length !=0){
                    for(int x=0; x<partise[i].getNames().length;x++){
                        output +=partise[i].getNames()[x]+"\\";
                    }
                    output +=";";
                }

                if(partise[i].getPhone().length !=0){
                    for(int x=0; x<partise[i].getPhone().length;x++){
                        output +=partise[i].getPhone()[x]+"\\";
                    }
                    output +=";";
                }
                output +='\n';
                fos.write(output.getBytes());

                output = "";
            }

            Toast.makeText(this,"saved to" + getFilesDir()+"/"+FILE,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        }else{// if there was no


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {//working

       /* switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);

                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }*/

    }

}
