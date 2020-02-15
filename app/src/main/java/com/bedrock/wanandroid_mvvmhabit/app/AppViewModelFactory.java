package com.bedrock.wanandroid_mvvmhabit.app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bedrock.wanandroid_mvvmhabit.data.MainRepository;
import com.bedrock.wanandroid_mvvmhabit.ui.login.LoginViewModel;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 *  自定义 viewmodel factory, viewModelProvider会使用此类进行 VM和Activity（等）进行绑定
 *  根据页面返回对应 ViewModel
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final MainRepository mRepository;


    public static AppViewModelFactory getInstance(Application application){
        if(INSTANCE == null){
            synchronized (AppViewModelFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new AppViewModelFactory(application,Injection.provideDemoRepository());
                }
            }
        }

        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, MainRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(mApplication,mRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
