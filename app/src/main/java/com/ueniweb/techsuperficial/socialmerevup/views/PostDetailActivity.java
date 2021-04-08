package com.ueniweb.techsuperficial.socialmerevup.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ueniweb.techsuperficial.socialmerevup.R;
import com.ueniweb.techsuperficial.socialmerevup.model.FeedData;
import com.ueniweb.techsuperficial.socialmerevup.util.GsonConvertor;
import com.ueniweb.techsuperficial.socialmerevup.util.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity {
    Context mcontext;
    FeedData feedData;
    ArrayList<FeedData> list;
    @BindView(R.id.feed_iv)
    ImageView feed_iv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.content_tv)
    TextView content_tv;
    @BindView(R.id.location_tv)
    TextView location_tv;
    @BindView(R.id.username_tv)
    TextView username_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String jsonData = getIntent().getStringExtra("feeddata");
            feedData = GsonConvertor.buildGSONConverter().fromJson(jsonData, FeedData.class);
        }
        init();
    }

    private void init() {
        initVariable();
        setView();
    }

    private void setView() {
        title_tv.setText(feedData.getTitle());
        content_tv.setText(feedData.getContent());
        username_tv.setText(feedData.getUsername());
        location_tv.setText(feedData.getLocation());
        if (feedData.getImage() != null)
            ImageLoader.loadImage(feedData.getImage(), feed_iv, mcontext);
    }

    private void initVariable() {
        mcontext = PostDetailActivity.this;

    }
}