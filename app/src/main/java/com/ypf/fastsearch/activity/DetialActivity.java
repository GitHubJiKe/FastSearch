package com.ypf.fastsearch.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.ypf.fastsearch.Pic;
import com.ypf.fastsearch.R;
import com.ypf.fastsearch.adapter.DetialRecycleViewAdapter;
import com.ypf.fastsearch.bean.Record;
import com.ypf.fastsearch.util.FrescoUtil;

public class DetialActivity extends AppCompatActivity implements Pic {
    private SimpleDraweeView logo_company;
    private TextView express_number;
    private RecyclerView rv_detials;
    private DetialRecycleViewAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detial_layout);
        initView();
        initData();
    }

    private void initData() {
        Record record = (Record) getIntent().getSerializableExtra("record");
        adapter = new DetialRecycleViewAdapter(record.detials);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_detials.setAdapter(adapter);
        rv_detials.setLayoutManager(manager);
        FrescoUtil.displayPicFromNet(logo_company, getUrl(record.company), ImageRequest.ImageType.SMALL, false);
        express_number.setText(String.valueOf(record.number));
    }

    private void initView() {
        logo_company = (SimpleDraweeView) findViewById(R.id.logo_company);
        express_number = (TextView) findViewById(R.id.express_number);
        rv_detials = (RecyclerView) findViewById(R.id.rv_detials);
    }

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
}
