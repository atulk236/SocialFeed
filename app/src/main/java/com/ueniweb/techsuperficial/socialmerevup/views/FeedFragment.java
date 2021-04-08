package com.ueniweb.techsuperficial.socialmerevup.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ueniweb.techsuperficial.socialmerevup.R;
import com.ueniweb.techsuperficial.socialmerevup.adapter.FeedPostAdapter;
import com.ueniweb.techsuperficial.socialmerevup.model.FeedData;
import com.ueniweb.techsuperficial.socialmerevup.model.FeedModel;
import com.ueniweb.techsuperficial.socialmerevup.util.GsonConvertor;
import com.ueniweb.techsuperficial.socialmerevup.util.listener.FeedClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FeedFragment extends Fragment implements FeedClickListener {
    Context mcontext;
    @BindView(R.id.feed_rv)
    RecyclerView feedRv;
    FeedPostAdapter feedPostAdapter;
    String jsonData;
    FeedModel feedModel;
    ArrayList<FeedData> list;
    @BindView(R.id.user_profile_ic_iv)
    ImageView user_profile_ic_iv;

    public FeedFragment() {
        // Required empty public constructor
    }


    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();

    }


    private void init() {
        initVariable();
        setFeedRv();
        AddData();
    }

    private void initVariable() {
        mcontext = getActivity();
        feedPostAdapter = new FeedPostAdapter(mcontext, this::feedItemClicked);
        list = new ArrayList<>();

    }

    private void AddData() {
        jsonData = (GsonConvertor.getJsonFromAssets(mcontext, "json.json"));
        feedModel = GsonConvertor.buildGSONConverter().fromJson(jsonData, FeedModel.class);
        //list.addAll(feedModel.list);
        feedPostAdapter.setList(feedModel.list);
        //feedPostAdapter.notifyDataSetChanged();
    }


    private void setFeedRv() {
        feedRv.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
        feedRv.setItemAnimator(new DefaultItemAnimator());
        feedRv.setAdapter(feedPostAdapter);

    }

    @OnClick(R.id.user_profile_ic_iv)
    public void ProfileClicked() {
        Intent intent = new Intent(mcontext, ProfileActivity.class);
        intent.putExtra("feeddata", jsonData);
        intent.putExtra("ismyprofile", true);
        startActivity(intent);
    }

    @Override
    public void feedItemClicked(FeedData feedData, boolean isprofileclicked) {
        String jsonData = GsonConvertor.buildGSONConverter().toJson(feedData);

        if (isprofileclicked) {
            Intent intent = new Intent(mcontext, ProfileActivity.class);
            intent.putExtra("feeddata", jsonData);
            intent.putExtra("ismyprofile", false);
            startActivity(intent);
        } else {
            Intent intent = new Intent(mcontext, PostDetailActivity.class);
            intent.putExtra("feeddata", jsonData);
            startActivity(intent);
        }


    }
}