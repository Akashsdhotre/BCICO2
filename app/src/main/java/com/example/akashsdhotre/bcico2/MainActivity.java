package com.example.akashsdhotre.bcico2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akashsdhotre.bcico2.Network.NetworkUrls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static boolean fragment1flag=false,fragment2flag=false,fragment3flag=false,fragment4flag=false,fragment5flag=false;

    Typeface font;
    DrawerLayout drawer;
    NavigationView navigationView,navigationView2;
    ActionBarDrawerToggle mDrawerToggle;   //test


    public static ViewPager viewPager;
    TabsPager tabsPager;
    TextView textView1,textView2,textView3,textView4,textView5,openLeftDrawer,openingNavBar,groupicon;
    LinearLayout lin1,lin2,lin3,lin4,lin5;
   public static LinearLayout slidebar1,slidebar2,slidebar3,slidebar4,slidebar5;
   ImageView profile;
   ImageView headerProfileImage;
   TextView headerUserName;
   String profileUrl,userFullName;
    Picasso.Builder builder1,builder2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        font = Typeface.createFromAsset(getAssets(), "FontAwesome.ttf");
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

//       getSupportActionBar().setLogo(R.drawable.bcico);
//       getSupportActionBar().setDisplayUseLogoEnabled(true);



       drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView2 = (NavigationView) findViewById(R.id.nav_view2);

        navigationView.setNavigationItemSelectedListener(this);

       drawer.addDrawerListener(mDrawerToggle);


    //App bar profile img set
       profile=(ImageView)findViewById(R.id.profile);
        builder2 = new Picasso.Builder(MainActivity.this);
        builder2.build().load(NetworkUrls.BCICOLinks.BASE_URL+"/profile/"+profileUrl).transform(new Fragment1.CircleTransform())
                .into(profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this,profile);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.profile_popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;

                    }
                });

                popup.show();//showing popup menu

            }
        });
    // end  App bar profile img set


       onViewPagerSetup();
       onfontAusomeUsing();


       //header1

       View header=navigationView.getHeaderView(0);
       openingNavBar=(TextView)header.findViewById(R.id.openingNavBarText);
       headerProfileImage=(ImageView)header.findViewById(R.id.headerProfile);
       headerUserName=(TextView)header.findViewById(R.id.headerUserName);

       openingNavBar.setTypeface(font);
       openingNavBar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               //openLeftDrawer.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(navigationView);
           }
       });


       // slide bar header profile image setup

       SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
       userFullName = sharedPreferences.getString("fullname", "N/A");
       profileUrl = sharedPreferences.getString("mainProfile", "N/A");


       System.out.println(profileUrl+"************* in mainActivity");
       String test=NetworkUrls.BCICOLinks.BASE_URL+"/profile/"+profileUrl;
        System.out.println(test+"************* Picaso url check");


       builder1 = new Picasso.Builder(MainActivity.this);

       builder1.build().load(NetworkUrls.BCICOLinks.BASE_URL+"/profile/"+profileUrl).transform(new Fragment1.CircleTransform())
               .into(headerProfileImage);
       headerUserName.setText(userFullName);

       //end slide bar profile image setup




       //header2

//       View header2=navigationView2.getHeaderView(0);
//       groupicon=(TextView)header2.findViewById(R.id.groupicon);
//       groupicon.setTypeface(font);





    }





    private void onViewPagerSetup() {


        viewPager=(ViewPager)findViewById(R.id.viewPager);
        tabsPager=new TabsPager(getSupportFragmentManager());

        tabsPager.getContext(MainActivity.this);




        tabsPager.addFragments(new Fragment1());
        tabsPager.addFragments(new Fragment2());
        tabsPager.addFragments(new Fragment3());
        tabsPager.addFragments(new Fragment4());
        tabsPager.addFragments(new Fragment5());


        viewPager.setAdapter(tabsPager);
    }

    private void onfontAusomeUsing() {

        textView1=(TextView)findViewById(R.id.text1);
        textView2=(TextView)findViewById(R.id.text2);
        textView3=(TextView)findViewById(R.id.text3);
        textView4=(TextView)findViewById(R.id.text4);
        textView5=(TextView)findViewById(R.id.text5);
        openLeftDrawer=(TextView)findViewById(R.id.openLeftDrawer);

        lin1=(LinearLayout)findViewById(R.id.lin1);
        lin2=(LinearLayout)findViewById(R.id.lin2);
        lin3=(LinearLayout)findViewById(R.id.lin3);
        lin4=(LinearLayout)findViewById(R.id.lin4);
        lin5=(LinearLayout)findViewById(R.id.lin5);

        slidebar1=(LinearLayout)findViewById(R.id.slidebar1);
        slidebar2=(LinearLayout)findViewById(R.id.slidebar2);
        slidebar3=(LinearLayout)findViewById(R.id.slidebar3);
        slidebar4=(LinearLayout)findViewById(R.id.slidebar4);
        slidebar5=(LinearLayout)findViewById(R.id.slidebar5);




        textView1.setTypeface(font);
        textView2.setTypeface(font);
        textView3.setTypeface(font);
        textView4.setTypeface(font);
        textView5.setTypeface(font);
       openLeftDrawer.setTypeface(font);


       //default page setup

        viewPager.setCurrentItem(0);
        slidebar2.setVisibility(View.GONE);
        slidebar3.setVisibility(View.GONE);
        slidebar4.setVisibility(View.GONE);
        slidebar5.setVisibility(View.GONE);
        slidebar1.setVisibility(View.VISIBLE);

        fragment1flag=true;
        fragment2flag =false;
        fragment3flag=false;
        fragment4flag=false;
        fragment5flag=false;





        lin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(0);


                slidebar2.setVisibility(View.GONE);
                slidebar3.setVisibility(View.GONE);
                slidebar4.setVisibility(View.GONE);
                slidebar5.setVisibility(View.GONE);
                slidebar1.setVisibility(View.VISIBLE);

                lin1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin5.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));





                fragment1flag=true;
                fragment2flag =false;
                fragment3flag=false;
                fragment4flag=false;
                fragment5flag=false;

                //textView1.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        lin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(1);


                slidebar1.setVisibility(View.GONE);
                slidebar3.setVisibility(View.GONE);
                slidebar4.setVisibility(View.GONE);
                slidebar5.setVisibility(View.GONE);
                slidebar2.setVisibility(View.VISIBLE);

                lin1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin5.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


                fragment1flag=false;
                fragment2flag =true;
                fragment3flag=false;
                fragment4flag=false;
                fragment5flag=false;

            }
        });
        lin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(2);


                slidebar1.setVisibility(View.GONE);
                slidebar2.setVisibility(View.GONE);
                slidebar4.setVisibility(View.GONE);
                slidebar5.setVisibility(View.GONE);
                slidebar3.setVisibility(View.VISIBLE);

                lin1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin5.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


                fragment1flag=false;
                fragment2flag =false;
                fragment3flag=true;
                fragment4flag=false;
                fragment5flag=false;

            }
        });

        lin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(3);


                slidebar1.setVisibility(View.GONE);
                slidebar2.setVisibility(View.GONE);
                slidebar3.setVisibility(View.GONE);
                slidebar5.setVisibility(View.GONE);
                slidebar4.setVisibility(View.VISIBLE);

                lin1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin5.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


                fragment1flag=false;
                fragment2flag =false;
                fragment3flag=false;
                fragment4flag=true;
                fragment5flag=false;

            }
        });
        lin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(4);


                slidebar1.setVisibility(View.GONE);
                slidebar2.setVisibility(View.GONE);
                slidebar3.setVisibility(View.GONE);
                slidebar4.setVisibility(View.GONE);
                slidebar5.setVisibility(View.VISIBLE);

                lin1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                lin5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                fragment1flag=false;
                fragment2flag =false;
                fragment3flag=false;
                fragment4flag=false;
                fragment5flag=true;

            }
        });

       openLeftDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openLeftDrawer.setTextColor(getResources().getColor(R.color.colorPrimary));

                drawer.openDrawer(navigationView);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item=menu.findItem(R.id.search_bar);
        SearchView searchView=(SearchView)item.getActionView();


        //test start

        searchView.setQueryHint("Type something...");
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        if (searchPlate!=null) {
            searchPlate.setBackgroundColor(Color.parseColor("#373A4D"));
            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
            if (searchText != null) {
                searchText.setTextColor(Color.WHITE);
                searchText.setHintTextColor(Color.WHITE);
            }
        }

        //test end


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if(fragment1flag==true) {
                    Fragment1.adapter.getFilter().filter(s);
                }
                else if(fragment2flag==true) {
                    Fragment2.adapter.getFilter().filter(s);
                }
                else if(fragment3flag==true) {
                    Fragment3.adapter.getFilter().filter(s);
                }
                else if(fragment4flag==true) {
                    Fragment4.adapter.getFilter().filter(s);
                }
                else
                {
                    Fragment5.adapter.getFilter().filter(s);
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(fragment1flag==true) {
                    Fragment1.adapter.getFilter().filter(s);
                }
                else if(fragment2flag==true) {
                    Fragment2.adapter.getFilter().filter(s);
                }
                else if(fragment3flag==true) {
                    Fragment3.adapter.getFilter().filter(s);
                }
                else if(fragment4flag==true) {
                    Fragment4.adapter.getFilter().filter(s);
                }
                else
                {
                    Fragment5.adapter.getFilter().filter(s);
                }


                return false;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


//        if (id == R.id.group) {
//
//            drawer.openDrawer(navigationView2);

//            return true;
//        }



//       if(id==R.id.nave1)
//       {
//           drawer.openDrawer(navigationView);
//
//       }
//
//        if(id==R.id.nav2)
//        {
//            drawer.openDrawer(navigationView2);
//
//        }


//        item2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (drawer.isDrawerOpen(navigationView)) {
//                    drawer.closeDrawer(navigationView);
//                } else if (!drawer.isDrawerOpen(navigationView)) {
//                    drawer.openDrawer(navigationView);
//                }
//
//                if (drawer.isDrawerOpen(navigationView2)) {
//                    drawer.closeDrawer(navigationView2);
//                }
//            }
//        });
//
//        imgRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (drawer.isDrawerOpen(navigationView2)) {
//                    drawer.closeDrawer(navigationView2);
//                } else if (!drawer.isDrawerOpen(navigationView2)) {
//                    drawer.openDrawer(navigationView2);
//                }
//
//                if (drawer.isDrawerOpen(navigationView)) {
//                    drawer.closeDrawer(navigationView);
//                }
//
//
//            }
//        });




        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.log_out)
        {
            Intent i=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
//         else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
