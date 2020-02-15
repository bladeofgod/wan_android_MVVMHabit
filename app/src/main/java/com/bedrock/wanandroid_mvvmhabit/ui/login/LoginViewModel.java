package com.bedrock.wanandroid_mvvmhabit.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bedrock.wanandroid_mvvmhabit.data.MainRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 * MainRepository: 内部进行 网络API接口调用  和 本地缓存调用,以获取数据
 */
public class LoginViewModel extends BaseViewModel<MainRepository> {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LoginViewModel(@NonNull Application application, MainRepository model) {
        super(application, model);
    }
}
