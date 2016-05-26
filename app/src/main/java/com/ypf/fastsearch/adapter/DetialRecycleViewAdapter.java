package com.ypf.fastsearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ypf.fastsearch.R;
import com.ypf.fastsearch.TApplication;
import com.ypf.fastsearch.bean.Detial;

import java.util.List;

/**
 * Created by Administrator on 2016/5/14.
 */
public class DetialRecycleViewAdapter extends
        RecyclerView.Adapter<DetialRecycleViewAdapter.DetialViewHolder> {
    private List<Detial> detials;

    public DetialRecycleViewAdapter(List<Detial> detials) {
        this.detials = detials;
    }

    @Override
    public DetialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(TApplication.getContext()).inflate(R.layout.detial_item_layout, parent, false);
        DetialViewHolder holder = new DetialViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetialViewHolder holder, int position) {
        holder.txt_time.setText(detials.get(position).time);
        holder.txt_detial.setText(detials.get(position).detial);
    }

    @Override
    public int getItemCount() {
        return detials.size();
    }

    class DetialViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_time;
        private TextView txt_detial;

        public DetialViewHolder(View itemView) {
            super(itemView);
            txt_time = (TextView) itemView.findViewById(R.id.txt_time);
            txt_detial = (TextView) itemView.findViewById(R.id.txt_detial);
        }
    }
}
