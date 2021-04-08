package com.ueniweb.techsuperficial.socialmerevup.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.ueniweb.techsuperficial.socialmerevup.R;
import com.ueniweb.techsuperficial.socialmerevup.model.FeedData;
import com.ueniweb.techsuperficial.socialmerevup.util.ImageLoader;
import com.ueniweb.techsuperficial.socialmerevup.util.listener.FeedClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedPostAdapter extends RecyclerView.Adapter<FeedPostAdapter.feedpostviewholder> {

    ArrayList<FeedData> mPostlist;
    private Context mcontext;
    FeedClickListener feedClickListener;

    public FeedPostAdapter(Context context, FeedClickListener feedClickListener) {
        mPostlist = new ArrayList<>();
        this.mcontext = context;
        this.feedClickListener = feedClickListener;
    }

    @NonNull
    @Override
    public feedpostviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.social_feed_item, parent, false);
        return new FeedPostAdapter.feedpostviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull feedpostviewholder holder, int position) {
        FeedData feedpost = mPostlist.get(position);
        holder.title_tv.setText(feedpost.getTitle());
        holder.content_tv.setText(feedpost.getContent());
        holder.username_tv.setText(feedpost.getUsername());
        holder.location_tv.setText(feedpost.getLocation());
        if (feedpost.getImage() != null)
            ImageLoader.loadImage(feedpost.getImage(), holder.feed_iv, mcontext);
        holder.top_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedClickListener.feedItemClicked(feedpost, false);
            }
        });
        holder.useriv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedClickListener.feedItemClicked(feedpost, true);
            }

        });
    }


    @Override
    public int getItemCount() {
        return mPostlist.size();

    }

    public void setList(ArrayList<FeedData> feedData) {
        mPostlist.clear();
        mPostlist.addAll(feedData);
        notifyDataSetChanged();
    }

    public class feedpostviewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.feed_iv)
        ImageView feed_iv;
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.content_tv)
        TextView content_tv;
        @BindView(R.id.topcl)
        ConstraintLayout top_cl;
        @BindView(R.id.location_tv)
        TextView location_tv;
        @BindView(R.id.username_tv)
        TextView username_tv;
        @BindView(R.id.user_iv)
        CircularImageView useriv;

        public feedpostviewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

