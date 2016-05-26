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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private ShimmerTextView shimmer_tv;
    private MaterialEditText edit_username;
    private MaterialEditText edit_password;
    private MaterialEditText edit_password2;
    private ShimmerButton register__btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
        configView();
    }

    private void configView() {
        edit_username.setHelperText(getResources().getString(R.string.helpertext2));
        edit_password.setHelperText(getResources().getString(R.string.helpertext1));
        edit_password2.setHelperText(getResources().getString(R.string.helpertext3));
        edit_username.addTextChangedListener(this);
        edit_password.addTextChangedListener(this);
        edit_password2.addTextChangedListener(this);
        Shimmer shimmer = new Shimmer();
        shimmer.start(shimmer_tv);
    }

    private void initView() {
        shimmer_tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        edit_username = (MaterialEditText) findViewById(R.id.edit_username);
        edit_password = (MaterialEditText) findViewById(R.id.edit_password);
        edit_password2 = (MaterialEditText) findViewById(R.id.edit_password2);
        register__btn = (ShimmerButton) findViewById(R.id.register__btn);
        register__btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register__btn:
                //register one new user
                break;
        }
    }

    private void submit() {
        // validate
        String username = edit_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            register__btn.setEnabled(false);
            return;
        }

        String password = edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            register__btn.setEnabled(false);
            return;
        }

        String password2 = edit_password2.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            register__btn.setEnabled(false);
            return;
        }

        if (password.compareTo(password2) != 0) {
            register__btn.setEnabled(false);
            //SnackUtil.show(register__btn, getResources().getString(R.string.password_not_same), 0);
            return;
        }

        register__btn.setEnabled(true);
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
}
