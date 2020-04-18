package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    Button btnSave;
    EditText name, kickPower;
    EditText kickSpeed;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edtName);
        kickPower = findViewById(R.id.edtKickPower);
        btnSave = findViewById(R.id.btnSave);
        kickSpeed = findViewById(R.id.edtKickSpeed);
        btnSave.setOnClickListener(SignUp.this);
    }

    public void helloWorldTapped(View view){

//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if(e==null){
//                    Toast.makeText(SignUp.this, "Saved in server success", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

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
