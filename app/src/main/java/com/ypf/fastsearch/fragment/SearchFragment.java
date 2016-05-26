package com.ypf.fastsearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.romainpiel.shimmer.ShimmerButton;
import com.ypf.fastsearch.R;
import com.ypf.fastsearch.util.SnackUtil;

/**
 * Created by Administrator on 2016/4/22.
 */
public class SearchFragment extends Fragment implements TextWatcher, View.OnClickListener {


    private MaterialEditText edit_number;
    private MaterialEditText edit_company;
    private ShimmerButton search_btn;
    private ImageView open_camera;
    private ImageView choose_company;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment_layout, container, false);
        initView(view);
        configView();
        return view;
    }

    private void configView() {
        edit_number.addTextChangedListener(this);
        edit_company.addTextChangedListener(this);
        search_btn.setOnClickListener(this);
        open_camera.setOnClickListener(this);
        choose_company.setOnClickListener(this);
    }


    private void initView(View view) {
        edit_number = (MaterialEditText) view.findViewById(R.id.edit_number);
        edit_company = (MaterialEditText) view.findViewById(R.id.edit_company);
        search_btn = (ShimmerButton) view.findViewById(R.id.search_btn);
        open_camera = (ImageView) view.findViewById(R.id.open_camera);
        choose_company = (ImageView) view.findViewById(R.id.choose_company);
    }

    private void submit() {
        // validate
        String number = edit_number.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            search_btn.setEnabled(false);
            return;
        }
        String company = edit_company.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            search_btn.setEnabled(false);
            return;
        }
        search_btn.setEnabled(true);
        // TODO validate success, do something


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        submit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                //search
                SnackUtil.show(edit_number, getString(R.string.search), 0);
                break;
            case R.id.open_camera:
                //search
                SnackUtil.show(edit_number, getString(R.string.action_open_camera), 0);
                break;
            case R.id.choose_company:
                //search
                SnackUtil.show(edit_number, getString(R.string.choose_com), 0);
                break;
        }
    }
}
