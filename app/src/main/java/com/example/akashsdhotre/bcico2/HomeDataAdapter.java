package com.example.akashsdhotre.bcico2;

/**
 * Created by user on 3/4/18.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akashsdhotre.bcico2.Network.NetworkUrls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.ViewHolder> implements Filterable {

    private ArrayList<HomeDataClass> mArrayList;
    private ArrayList<HomeDataClass> mFilteredList;
    Context context;
    Typeface font,fontsolid;
    Picasso.Builder builder1,builder2;


    public HomeDataAdapter(ArrayList<HomeDataClass> arrayList, Context ctx) {

       mArrayList = arrayList;
       mFilteredList = arrayList;
       context=ctx;
    }

    @Override
    public HomeDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_posts, viewGroup, false);
        font = Typeface.createFromAsset(context.getAssets(), "fa-regular-400.ttf");
        fontsolid = Typeface.createFromAsset(context.getAssets(), "fa-solid-900.ttf");


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final HomeDataAdapter.ViewHolder holder, int position) {

        holder.cardShare.setTypeface(font);
        holder.cardLike.setTypeface(font);
        holder.cardComment.setTypeface(font);
        holder.cardoption.setTypeface(fontsolid);

        holder.cardUserName.setText(mFilteredList.get(position).getUserName());
        holder.cardPostDate.setText(mFilteredList.get(position).getPostDate());
        holder.cardPostText.setText(mFilteredList.get(position).getPostText());

        String profilepath=mFilteredList.get(position).getProfileImgUrl();
       String postImagePath= mFilteredList.get(position).getImageUrl();

        String[] words1=profilepath.split("/");
        String profileImage=words1[words1.length-1];

        System.out.println("###########"+profileImage);



        String[] words2=postImagePath.split("/");
        String postImage=words2[words2.length-1];

        System.out.println("###########"+postImage);

        builder1 = new Picasso.Builder(holder.cardProfileImage.getContext());

        builder1.build().load(NetworkUrls.BCICOLinks.BASE_URL+"/profile/"+profileImage).transform(new Fragment1.CircleTransform())
                .into(holder.cardProfileImage, new com.squareup.picasso.Callback() {


                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });


        Picasso.with(context)
                .load(NetworkUrls.BCICOLinks.BASE_URL+"/img/"+postImage)
//                .placeholder(R.drawable.placeholder)   // optional
//                .error(R.drawable.error)      // optional
                .resize(500,500)                        // optional
                .into(holder.cardPostImage);






////        viewHolder.tv_version.setText(mFilteredList.get(i).getVer());
////        viewHolder.tv_api_level.setText(mFilteredList.get(i).getApi());

    }

//    @Override
//    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
//
////        viewHolder.tv_name.setText(mFilteredList.get(i).getName());
////        viewHolder.tv_version.setText(mFilteredList.get(i).getVer());
////        viewHolder.tv_api_level.setText(mFilteredList.get(i).getApi());
//    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<HomeDataClass> filteredList = new ArrayList<>();

                    for (HomeDataClass data : mArrayList) {

                        if (data.getUserName().toLowerCase().contains(charString) || data.getPostText().toLowerCase().contains(charString) || data.getPostDate().toLowerCase().contains(charString)) {

                            filteredList.add(data);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<HomeDataClass>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //private TextView tv_name,tv_version,tv_api_level;
        private ImageView cardProfileImage,cardPostImage;
        private TextView cardUserName,cardPostDate,cardPostText;
        private TextView cardShare,cardLike,cardComment,cardoption;

        public ViewHolder(View view) {
            super(view);

            cardProfileImage=(ImageView)view.findViewById(R.id.cardProfileImage);
            cardPostImage=(ImageView)view.findViewById(R.id.cardPostImageUrl);


            cardUserName = (TextView)view.findViewById(R.id.cardUserName);
            cardPostDate = (TextView)view.findViewById(R.id.cardPostDate);
            cardPostText = (TextView)view.findViewById(R.id.cardPostText);

            cardShare = (TextView)view.findViewById(R.id.cardShare);
            cardLike = (TextView)view.findViewById(R.id.cardLike);
            cardComment = (TextView)view.findViewById(R.id.cardComment);
            cardoption = (TextView)view.findViewById(R.id.cardOption);


        }
    }

}