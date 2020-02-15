package com.bedrock.wanandroid_mvvmhabit.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.util.Map;

import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.Utils;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 */
public class RetrofitClient {

    //超时时间
    private static final int DEFAULT_TIMEOUT = 20;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;
    //服务端根路径
    public static String baseUrl = "https://www.wanandroid.com/";

    private static Context mContext = Utils.getContext();

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private Cache cache = null;
    private File httpCacheDirectory;

    private static class SingletonHolder{
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient(){
        this(baseUrl,null);
    }

    private RetrofitClient(String url, Map<String,String> headers){
        if(TextUtils.isEmpty(url)){
            url = baseUrl;
        }

        if(httpCacheDirectory == null){
            httpCacheDirectory = new File(mContext.getCacheDir(),"wan_android");
        }

        try {
            if(cache == null){
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        }catch (Exception e){
            KLog.e("Could not create http cache", e);
        }

        Httpu



    }


}

















