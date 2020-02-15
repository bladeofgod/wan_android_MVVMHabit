package com.bedrock.wanandroid_mvvmhabit.app;

import com.bedrock.wanandroid_mvvmhabit.data.MainRepository;
import com.bedrock.wanandroid_mvvmhabit.data.source.HttpDataSource;
import com.bedrock.wanandroid_mvvmhabit.data.source.LocalDataSource;
import com.bedrock.wanandroid_mvvmhabit.data.source.http.HttpDataSourceImpl;
import com.bedrock.wanandroid_mvvmhabit.data.source.http.service.WanAndroidAPI;
import com.bedrock.wanandroid_mvvmhabit.data.source.local.LocalDataSourceImpl;
import com.bedrock.wanandroid_mvvmhabit.utils.RetrofitClient;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 *  此类会在 自定义的 viewmodel factory调用 :AppViewModelFactory
 *
 *  加载网络API、网络数据源、和本地数据源
 *  并组装 repository
 *
 */
public class Injection {


    public static MainRepository provideDemoRepository() {
        //网络API服务
        WanAndroidAPI apiService = RetrofitClient.getInstance().create(WanAndroidAPI.class);
        //网络数据源
                                    //HttpDataSourceImpl是接口的实现，并且根据网络请求调用具体的API接口
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
                                    //如上
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        //我们将上面的两个具体实现 的 对象注入 MainRepository，之后便可以在VM中进行调用请求了
        return MainRepository.getInstance(httpDataSource, localDataSource);
    }

}
