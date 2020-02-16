package com.bedrock.wanandroid_mvvmhabit.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bedrock.wanandroid_mvvmhabit.BR;
import com.bedrock.wanandroid_mvvmhabit.R;
import com.bedrock.wanandroid_mvvmhabit.app.AppViewModelFactory;
import com.bedrock.wanandroid_mvvmhabit.databinding.ActivityLoginBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.KLog;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginViewModel;
    }


    @Override
    public LoginViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，
        // 则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        // 同时，下面的方法会将 viewmodel 和 activity 进行生命周期绑定
        return ViewModelProviders.of(this,factory).get(LoginViewModel.class);
    }


    @Override
    public void initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行
        // 【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uc.pSwitchEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if(viewModel.uc.pSwitchEvent.getValue()){
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    KLog.a("login", "observe value " + aBoolean);
                    binding.ivSwitchPwd.setImageResource(R.drawable.show_psw);
                }else{
                    //密码不可见
                    KLog.a("login", "observe value " + aBoolean);
                    binding.ivSwitchPwd.setImageResource(R.drawable.show_psw_press);
                }

            }
        });

    }
}

























