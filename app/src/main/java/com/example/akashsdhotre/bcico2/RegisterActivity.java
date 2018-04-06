package com.example.akashsdhotre.bcico2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akashsdhotre.bcico2.Interfaces.HTTPCallback;
import com.example.akashsdhotre.bcico2.Network.HTTPTask;
import com.example.akashsdhotre.bcico2.Network.NetworkUrls;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements HTTPCallback {


    EditText registerFnameEt, registerLnameEt, registerEmailEt, registerPasswordEt, registerMnoEt, registerReferralEt;
    Spinner registerCitySp, registerInterestsSp, registerIamSp;
    Button registerButton;
    TextView backtoLoginButton;
    TextInputLayout firstNameLayout,lastNameLayout,emailLayout,passwordLayout,mnoLayout,referralLayout;
    CheckBox registerCheckBox;

    String fname,lname,email,password,mno,referralCode,city,interests,whoiam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerFnameEt = (EditText) findViewById(R.id.registerFnameEt);
        registerLnameEt = (EditText) findViewById(R.id.registerLnameEt);
        registerEmailEt = (EditText) findViewById(R.id.registerEmailEt);
        registerPasswordEt = (EditText) findViewById(R.id.registerPasswordEt);
        registerMnoEt = (EditText) findViewById(R.id.registerMobileNoEt);
        registerReferralEt = (EditText) findViewById(R.id.registerReferralCodeEt);

        registerCitySp=(Spinner)findViewById(R.id.registerCitySp);
        registerInterestsSp=(Spinner)findViewById(R.id.registerInterestsSp);
        registerIamSp=(Spinner)findViewById(R.id.registerIamSp);

//        onclickSetup();

        registerCheckBox=(CheckBox)findViewById(R.id.registerCheckBox);

        registerButton = (Button) findViewById(R.id.registerButton);


    }

//    private void onclickSetup() {
//        firstNameLayout=(TextInputLayout)findViewById(R.id.firstNameLayout);
//        lastNameLayout=(TextInputLayout)findViewById(R.id.lastNameLayout);
//        emailLayout=(TextInputLayout)findViewById(R.id.emailLayout);
//        passwordLayout=(TextInputLayout)findViewById(R.id.passwordLayout);
//        mnoLayout=(TextInputLayout)findViewById(R.id.mnoLayout);
//        referralLayout=(TextInputLayout)findViewById(R.id.refferalLayout);
//
//        registerFnameEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.selected_rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                referralLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerLnameEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.selected_rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                referralLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerEmailEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.selected_rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                referralLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerPasswordEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.selected_rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                referralLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerMnoEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.selected_rectangle);
//                referralLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerReferralEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                referralLayout.setBackgroundResource(R.drawable.selected_rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerFnameEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.selected_rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//
//        registerFnameEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.selected_rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.rectangle);
//
//            }
//        });
//
//        registerFnameEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firstNameLayout.setBackgroundResource(R.drawable.rectangle);
//                lastNameLayout.setBackgroundResource(R.drawable.rectangle);
//                emailLayout.setBackgroundResource(R.drawable.rectangle);
//                passwordLayout.setBackgroundResource(R.drawable.rectangle);
//                mnoLayout.setBackgroundResource(R.drawable.rectangle);
//                registerCitySp.setBackgroundResource(R.drawable.rectangle);
//                registerInterestsSp.setBackgroundResource(R.drawable.rectangle);
//                registerIamSp.setBackgroundResource(R.drawable.selected_rectangle);
//
//            }
//        });
//    }


    public void onRegisterButtonClicked(View view) {


        //user = userName.getText().toString().trim();
        fname = registerFnameEt.getText().toString().trim();
        //user = "akashsdhotre@gmail.com";
        // Toast.makeText(LoginActivity.this, notificationToken, Toast.LENGTH_SHORT).show();
        lname = registerLnameEt.getText().toString().trim();
        email=registerEmailEt.getText().toString().trim();
        password=registerPasswordEt.getText().toString().trim();
        mno=registerMnoEt.getText().toString().trim();
        referralCode=registerReferralEt.getText().toString().trim();

        city=registerCitySp.getSelectedItem().toString().trim();
        interests=registerInterestsSp.getSelectedItem().toString().trim();
        whoiam=registerIamSp.getSelectedItem().toString().trim();

        //pass = "akashsdhotre";
//                type = userType.getSelectedItem().toString().trim();
//                //type = "Merchandiser";
        System.out.println(fname + " " + lname+" "+email + " " + password+" "+mno + " " + referralCode+" "+city + " " + interests+" "+whoiam);

        int flag = 0;
        if (fname.length() == 0) {
            //userName.setError("Please enter user name");
            registerFnameEt.setError("Please enter first name");
            flag = 1;
        }
        if (lname.length() == 0) {
            registerLnameEt.setError("Please enter last name");
            flag = 1;
        }

        if (email.length() == 0) {
            //userName.setError("Please enter user name");
            registerEmailEt.setError("Please enter Email");
            flag = 1;
        }
        if (password.length() == 0) {
            registerPasswordEt.setError("Please enter Password");
            flag = 1;
        }

        if (mno.length() == 0) {
            //userName.setError("Please enter user name");
            registerMnoEt.setError("Please enter Mobile no.");
            flag = 1;
        }
        if (referralCode.length() == 0) {
            registerReferralEt.setError("Please Referral code");
            flag = 1;
        }

        if(registerCheckBox.isChecked()==false)
        {
            registerCheckBox.setError("please select checkbox");
            flag=1;
        }




        JSONObject jsonObject = new JSONObject();
        if (flag == 0) {
            try {
                System.out.println("*********************Before json");
                System.out.println(fname+" "+lname+" "+email+" "+city+" "+interests+" "+whoiam+" "+mno+" "+password);

                jsonObject.put("fName", fname);
                jsonObject.put("lName", lname);
                jsonObject.put("email", email);
                jsonObject.put("city", city);
                jsonObject.put("intrest", interests);
                jsonObject.put("investor", whoiam);
                jsonObject.put("phoneNumber", mno);
                jsonObject.put("password",password);
//                jsonObject.put("password", lname);
//                jsonObject.put("email", fname);
//                jsonObject.put("password", lname);

//                obj.firstName = req.body.fName;
//                obj.lastName = req.body.lName;
//                obj.email = req.body.email;
//                obj.city = req.body.city;
//                obj.intrest = req.body.intrest;
//                obj.investor = req.body.investor;
//                obj.fromReference = req.body.fromReference;
//                obj.phoneNumber = req.body.phoneNumber;
//                obj.isAuthenticated = false;


            } catch (JSONException e) {
                e.printStackTrace();
            }
            HTTPTask httpTask = new HTTPTask();
            System.out.println(jsonObject.toString());
            httpTask.setData(RegisterActivity.this, RegisterActivity.this, "POST", NetworkUrls.BCICOLinks.REGISTRATION_URL, jsonObject.toString(), 1);
            httpTask.execute("");


        }



        if(flag==0) {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        }


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
            System.out.println("/***********///////"+message);
//            int stCode = Integer.parseInt(jResponse.getString("statusCode"));
//            System.out.println("status code from log in " + stCode);
//            String stMsg = jResponse.getString("statusMessage");
            //String success=jResponse.getString("success");

            System.out.println("***********************");
            // System.out.println(success);

            //Toast.makeText(this, statusMessage, Toast.LENGTH_LONG).show();

            if (message == true) {
                JSONObject Rdata = jResponse.getJSONObject("data");
                System.out.println(Rdata.toString());

                Toast.makeText(this, "Register Successfully", Toast.LENGTH_LONG).show();

//                Intent i=new Intent(LoginActivity.this,HomeActivity.class);
//                startActivity(i);



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


}
