package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText name, kickPower;
    private EditText kickSpeed;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickBoxers;
    private Button btnTransition;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edtName);
        kickPower = findViewById(R.id.edtKickPower);
        btnSave = findViewById(R.id.btnSave);
        kickSpeed = findViewById(R.id.edtKickSpeed);
        btnSave.setOnClickListener(SignUp.this);
        txtGetData = findViewById(R.id.txtGetData);
        btnTransition = findViewById(R.id.btnNextActivity);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("Xll2263z5O", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object != null && e==null){

                            Toast.makeText(SignUp.this, "done", Toast.LENGTH_SHORT).show();
                            txtGetData.setText(object.get("Name") + "- Kick Power: "+ object.get("KickPower") + "- Kick Speed: "+ object.get("KickSpeed"));

                        }

                    }
                });

            }
        });


        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> queryAll =  ParseQuery.getQuery("KickBoxer");
                queryAll.whereGreaterThan("punchPower", 900);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size() > 0){
                                allKickBoxers = "";

                                for(ParseObject kickBoxer: objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("Name") +" " +kickBoxer.get("KickPower")+ " "+ kickBoxer.get("KickSpeed") +"\n";
                                    FancyToast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_SHORT, FancyToast.SUCCESS, true ).show();
                                }

                            }
                        }
                    }
                });


            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);

            }
        });

    }


    @Override
    public void onClick(View v) {
        final ParseObject kickboxer = new ParseObject("KickBoxer");
        kickboxer.put("Name", name.getText().toString());
        kickboxer.put("KickPower", kickPower.getText().toString());
        kickboxer.put("KickSpeed", kickSpeed.getText().toString());
        kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null) {
                   // Toast.makeText(SignUp.this, "Success", Toast.LENGTH_SHORT).show();

                    FancyToast.makeText(SignUp.this, "Successfully Saved", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                }else{
                    Toast.makeText(SignUp.this, e.getMessage() , Toast.LENGTH_LONG).show();

                }
            }
        });
    }



}
