package com.bedrock.wanandroid_mvvmhabit.app;

import com.bedrock.wanandroid_mvvmhabit.data.MainRepositiory;
import com.bedrock.wanandroid_mvvmhabit.data.source.HttpDataSource;
import com.bedrock.wanandroid_mvvmhabit.data.source.LocalDataSource;
import com.bedrock.wanandroid_mvvmhabit.data.source.http.HttpDataSourceImpl;
import com.bedrock.wanandroid_mvvmhabit.data.source.http.service.WanAndroidAPI;
import com.bedrock.wanandroid_mvvmhabit.utils.RetrofitClient;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 *  加载网络API、网络数据源、和本地数据源
 *  并组装 repository
 *
 */
public class Injection {


    public static MainRepositiory provideDemoRepository() {
        //网络API服务
        WanAndroidAPI apiService = RetrofitClient.getInstance().create(WanAndroidAPI.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return DemoRepository.getInstance(httpDataSource, localDataSource);
    }

}
