package com.example.akashsdhotre.bcico2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akashsdhotre.bcico2.Interfaces.HTTPCallback;
import com.example.akashsdhotre.bcico2.Network.HTTPTask;
import com.example.akashsdhotre.bcico2.Network.NetworkUrls;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements HTTPCallback {

    EditText loginEmailEt,loginPasswordEt;
    Button loginButton;
    TextView loginRegisterLink,loginForgotLink;
    TextInputLayout loginEmailLayout1,loginPasswordLayout2;

    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginRegisterLink=(TextView)findViewById(R.id.loginRegisterLink);
        loginForgotLink=(TextView)findViewById(R.id.loginForgotLink);

        loginButton=(Button)findViewById(R.id.loginButton);


        loginEmailLayout1=(TextInputLayout)findViewById(R.id.loginEmailLayout1);
        loginPasswordLayout2=(TextInputLayout)findViewById(R.id.loginPasswordLayout2);

        loginEmailEt=(EditText)findViewById(R.id.loginEmailEt);
        loginPasswordEt=(EditText)findViewById(R.id.loginPasswordEt);



//        loginEmailLayout1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                loginEmailLayout1.setBackgroundResource(R.drawable.selected_rectangle);
//               loginPasswordLayout2.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        loginPasswordLayout2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                loginEmailLayout1.setBackgroundResource(R.drawable.rectangle);
//                loginPasswordLayout2.setBackgroundResource(R.drawable.selected_rectangle);
//
//            }
//        });



//
//        loginEmailEt=(EditText)findViewById(R.id.loginEmailEt);
//        loginPasswordEt=(EditText)findViewById(R.id.loginPasswordEt);
//
//
//        loginEmailEt.setFocusable(false);
//        loginPasswordEt.setFocusable(false);
//
//
//        loginEmailEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                loginEmailLayout1.setBackgroundResource(R.drawable.selected_rectangle);
//                loginPasswordLayout2.setBackgroundResource(R.drawable.rectangle);
//                loginEmailEt.setFocusableInTouchMode(true);
//
//
//            }
//        });


        loginPasswordEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loginPasswordLayout2.setBackgroundResource(R.drawable.selected_rectangle);
                loginEmailLayout1.setBackgroundResource(R.drawable.rectangle);
                loginPasswordEt.setFocusableInTouchMode(true);



            }
        });



        loginRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        loginForgotLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //user = userName.getText().toString().trim();
                email = loginEmailEt.getText().toString().trim();
                //user = "akashsdhotre@gmail.com";
                // Toast.makeText(LoginActivity.this, notificationToken, Toast.LENGTH_SHORT).show();
                pass = loginPasswordEt.getText().toString().trim();
                //pass = "akashsdhotre";
//                type = userType.getSelectedItem().toString().trim();
//                //type = "Merchandiser";
                System.out.println(email+" "+pass);

                int flag = 0;
                if (email.length() == 0) {
                    //userName.setError("Please enter user name");
                    loginEmailEt.setError("Please enter user name");
                    flag = 1;
                }
                if (pass.length() == 0) {
                    loginPasswordEt.setError("Please enter password");
                    flag = 1;
                }

                JSONObject jsonObject = new JSONObject();
                if (flag == 0 ) {
                    try {
                        jsonObject.put("email", email);
                        jsonObject.put("password", pass);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HTTPTask httpTask = new HTTPTask();
                    System.out.println(jsonObject.toString());
                    httpTask.setData( LoginActivity.this, LoginActivity.this, "POST", NetworkUrls.BCICOLinks.LOGIN_URL, jsonObject.toString(), 1);
                    httpTask.execute("");


                }

                loginEmailEt.setText("");
                loginPasswordEt.setText("");
            }

        });
    }


    @Override
    public void onSuccess(int statusCode, String statusMessage, String data, int version) {

        System.out.println("on Success***********************");
        System.out.println(statusCode+"  " + statusMessage + "  "+data + " "+version);

        JSONObject jResponse;
        try {
            System.out.println("here " + data);
            jResponse = new JSONObject(data.trim());
            boolean message =  jResponse.getBoolean("success");
            System.out.println("/*********** message///////"+message);


            if (message == true) {
                JSONObject Rdata = jResponse.getJSONObject("data");
                System.out.println(Rdata.toString());

                String fullname =""+Rdata.getString("firstName")+" "+Rdata.getString("lastName");

                String mainProfile=Rdata.getString("profileImage");
                //

                String[] words=mainProfile.split("/");
                //System.out.println(words[words.length-1]);

                String profileImage=words[words.length-1];


                //

                String token=Rdata.getString("token");

                System.out.println("************fullname****");
                System.out.println(fullname);
                System.out.println("************profileImage****");
                System.out.println(profileImage);
                System.out.println("************token****");
                System.out.println(token);

                SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", email);
                editor.putString("password", pass);
                editor.putString("fullname",fullname);
                editor.putString("mainProfile",profileImage);
                editor.putString("token",token);
                editor.commit();

                Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();


                Intent i=new Intent(LoginActivity.this,MainActivity.class);
//               i.putExtra("email",email);
//               i.putExtra("full_name",fullname);
                startActivity(i);



            }

            else
            {
                Toast.makeText(this, "Please Enter Correct email and password", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(int statusCode, String statusMessage) {

        Toast.makeText(this, statusMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(int statusCode, String statusMessage) {

        Toast.makeText(this, statusMessage, Toast.LENGTH_SHORT).show();


    }


    //  loginEmailLayout1.setBackgroundResource(R.drawable.selected_rectangle);


}
