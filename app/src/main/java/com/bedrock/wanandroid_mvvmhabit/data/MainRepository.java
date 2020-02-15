package com.bedrock.wanandroid_mvvmhabit.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.bedrock.wanandroid_mvvmhabit.data.source.HttpDataSource;
import com.bedrock.wanandroid_mvvmhabit.data.source.LocalDataSource;
import com.bedrock.wanandroid_mvvmhabit.entity.LoginBean;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 */
public class MainRepository extends BaseModel implements HttpDataSource, LocalDataSource {

    private volatile static MainRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    private MainRepository(@NonNull HttpDataSource httpDataSource,
                           @NonNull LocalDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static MainRepository getInstance(HttpDataSource httpDataSource,
                                             LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Observable<BaseResponse<LoginBean>> login(String userName, String passWord) {
        return mHttpDataSource.login(userName, passWord);
    }

    @Override
    public void saveUserName(String userName) {

    }

    @Override
    public void savePassword(String password) {

    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }
}
