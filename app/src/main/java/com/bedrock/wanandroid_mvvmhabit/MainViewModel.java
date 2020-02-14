package com.bedrock.wanandroid_mvvmhabit;

import android.app.Application;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @author LiJiaqi
 * @date 2020/2/14
 * Description:
 */
public class MainViewModel extends BaseViewModel {
    public MainViewModel(Application application) {
        super(application);
    }

    public MainViewModel(Application application, BaseModel model) {
        super(application, model);
    }
}
