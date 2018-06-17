package com.example.muhtadi.cataloguemovieuiux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView ivBackdrop;
    TextView tvTitleDetail, tvVoteDetail, tvReleaseDetail, tvOverview;
    String imageUrl = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBackdrop = findViewById(R.id.iv_backdrop);
        tvTitleDetail = findViewById(R.id.tv_title_detail);
        tvVoteDetail = findViewById(R.id.tv_vote_detail);
        tvReleaseDetail = findViewById(R.id.tv_release_detail);
        tvOverview = findViewById(R.id.tv_overview);

        Intent movieDetailIntent = getIntent();
        final String backdropUrl = movieDetailIntent.getStringExtra("backdrop");
        final String detailBackdrop = imageUrl+backdropUrl;
        final String detailTitle = movieDetailIntent.getStringExtra("title");
        final Double detailVote = movieDetailIntent.getDoubleExtra("vote",0.0);
        final String detailRelease = movieDetailIntent.getStringExtra("release");
        final String detailOverview = movieDetailIntent.getStringExtra("overview");

        Glide.with(this).load(detailBackdrop).placeholder(R.drawable.ic_poster_image).into(ivBackdrop);
        tvTitleDetail.setText(detailTitle);
        tvVoteDetail.setText(String.valueOf(detailVote));
        tvReleaseDetail.setText(detailRelease);
        tvOverview.setText(detailOverview);
    }
}
