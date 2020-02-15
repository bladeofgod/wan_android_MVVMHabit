package com.bedrock.wanandroid_mvvmhabit.data.source.http;

import com.bedrock.wanandroid_mvvmhabit.data.source.HttpDataSource;
import com.bedrock.wanandroid_mvvmhabit.data.source.http.service.WanAndroidAPI;
import com.bedrock.wanandroid_mvvmhabit.entity.LoginBean;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 * 各网络请求的代理类
 */
public class HttpDataSourceImpl implements HttpDataSource {

    private WanAndroidAPI wanAndroidAPI;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(WanAndroidAPI wanAndroidAPI){
        if(INSTANCE == null){
            synchronized (HttpDataSourceImpl.class){
                if(INSTANCE == null){
                    INSTANCE = new HttpDataSourceImpl(wanAndroidAPI);
                }
            }
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public HttpDataSourceImpl(WanAndroidAPI wanAndroidAPI) {
        this.wanAndroidAPI = wanAndroidAPI;
    }

    @Override
    public Observable<BaseResponse<LoginBean>> login(String userName, String passWord) {
        return wanAndroidAPI.login(userName, passWord);
    }
}
