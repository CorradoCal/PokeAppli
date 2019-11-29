package com.corrado.pokelist.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.corrado.pokelist.R;
import com.corrado.pokelist.models.Videos;

import java.util.List;

/**
 * Created by Corrado on 20/12/2019.
 */

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.VideoViewHolder> {

    List<Videos> youtubeVideoList;

    public YTAdapter() {
    }

    public YTAdapter(List<Videos> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.videoyt, parent, false);

        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        holder.videoWeb.loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public WebView videoWeb;
        public TextView nomTxt;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.videoView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {

            } );
        }
    }
}