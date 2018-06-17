package com.example.muhtadi.cataloguemovieuiux.MyFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhtadi.cataloguemovieuiux.Adapter.MovieAdapter;
import com.example.muhtadi.cataloguemovieuiux.MainActivity;
import com.example.muhtadi.cataloguemovieuiux.MoviePOJO;
import com.example.muhtadi.cataloguemovieuiux.R;
import com.example.muhtadi.cataloguemovieuiux.util.ApiClient;
import com.example.muhtadi.cataloguemovieuiux.util.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private EditText editText;
    private ProgressBar progressBar;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editText = view.findViewById(R.id.et_cari);
        Button button = view.findViewById(R.id.btn_cari);
        progressBar = view.findViewById(R.id.pg_waiting);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMovie();
            }
        });
        return view;
    }

    public void MyMovie() {

        String cariMovie = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        final RecyclerView recyclerView = getView().findViewById(R.id.rv_hasil);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        Call<MoviePOJO> call = apiInterface.getMovieItems(cariMovie);

        call.enqueue(new Callback<MoviePOJO>() {
            @Override
            public void onResponse(@NonNull Call<MoviePOJO> call, @NonNull Response<MoviePOJO> response) {
                MoviePOJO data = response.body();
                assert data != null;
                if (data.getResult().size()==0) {
                    Toast.makeText(getContext(), "maaf data yang anda cari tidak ditemukan", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    recyclerView.setAdapter(new MovieAdapter(data.getResult(), R.layout.movie_list_item, getContext()));
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NonNull Call<MoviePOJO> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Maaf, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}
