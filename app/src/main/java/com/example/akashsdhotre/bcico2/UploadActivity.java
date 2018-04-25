package com.example.akashsdhotre.bcico2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akashsdhotre.bcico2.Interfaces.HTTPCallback;
import com.example.akashsdhotre.bcico2.Network.CircleTransform;
import com.example.akashsdhotre.bcico2.Network.HTTPTask;
import com.example.akashsdhotre.bcico2.Network.NetworkUrls;
import com.example.akashsdhotre.bcico2.Network.UploadImage;
import com.example.akashsdhotre.bcico2.model.FileInfo;
import com.example.akashsdhotre.bcico2.remote.APIUtils;
import com.example.akashsdhotre.bcico2.remote.FileService;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;


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

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadActivity extends AppCompatActivity implements HTTPCallback {
    SharedPreferences sharedPreferences;
    Picasso.Builder builder1;
    Typeface font;
    ImageView uploadProfile,viewImage;
    EditText uploadText;
    LinearLayout linCamera,linVideo;
    TextView uploadPhoto,uploadVideo;
    Button post;
    File file;
    String picturePath;
    String token;

    FileService fileService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        font = Typeface.createFromAsset(getAssets(), "fa-solid-900.ttf");
        setContentView(R.layout.activity_upload);

        uploadProfile=(ImageView)findViewById(R.id.uploadProfile);
        viewImage=(ImageView)findViewById(R.id.viewImage);
        uploadText=(EditText)findViewById(R.id.uploadText);
        linCamera=(LinearLayout)findViewById(R.id.linCamera);
        linVideo=(LinearLayout)findViewById(R.id.linVideo);
        uploadPhoto=(TextView)findViewById(R.id.uploadPhoto);
        uploadVideo=(TextView)findViewById(R.id.uploadVideo);
        post=(Button)findViewById(R.id.post);

        fileService= APIUtils.getFileService();

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String profileUrl = sharedPreferences.getString("mainProfile", "N/A");

        builder1 = new Picasso.Builder(UploadActivity.this);
        builder1.build().load(NetworkUrls.BCICOLinks.BASE_URL+"/profile/"+profileUrl)
                .transform(new CircleTransform())
                .into(uploadProfile);

        uploadPhoto.setTypeface(font);
        uploadVideo.setTypeface(font);


        linCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });

        linVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        uploadPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectImage();
//
//            }
//        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                token = sharedPreferences.getString("token", "N/A");

//                //
//
//                UploadImage task=new UploadImage(token,picturePath,UploadActivity.this);
//                task.execute();
//
//                //
                File file=new File(picturePath);
                RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                MultipartBody.Part body=MultipartBody.Part.createFormData("image",file.getName(),requestBody);

                retrofit2.Call<FileInfo> call=fileService.upload(body);

                call.enqueue(new Callback<FileInfo>() {
                    @Override
                    public void onResponse(retrofit2.Call<FileInfo> call, Response<FileInfo> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(UploadActivity.this,"Image uploaded successfully",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<FileInfo> call, Throwable t) {


                        Toast.makeText(UploadActivity.this,"Error: "+t.getMessage(),Toast.LENGTH_SHORT).show();


                    }
                });









//                JSONObject jsonObject = new JSONObject();
//
//                try {
//                    jsonObject.put("token", token);
//                    jsonObject.put("image", picturePath);
//                    jsonObject.put("type", "image");
//                    jsonObject.put("action", "New");
//                    jsonObject.put("text", "my post");
//                    jsonObject.put("location", "pune");
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                HTTPTask httpTask = new HTTPTask();
//                System.out.println(jsonObject.toString());
//                httpTask.setData( UploadActivity.this, UploadActivity.this, "POST", NetworkUrls.BCICOLinks.INSERT_POST_DATA, jsonObject.toString(), 1);
//                httpTask.execute("");





            }
        });



    }

        private void upload()throws Exception {

        //Url of the server
        String url = "http://192.168.5.175:9000/";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        MultipartEntity mpEntity = new MultipartEntity();
        //Path of the file to be uploaded
        String filepath = picturePath;
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
        //Get the response from the server
        HttpEntity resEntity = response1.getEntity();
        String Response=EntityUtils.toString(resEntity);
        Log.d("Response:", Response);
        //Generate the array from the response
        JSONArray jsonarray = new JSONArray("["+Response+"]");
        JSONObject jsonobject = jsonarray.getJSONObject(0);
        //Get the result variables from response
        String result = (jsonobject.getString("result"));
        String msg = (jsonobject.getString("msg"));
        //Close the connection
        client.getConnectionManager().shutdown();

    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                     file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

               // Log.w("path of image from gallery......******************.........", picturePath+"");
                Toast.makeText(this,"ppicturepth="+picturePath,Toast.LENGTH_LONG).show();

                viewImage.setImageBitmap(thumbnail);

//                fileFromBitmap fromBitmap=new fileFromBitmap(thumbnail,this);
//                fromBitmap.execute();
            }
        }
    }


    @Override
    public void onSuccess(int statusCode, String statusMessage, String data, int version) {

        JSONObject jResponse;
        try {
            System.out.println("here " + data);
            jResponse = new JSONObject(data.trim());
            boolean message =  jResponse.getBoolean("success");
            System.out.println("/*********** message///////"+message);


            if (message == true) {

                Toast.makeText(this, "Image Uploading Successfully", Toast.LENGTH_LONG).show();


            }

            else
            {
                Toast.makeText(this, "Image Uploading fail", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(int statusCode, String statusMessage) {

    }

    @Override
    public void onError(int statusCode, String statusMessage) {

    }
}
