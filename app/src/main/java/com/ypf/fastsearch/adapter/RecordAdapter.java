package com.ypf.fastsearch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.tubb.smrv.SwipeMenuLayout;
import com.tubb.smrv.SwipeMenuRecyclerView;
import com.ypf.fastsearch.Pic;
import com.ypf.fastsearch.R;
import com.ypf.fastsearch.TApplication;
import com.ypf.fastsearch.bean.Record;
import com.ypf.fastsearch.util.FrescoUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
public class RecordAdapter extends SwipeMenuRecyclerView.Adapter<RecordAdapter.RecordViewHolder> implements Pic {
    private List<Record> datas;

    public RecordAdapter(List<Record> datas) {
        this.datas = datas;
    }

    private OnitemClickListener listener;

    @Override
    public String getUrl(String com) {
        String url = "";
        switch (com) {
            case "顺丰":
                url = SF;
                break;
            case "申通":
                url = STO;
                break;
            case "圆通":
                url = YTO;
                break;
            case "韵达":
                url = YD;
                break;
            case "天天":
                url = TT;
                break;
            case "EMS":
                url = EMS;
                break;
            case "中通":
                url = ZTO;
                break;
            case "汇通":
                url = HTO;
                break;
            case "全峰":
                url = QF;
                break;
            case "德邦":
                url = DB;
                break;
        }
        return url;
    }


    public interface OnitemClickListener {
        void OnitemClickListener(View view, int position);
    }

    public void setListener(OnitemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(TApplication.getContext()).inflate(R.layout.record_item_layout, parent, false);
        RecordViewHolder holder = new RecordViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecordViewHolder holder, final int position) {
        if (holder.layout.isMenuOpen()) {
            holder.layout.smoothCloseRightMenu();
        }
        if (listener != null) {
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnitemClickListener(v, position);
                }
            });
        }
        holder.delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
        Record record = datas.get(position);
        holder.textView.setText(record.number);
        FrescoUtil.displayPicFromNet(holder.simpleDraweeView, getUrl(record.company), ImageRequest.ImageType.SMALL, false);
    }

    private void delete(int position) {
        datas.remove(position);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        datas.clear();
        notifyDataSetChanged();
    }

    public void refreshData(List<Record> records) {
        datas.addAll(0, records);
        notifyDataSetChanged();
    }

    public Record getItemData(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecordViewHolder extends SwipeMenuRecyclerView.ViewHolder {
        private SimpleDraweeView simpleDraweeView;
        private TextView textView;
        private ImageView delete_iv;
        private SwipeMenuLayout layout;

        public RecordViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.company);
            textView = (TextView) itemView.findViewById(R.id.number);
            delete_iv = (ImageView) itemView.findViewById(R.id.delete_iv);
            layout = (SwipeMenuLayout) itemView.findViewById(R.id.sml);
        }
    }
}
