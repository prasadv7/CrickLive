package com.cricket.crickais.freecode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter13 extends RecyclerView.Adapter <MyAdapter13.MyViewHolder>{
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReferencemun;
    ArrayList<Model> mList;
    Context context;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;
    private NativeAd nativeAd;
View containerView;

    public MyAdapter13(Context context, ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item1,parent,false);
containerView=LayoutInflater.from(context).inflate(R.layout.nativefacebook_ads,parent,false);


       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%4 == 0) {

            MobileAds.initialize(context);
            AdLoader.Builder builder = new AdLoader.Builder(context, context.getString(R.string.native2));
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    holder.templateView.setNativeAd(nativeAd);
                }
            });

            final AdLoader adLoader = builder.build();
            adLoader.loadAd(new AdRequest.Builder().build());
            holder.templateView.setVisibility(View.VISIBLE);
        }
        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Collections.sort(mList, new Comparator<Model>() {
            @Override
            public int compare(Model model, Model t1) {
                return t1.getNewssub().compareTo(model.getNewssub());
            }
        });



        Model model =mList.get(position);
        holder.point.setText(model.getHead());
        holder.schedule.setText(model.getNewshead());
        holder.schedule1.setText(model.getNews());

        Glide.with(holder.imgnews.getContext()).load(model.getNewsimg()).into(holder.imgnews);

        holder.news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, newsdetails.class);


                intent.putExtra("news",model.getNews());
                intent.putExtra("aid",model.getIdd());
                intent.putExtra("newsimg",model.getNewsimg());
                intent.putExtra("newshead",model.getNewshead());
                intent.putExtra("head",model.getHead());
                intent.putExtra("newsdate",model.getNewssub());
                intent.putExtra("desc",model.getDesc());
                intent.putExtra("ts1",model.getTs1());
                intent.putExtra("ts2",model.getTs2());


                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView point,schedule,schedule1;
        ImageView imgnews;
        RelativeLayout news;
        private NativeAdLayout nativeAd;
        TemplateView templateView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            point=itemView.findViewById(R.id.point);
            schedule=itemView.findViewById(R.id.schedule);
            schedule1=itemView.findViewById(R.id.schedule1);
            imgnews=itemView.findViewById(R.id.imgnews);
            news=itemView.findViewById(R.id.news);
            templateView=itemView.findViewById(R.id.my_template);


        }
    }
}
