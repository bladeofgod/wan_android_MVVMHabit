package com.bedrock.wanandroid_mvvmhabit.entity.base;

import com.bedrock.wanandroid_mvvmhabit.utils.JsonFormatUtils;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 */
public class BaseBean implements Serializable {

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String toFormatJson(){
        return JsonFormatUtils.format(toJson());
    }

}
