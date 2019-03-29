package com.corrado.pokelist.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.corrado.pokelist.models.Result;
import com.corrado.pokelist.R;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Corrado on 26/03/2019.
 */
public class PokemonAdapter extends RecyclerView.Adapter {

    public interface OnItemClickListener {
        void onItemClick(Result result);
    }

    private List<Result> mResultList;
    private OnItemClickListener mListener;
    private Context mContext;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    // CONSTRUCTOR
    public PokemonAdapter(List<Result> resultList, OnItemClickListener listener, Context context) {
        this.mResultList = resultList;
        this.mListener = listener;
        this.mContext = context;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pokemon_item, parent, false);

        return new ListViewHolder(view);
    }

    // UPDATE VIEW HOLDER
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((ListViewHolder) viewHolder).bindView(this.mResultList.get(position));

        // apply the animation when the view is bound
        setAnimation(viewHolder.itemView, position);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mResultList.size();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.pokemon_item_name)
        TextView textView;

        Result mResult;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindView(Result result){
            this.textView.setText(StringUtils.capitalize(result.getName()));
            mResult = result;
        }

        @Override
        public void onClick(View v) {
            Log.d("ListViewHolder", "onClick: "+mResult.getUrl());
            mListener.onItemClick(mResult);
        }
    }
}
