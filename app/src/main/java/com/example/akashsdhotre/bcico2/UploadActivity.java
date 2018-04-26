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

import com.example.akashsdhotre.bcico2.service.UserClient;
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
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UploadActivity extends AppCompatActivity  {
    SharedPreferences sharedPreferences;
    Picasso.Builder builder1;
    Typeface font;
    ImageView uploadProfile,viewImage;
    EditText uploadText;
    LinearLayout linCamera,linVideo;
    TextView uploadPhoto,uploadVideo;
    Button post;
    File takephotofile;
    String picturePath;
    String token;






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



        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                token = sharedPreferences.getString("token", "N/A");

                uploadFile();



            }

            private void uploadFile() {

                File file=new File(picturePath);

                RequestBody tokenpart=RequestBody.create(MultipartBody.FORM,token);
                RequestBody typepart=RequestBody.create(MultipartBody.FORM,"image");
                RequestBody actionpart=RequestBody.create(MultipartBody.FORM,"New");
                RequestBody textpart=RequestBody.create(MultipartBody.FORM,uploadText.getText().toString());

                RequestBody filebody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                MultipartBody.Part filepart=MultipartBody.Part.createFormData("image",file.getName(),filebody);



                Retrofit.Builder builder=new Retrofit.Builder()
                        .baseUrl(NetworkUrls.BCICOLinks.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit=builder.build();

                UserClient client=retrofit.create(UserClient.class);

                retrofit2.Call<ResponseBody> call=client.insertpostData(tokenpart,typepart,actionpart,textpart,filepart);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                        Toast.makeText(UploadActivity.this,"Image uploaded successfully",Toast.LENGTH_SHORT).show();
                        uploadText.setText("");
                        viewImage.setImageResource(0);

                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(UploadActivity.this,"Not Uploaded",Toast.LENGTH_SHORT).show();
                        uploadText.setText("");
                        viewImage.setImageResource(0);




                    }
                });


            }
        });



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

                Toast.makeText(this,"take photo path="+Environment.getExternalStorageDirectory().toString(),Toast.LENGTH_LONG).show();

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
                     takephotofile = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(takephotofile);
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





}
