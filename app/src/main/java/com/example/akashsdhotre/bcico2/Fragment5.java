package com.example.akashsdhotre.bcico2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment5 extends Fragment {

    public  static DataAdapter adapter;



    private ArrayList<AndroidVersion> androidVersions = new ArrayList<>();
    View view;


    public Fragment5() {
        // Required empty public constructor
    }


    public static Fragment5 newInstance() {
        Fragment5 fragment5 = new Fragment5();

        return fragment5;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_fragment5, container, false);

        initViews();
        return view;


    }

    private void initViews() {

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view5);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        androidVersions.add(new AndroidVersion("1","Beta","11"));
        androidVersions.add(new AndroidVersion("2","Alfa","11"));
        androidVersions.add(new AndroidVersion("3","cupcake","11"));
        androidVersions.add(new AndroidVersion("4","donut","11"));
        androidVersions.add(new AndroidVersion("5","eclaire","11"));
        androidVersions.add(new AndroidVersion("6","fun","11"));
        androidVersions.add(new AndroidVersion("7","gingerbread","11"));
        androidVersions.add(new AndroidVersion("8","honeycomb","11"));

//        androidVersions.add(new AndroidVersion("5","five","11"));
        //   countries = new ArrayList<>();
//        countries.add("Australia");
//        countries.add("India");
//        countries.add("United States of America");
//        countries.add("Germany");
//        countries.add("Russia");

        //   androidVersions.add(new AndroidVersion())

        adapter = new DataAdapter(androidVersions);
        recyclerView.setAdapter(adapter);

//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
//
//                @Override public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//            });
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if(child != null && gestureDetector.onTouchEvent(e)) {
//                    int position = rv.getChildAdapterPosition(child);
//                    //Toast.makeText(getActivity(), (int)countries.get(position), Toast.LENGTH_SHORT).show();
//                }
//
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
    }

}
