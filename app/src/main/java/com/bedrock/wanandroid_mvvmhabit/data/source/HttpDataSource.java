package com.bedrock.wanandroid_mvvmhabit.data.source;

import com.bedrock.wanandroid_mvvmhabit.entity.LoginBean;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 * 定义数据获取行为
 */
public interface HttpDataSource {

    //登录
    Observable<BaseResponse<LoginBean>> login(String userName, String passWord);

}
