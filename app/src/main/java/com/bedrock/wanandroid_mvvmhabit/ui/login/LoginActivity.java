package com.bedrock.wanandroid_mvvmhabit.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bedrock.wanandroid_mvvmhabit.BR;
import com.bedrock.wanandroid_mvvmhabit.R;

import me.goldze.mvvmhabit.base.BaseActivity;

public class LoginActivity extends BaseActivity {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginViewModel;
    }
}
