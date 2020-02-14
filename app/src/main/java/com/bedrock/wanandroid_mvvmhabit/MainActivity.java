package com.bedrock.wanandroid_mvvmhabit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bedrock.wanandroid_mvvmhabit.databinding.ActivityMainBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mainViewModel;
    }
}
