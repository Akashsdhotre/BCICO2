package com.example.akashsdhotre.bcico2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.akashsdhotre.bcico2.Interfaces.HTTPCallback;
import com.example.akashsdhotre.bcico2.Network.HTTPTask;
import com.example.akashsdhotre.bcico2.Network.HTTPTaskDemo;
import com.example.akashsdhotre.bcico2.Network.NetworkUrls;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements HTTPCallback {

    public  static HomeDataAdapter adapter;
    RecyclerView recyclerView;
   // private ArrayList<AndroidVersion> androidVersions = new ArrayList<>();
   private ArrayList<HomeDataClass> allData = new ArrayList<>();
    View view;


    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance() {
        Fragment1 fragment1 = new Fragment1();

        return fragment1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view= inflater.inflate(R.layout.fragment_fragment1, container, false);


        setHasOptionsMenu(true);  // To performing action bar operation on fragment

        initViews();             //

        return view;


    }

    private void initViews() {

        recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

         //passing data to dataAdapter

//        JSONObject jsonObject = new JSONObject();
//
//            try {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
               String token = sharedPreferences.getString("token", "N/A");

//                //jsonObject.put("x-access-token", token);

               String reqUrl =NetworkUrls.BCICOLinks.GET_POST_DATA+"?token="+token;

               System.out.println("********** "+reqUrl);
//
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            HTTPTask httpTask = new HTTPTask();


           // System.out.println("###############"+jsonObject.toString());


//            httpTask.setData( Fragment1.this, getActivity(), "GET", reqUrl, "", 1);
//            httpTask.execute("");

        HTTPTaskDemo httpTask = new HTTPTaskDemo();

        httpTask.setData( Fragment1.this, getActivity(), "GET", reqUrl,"", 1);
            httpTask.execute("");

        System.out.println("###############"+"After http call");



        //end passing data to adapter


//        androidVersions.add(new AndroidVersion("1","Beta","11"));
//        androidVersions.add(new AndroidVersion("2","Alfa","11"));
//        androidVersions.add(new AndroidVersion("3","cupcake","11"));
//        androidVersions.add(new AndroidVersion("4","donut","11"));
//        androidVersions.add(new AndroidVersion("5","eclaire","11"));
//        androidVersions.add(new AndroidVersion("6","fun","11"));
//        androidVersions.add(new AndroidVersion("7","gingerbread","11"));
//        androidVersions.add(new AndroidVersion("8","honeycomb","11"));

        //data.add(new HomeDataClass("","Akash Dhotre","03/04/2018","My name isAkash dhotre Iam from pune",""));
        //data





//        adapter = new HomeDataAdapter(data,getActivity());
//        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    //Toast.makeText(getActivity(), (int)countries.get(position), Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void onSuccess(int statusCode, String statusMessage, String data, int version) {

        System.out.println(data);


        JSONObject jResponse;
        try {
            jResponse = new JSONObject(data);

                JSONArray tempArray = jResponse.getJSONArray("data");
                JSONObject tempObjectList;
                String profileImage,userName,createdAt,text,image;

                for (int i = 0; i < tempArray.length(); i++) {

                    tempObjectList = tempArray.getJSONObject(i);

                    profileImage = tempObjectList.getString("profileImage");
                    userName = tempObjectList.getString("userName");

                    createdAt=tempObjectList.getString("createdAt");
                    text=tempObjectList.getString("text");
                    image= tempObjectList.getString("image");


                    HomeDataClass tempAP = new HomeDataClass(profileImage,userName,createdAt,text,image );

                    //data.add(new HomeDataClass("","Akash Dhotre","03/04/2018","My name isAkash dhotre Iam from pune",""));
                    allData.add(tempAP);

                }
                System.out.println("@@@@@@@@@@@@ Before set Adapter");
                adapter = new HomeDataAdapter(allData,getActivity());
                recyclerView.setAdapter(adapter);


//                apViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                        APlist selectedEntry = approvePendingList.get(position);
////
////                        Intent intent = new Intent(PromoterEntryListActivity.this, PromoterEditAPActivity.class);
////                        intent.putExtra("MyAPClass", selectedEntry);
////                        startActivity(intent);
//
//                    }
//                });
//
//            }
//            else {
//                Toast.makeText(getActivity(), stMsg, Toast.LENGTH_SHORT).show();
//            }

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

    static public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
