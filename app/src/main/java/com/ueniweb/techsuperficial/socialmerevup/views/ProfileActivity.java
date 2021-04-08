package com.ueniweb.techsuperficial.socialmerevup.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ueniweb.techsuperficial.socialmerevup.R;
import com.ueniweb.techsuperficial.socialmerevup.model.FeedData;
import com.ueniweb.techsuperficial.socialmerevup.util.GsonConvertor;
import com.ueniweb.techsuperficial.socialmerevup.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
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

    @BindView(R.id.plocation_tv)
    TextView plocation_tv;
    @BindView(R.id.pusername_tv)
    TextView pusername_tv;
    boolean ismyprofile;
    @BindView(R.id.all_post_tv)
    TextView all_post_tv;
    @BindView(R.id.cardview)
    CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String jsonData = getIntent().getStringExtra("feeddata");
            feedData = GsonConvertor.buildGSONConverter().fromJson(jsonData, FeedData.class);
            ismyprofile = getIntent().getBooleanExtra("ismyprofile", false);
        }
        init();
    }

    private void init() {
        initVariable();
        if (!ismyprofile)
            setView();
        else {
            all_post_tv.setText("My Post");
            username_tv.setText("My Profile");
            location_tv.setText("India");
            title_tv.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam efficitur ipsum in placerat molestie");
            content_tv.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam efficitur ipsum in placerat molestie\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam efficitur ipsum in placerat molestie");
        }
    }

    private void initVariable() {
        mcontext = ProfileActivity.this;

    }

    private void setView() {
        title_tv.setText(feedData.getTitle());
        content_tv.setText(feedData.getContent());
        username_tv.setText(feedData.getUsername());
        location_tv.setText(feedData.getLocation());
        pusername_tv.setText(feedData.getUsername());
        plocation_tv.setText(feedData.getLocation());
        if (feedData.getImage() != null)
            ImageLoader.loadImage(feedData.getImage(), feed_iv, mcontext);
    }
}