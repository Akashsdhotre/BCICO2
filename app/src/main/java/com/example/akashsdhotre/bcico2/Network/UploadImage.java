package com.example.akashsdhotre.bcico2.Network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.Charset;


/**
 * Created by user on 24/4/18.
 */

public class UploadImage extends AsyncTask<Void,Void,Void> {


    private ProgressDialog progressDialog=null;
   // private Activity activity=null;
    Context ctx;



    String token,imgpath;

    public UploadImage(String token, String picturePath, Context ctx) {
        this.token=token;
        this.imgpath=picturePath;
      this.ctx=ctx;
    }


    @Override
    protected Void doInBackground(Void... voids) {


        try {//Url of the server
        String url = "http://192.168.5.175:9000/insertpostData";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        MultipartEntity mpEntity = new MultipartEntity();
        //Path of the file to be uploaded
        String filepath = imgpath;

        File file = new File(filepath);
        ContentBody cbFile = new FileBody(file, "image/jpeg");

        //Add the data to the multipart entity

            mpEntity.addPart("image", cbFile);
            mpEntity.addPart("token", new StringBody(token, Charset.forName("UTF-8")));
            mpEntity.addPart("type", new StringBody("image", Charset.forName("UTF-8")));
            mpEntity.addPart("action", new StringBody("New", Charset.forName("UTF-8")));
            mpEntity.addPart("text", new StringBody("my post", Charset.forName("UTF-8")));
            mpEntity.addPart("location", new StringBody("pune", Charset.forName("UTF-8")));


            post.setEntity(mpEntity);
            //Execute the post request
            HttpResponse response1 = client.execute(post);
//            //Get the response from the server
//            HttpEntity resEntity = response1.getEntity();
//            String Response = EntityUtils.toString(resEntity);
//            Log.d("Response:", Response);
//            //Generate the array from the response
//            JSONArray jsonarray = new JSONArray("[" + Response + "]");
//            JSONObject jsonobject = jsonarray.getJSONObject(0);
//            //Get the result variables from response
//            String result = (jsonobject.getString("result"));
//            String msg = (jsonobject.getString("msg"));
            //Close the connection
            client.getConnectionManager().shutdown();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Toast.makeText(ctx,"Uploading",Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Toast.makeText(ctx,"Uploaded",Toast.LENGTH_SHORT).show();

    }
}

