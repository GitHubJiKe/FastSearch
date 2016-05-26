package com.ypf.fastsearch.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.tubb.smrv.SwipeMenuRecyclerView;
import com.ypf.fastsearch.R;
import com.ypf.fastsearch.TApplication;
import com.ypf.fastsearch.adapter.RecordAdapter;
import com.ypf.fastsearch.bean.Detial;
import com.ypf.fastsearch.bean.Record;
import com.ypf.fastsearch.util.SnackUtil;
import com.ypf.fastsearch.util.StartActivityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/4/22.
 */
public class RecordFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecordAdapter.OnitemClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private List<Record> records;
    private Random random;
    private RecordAdapter adapter;
    private SwipeMenuRecyclerView.LayoutManager manager;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_fagment_layout, container, false);
        random = new Random();
        initView(view);
        initData();
        configView();
        return view;
    }

    private void configView() {
        adapter = new RecordAdapter(records);
        manager = new LinearLayoutManager(TApplication.getContext(), LinearLayoutManager.VERTICAL, false);
        swipeMenuRecyclerView.setAdapter(adapter);
        swipeMenuRecyclerView.setLayoutManager(manager);
        adapter.setListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Record record = new Record();
            record.company = getCompany();
            record.isFinished = getFinished();
            record.number = getNumber();
            record.detials = getDetials();
            records.add(record);
        }
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        swipeMenuRecyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.smrl);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.attachToRecyclerView(swipeMenuRecyclerView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                dialog.setTitle(getString(R.string.delete_all));
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.deleteAll();
                        SnackUtil.show(swipeMenuRecyclerView, getString(R.string.delete_all), 0);
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        floatingActionButton.show();
                    }
                });
                dialog.show();
                floatingActionButton.hide();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                adapter.refreshData(loadMoreData());
                SnackUtil.show(swipeMenuRecyclerView, getString(R.string.refresh), 0);
            }
        }, 1000);

    }

    private List<Record> loadMoreData() {
        ArrayList<Record> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Record record = new Record();
            record.company = getCompany();
            record.isFinished = getFinished();
            record.number = getNumber();
            record.detials = getDetials();
            records.add(record);
        }
        return records;
    }

    public String getCompany() {
        String[] companys = new String[]{"中通", "申通", "圆通", "顺丰", "德邦", "汇通", "韵达", "全峰", "天天", "EMS"};

        return companys[random.nextInt(companys.length)];
    }

    public boolean getFinished() {
        return random.nextBoolean();
    }


    public String getNumber() {
        StringBuffer number = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    public List<Detial> getDetials() {
        List<Detial> detials = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Detial detial = new Detial();
            detial.time = "2016-5-14 11:53";
            detial.detial = "快件已经到达北京市朝阳区";
            detials.add(detial);
        }
        return detials;
    }

    @Override
    public void OnitemClickListener(View view, int position) {
        StartActivityUtil.gotoDetialActivity(getActivity(), adapter.getItemData(position));
    }
}
