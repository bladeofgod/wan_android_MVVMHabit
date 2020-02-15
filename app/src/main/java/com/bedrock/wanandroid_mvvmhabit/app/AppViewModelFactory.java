package com.bedrock.wanandroid_mvvmhabit.app;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.bedrock.wanandroid_mvvmhabit.data.MainRepositiory;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 *  根据页面返回对应 ViewModel
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final MainRepositiory mRepository;


    public static AppViewModelFactory getInstance(Application application){
        if(INSTANCE == null){
            synchronized (AppViewModelFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new AppViewModelFactory(application,);
                }
            }
        }
    }


}
