package com.ypf.fastsearch.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerButton;
import com.romainpiel.shimmer.ShimmerTextView;
import com.ypf.fastsearch.R;
import com.ypf.fastsearch.util.StartActivityUtil;

public class LoginActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    private ShimmerTextView shimmer_tv;
    private MaterialEditText edit_username;
    private MaterialEditText edit_password;
    private ShimmerButton login_btn;
    private ShimmerButton register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
        configView();
    }

    private void configView() {
        edit_username.setHelperText(getResources().getString(R.string.helpertext2));
        edit_password.setHelperText(getResources().getString(R.string.helpertext1));
        edit_username.addTextChangedListener(this);
        edit_password.addTextChangedListener(this);
        Shimmer shimmer = new Shimmer();
        shimmer.start(shimmer_tv);

    }

    private void initView() {
        shimmer_tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        edit_username = (MaterialEditText) findViewById(R.id.edit_username);
        edit_password = (MaterialEditText) findViewById(R.id.edit_password);
        login_btn = (ShimmerButton) findViewById(R.id.login_btn);
        register_btn = (ShimmerButton) findViewById(R.id.register_btn);
        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String username = edit_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            login_btn.setEnabled(false);
            return;
        }

        String password = edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            login_btn.setEnabled(false);
            return;
        }

        // TODO validate success, do something
        login_btn.setEnabled(true);


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
            case R.id.login_btn:
                //login to main
                StartActivityUtil.gotoMainActivity(LoginActivity.this);
                finish();
                break;
            case R.id.register_btn:
                //go to register
                StartActivityUtil.gotoRegisterActivity(LoginActivity.this);
                break;
        }
    }
}
