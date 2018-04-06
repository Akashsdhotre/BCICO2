package com.example.akashsdhotre.bcico2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by akashsdhotre on 07/03/18.
 */

public class TabsPager extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String>tabTitles=new ArrayList<>();
    Context ctx;

//    private int[] images = {
//            R.drawable.ic_menu_camera,
//            R.drawable.ic_menu_camera,
//            R.drawable.ic_menu_camera
//    };

   // private String[] titles = {"frag1", "frag2", "frag3"};

    //ctx.getResources().getString(R.string.fa_bell)

    public TabsPager(FragmentManager fm) {
        super(fm);
    }


    public void addFragments(Fragment fragments) {
        this.fragments.add(fragments);
      //this.tabTitles.add(titles);

    }

    public void getContext(Context context) {
        ctx = context;

    }




    @Override
    public Fragment getItem(int position) {

//        int temp=0;
//
//        switch(position)
//        {
//            case 0:
//
//                Toast.makeText(ctx,"page="+position,Toast.LENGTH_SHORT).show();
//               MainActivity.slidebar2.setVisibility(View.GONE);
//                MainActivity.slidebar3.setVisibility(View.GONE);
//                MainActivity.slidebar4.setVisibility(View.GONE);
//                MainActivity.slidebar5.setVisibility(View.GONE);
//                MainActivity.slidebar1.setVisibility(View.VISIBLE);
//
//              return Fragment1.newInstance();
////                temp=1;
////                break;
//
//
//
//            case 2:
//                Toast.makeText(ctx,"page="+position,Toast.LENGTH_SHORT).show();
//                MainActivity.slidebar1.setVisibility(View.GONE);
//                MainActivity.slidebar3.setVisibility(View.GONE);
//                MainActivity.slidebar4.setVisibility(View.GONE);
//                MainActivity.slidebar5.setVisibility(View.GONE);
//                MainActivity.slidebar2.setVisibility(View.VISIBLE);
//
//                return Fragment2.newInstance();
////                temp=2;
////                break;
//
//
//
//            case 3:
//                Toast.makeText(ctx,"page="+position,Toast.LENGTH_SHORT).show();
//
//                MainActivity.slidebar1.setVisibility(View.GONE);
//                MainActivity.slidebar2.setVisibility(View.GONE);
//                MainActivity.slidebar4.setVisibility(View.GONE);
//                MainActivity.slidebar5.setVisibility(View.GONE);
//                MainActivity.slidebar3.setVisibility(View.VISIBLE);
//
//
//                return Fragment3.newInstance();
////                temp=3;
////                break;
//
//            case 4:
//
//                Toast.makeText(ctx,"page="+position,Toast.LENGTH_SHORT).show();
//
//                MainActivity.slidebar1.setVisibility(View.GONE);
//                MainActivity.slidebar2.setVisibility(View.GONE);
//                MainActivity.slidebar3.setVisibility(View.GONE);
//                MainActivity.slidebar5.setVisibility(View.GONE);
//                MainActivity.slidebar4.setVisibility(View.VISIBLE);
//
//                return Fragment4.newInstance();
////                temp=4;
////                break;
//
//
//
//            case 5:
//                Toast.makeText(ctx,"page="+position,Toast.LENGTH_SHORT).show();
//
//
//                MainActivity.slidebar1.setVisibility(View.GONE);
//                MainActivity.slidebar2.setVisibility(View.GONE);
//                MainActivity.slidebar3.setVisibility(View.GONE);
//                MainActivity.slidebar4.setVisibility(View.GONE);
//                MainActivity.slidebar5.setVisibility(View.VISIBLE);
//
//                return Fragment5.newInstance();
//
////                temp=5;
////                break;
//
//
//
//        }


      // MainActivity.slidebar1.setVisibility(View.VISIBLE);

        return fragments.get(position);

    }

    @Override
    public int getCount() {
//        System.out.println("*******fragment size="+fragments.size());
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
////        switch(position)
////           {
////               case 1:
////                   titles[position]=ctx.getResources().getString(R.string.fa_bell);
////                   break;
////
////               case 2:
////                   titles[position]=ctx.getResources().getString(R.string.fa_bell);
////                   break;
////
////               case 3:
////                   titles[position]=ctx.getResources().getString(R.string.fa_bell);
////                   break;
////
////            }
//          return tabTitles.get(position);
//
//    }


}
