package com.example.muhtadi.cataloguemovieuiux.util;

import com.example.muhtadi.cataloguemovieuiux.BuildConfig;
import com.example.muhtadi.cataloguemovieuiux.MoviePOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //Now Playing
    @GET("/3/movie/now_playing?api_key=" + BuildConfig.BASEURL)
    Call<MoviePOJO> getMovieNowPlaying (
    );

    //Up Coming
    @GET("/3/movie/upcoming?api_key=" + BuildConfig.BASEURL)
    Call<MoviePOJO> getMovieUpcoming (
    );

    //Search
    @GET("/3/search/movie?api_key=" + BuildConfig.BASEURL)
    Call<MoviePOJO> getMovieItems (
            @Query("query") String name_movie
    );


}
