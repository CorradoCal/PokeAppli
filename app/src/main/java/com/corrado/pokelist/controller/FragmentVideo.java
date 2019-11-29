package com.corrado.pokelist.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corrado.pokelist.R;
import com.corrado.pokelist.models.Videos;
import com.corrado.pokelist.views.YTAdapter;

import java.util.Vector;

public class FragmentVideo extends Fragment {
    public View v;
    private RecyclerView recyclerView;
    private static Vector<Videos> videos = new Vector<Videos>();
    private static int videoAffiche = 0;

    public FragmentVideo() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.video_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));

        if(videoAffiche == 0) {
            videos.add(new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3zTSakCQCSc\" frameborder=\"0\" allowfullscreen></iframe>"));
            videos.add(new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/NjBe9yr-XGY\" frameborder=\"0\" allowfullscreen></iframe>"));
            videoAffiche = 1;
        }
        YTAdapter videoAdapter = new YTAdapter(videos);
        recyclerView.setAdapter(videoAdapter);

        return v;
    }
}
