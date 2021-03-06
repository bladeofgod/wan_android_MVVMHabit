package com.bedrock.wanandroid_mvvmhabit.data.source.http.service;

import com.bedrock.wanandroid_mvvmhabit.entity.AboutMeBean;
import com.bedrock.wanandroid_mvvmhabit.entity.ArticleBean;
import com.bedrock.wanandroid_mvvmhabit.entity.ArticleListBean;
import com.bedrock.wanandroid_mvvmhabit.entity.BannerBean;
import com.bedrock.wanandroid_mvvmhabit.entity.ChapterBean;
import com.bedrock.wanandroid_mvvmhabit.entity.CoinRankBean;
import com.bedrock.wanandroid_mvvmhabit.entity.CoinRecordBean;
import com.bedrock.wanandroid_mvvmhabit.entity.CollectionLinkBean;
import com.bedrock.wanandroid_mvvmhabit.entity.ConfigBean;
import com.bedrock.wanandroid_mvvmhabit.entity.HotKeyBean;
import com.bedrock.wanandroid_mvvmhabit.entity.JinrishiciBean;
import com.bedrock.wanandroid_mvvmhabit.entity.LoginBean;
import com.bedrock.wanandroid_mvvmhabit.entity.NaviBean;
import com.bedrock.wanandroid_mvvmhabit.entity.UpdateBean;
import com.bedrock.wanandroid_mvvmhabit.entity.UsefulWebBean;
import com.bedrock.wanandroid_mvvmhabit.entity.UserInfoBean;
import com.bedrock.wanandroid_mvvmhabit.entity.UserPageBean;
import com.bedrock.wanandroid_mvvmhabit.entity.base.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 */
public interface WanAndroidAPI {


    @GET("https://v2.jinrishici.com/token")
    Observable<BaseResponse<String>> getJinrishiciToken();

    @GET("https://v2.jinrishici.com/sentence")
    Observable<BaseResponse<JinrishiciBean>> getJinrishici(@retrofit2.http.Header("Token") String token);

    @GET("https://gitee.com/goweii/WanAndroidServer/raw/master/update/update.json")
    Observable<BaseResponse<UpdateBean>> update();

    @GET("https://gitee.com/goweii/WanAndroidServer/raw/master/about/about_me.json")
    Observable<BaseResponse<AboutMeBean>> getAboutMe();

    @GET("https://gitee.com/goweii/WanAndroidServer/raw/master/config/config.json")
    Observable<BaseResponse<ConfigBean>> getConfig();

    /**
     * 登录
     * 方法： POST
     * 参数：
     * username，password
     * 登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<LoginBean>> login(@Field("username") String username,
                                              @Field("password") String password);

    /**
     * 注册
     * 方法： POST
     * 参数：
     * username，password,repassword
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponse<LoginBean>> register(@Field("username") String username,
                                                @Field("password") String password,
                                                @Field("repassword") String repassword);

    /**
     * 退出
     * 方法： GET
     * 访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
     * 如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，及时清理。
     */
    @GET("user/logout/json")
    Observable<BaseResponse<BaseBean>> logout();

    /**
     * 获取公众号列表
     * 方法： GET
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<ChapterBean>>> getWxArticleChapters();

    /**
     * 查看某个公众号历史数据
     * 方法：GET
     * 参数：
     * 公众号 ID：拼接在 url 中，eg:405
     * 公众号页码：拼接在 url 中，eg:1
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getWxArticleList(@Path("id") int id,
                                                               @Path("page") int page);

    /**
     * 在某个公众号中搜索历史文章
     * 方法：GET
     * 参数：
     * k : 字符串，eg:Java
     * 公众号 ID：拼接在 url 中，eg:405
     * 公众号页码：拼接在 url 中，eg:1
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getWxArticleList(@Path("id") int id,
                                                              @Path("page") int page,
                                                              @Query("k") String key);

    /**
     * 项目分类
     * 方法： GET
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ChapterBean>>> getProjectChapters();

    /**
     * 项目列表数据
     * 方法：GET
     * 参数：
     * cid 分类的id，上面项目分类接口
     * 页码：拼接在链接中，从1开始。
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getProjectArticleList(@Path("page") int page,
                                                                   @Query("cid") int id);

    /**
     * 置顶文章
     * 方法：GET
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<ArticleBean>>> getTopArticleList();

    /**
     * 首页文章列表
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getArticleList(@Path("page") int page);

    /**
     * 首页banner
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBanner();

    /**
     * 常用网站
     */
    @GET("friend/json")
    Observable<BaseResponse<List<UsefulWebBean>>> getUsefulWebList();

    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKeyBean>>> getHotKeyList();

    /**
     * 搜索
     * 方法：POST
     * 参数：
     * 页码：拼接在链接上，从0开始。
     * k ： 搜索关键词
     * 支持多个关键词，用空格隔开
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Observable<BaseResponse<ArticleListBean>> search(@Path("page") int page,
                                                    @Field("k") String key);

    /**
     * 搜索热词
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NaviBean>>> getNaviList();

    /**
     * 体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<ChapterBean>>> getKnowledgeList();

    /**
     * 知识体系下的文章
     * 方法：GET
     * 参数：
     * cid 分类的id，上述二级目录的id
     * 页码：拼接在链接上，从0开始。
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getKnowledgeArticleList(@Path("page") int page,
                                                                     @Query("cid") int id);

    /**
     * 收藏文章列表
     * 方法：GET
     * 参数： 页码：拼接在链接中，从0开始。
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getCollectArticleList(@Path("page") int page);

    /**
     * 收藏网站列表
     * 方法：GET
     */
    @GET("lg/collect/usertools/json")
    Observable<BaseResponse<List<CollectionLinkBean>>> getCollectLinkList();

    /**
     * 收藏站内文章
     * 方法：POST
     * 参数： 文章id，拼接在链接中。
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<BaseBean>> collect(@Path("id") int id);

    /**
     * 收藏站外文章
     * 方法：POST
     * 参数：
     * title，author，link
     */
    @FormUrlEncoded
    @POST("lg/collect/add/json")
    Observable<BaseResponse<ArticleBean>> collect(@Field("title") String title,
                                                 @Field("author") String author,
                                                 @Field("link") String link);

    /**
     * 收藏网址
     * 方法：POST
     * 参数：
     * name,link
     */
    @FormUrlEncoded
    @POST("lg/collect/addtool/json")
    Observable<BaseResponse<CollectionLinkBean>> collect(@Field("name") String name,
                                                        @Field("link") String link);

    /**
     * 取消收藏 文章列表
     * 方法：POST
     * 参数：
     * id:拼接在链接上 id传入的是列表中文章的id。
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<BaseBean>> uncollect(@Path("id") int id);

    /**
     * 删除收藏网站
     * 方法：POST
     * 参数：
     * id
     */
    @FormUrlEncoded
    @POST("lg/collect/deletetool/json")
    Observable<BaseResponse<BaseBean>> uncollectLink(@Field("id") int id);

    /**
     * 编辑收藏网站
     * 方法：POST
     * 参数：
     * id,name,link
     */
    @FormUrlEncoded
    @POST("lg/collect/updatetool/json")
    Observable<BaseResponse<CollectionLinkBean>> updateCollectLink(@Field("id") int id,
                                                                  @Field("name") String name,
                                                                  @Field("link") String link);

    /**
     * 取消收藏 我的收藏页面（该页面包含自己录入的内容）
     * 方法：POST
     * 参数：
     * id:拼接在链接上
     * originId:列表页下发，无则为-1
     * originId 代表的是你收藏之前的那篇文章本身的id； 但是收藏支持主动添加，这种情况下，没有originId则为-1
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    Observable<BaseResponse<BaseBean>> uncollect(@Path("id") int id,
                                                @Field("originId") int originId);

    /**
     * 获取个人积分
     */
    @GET("lg/coin/getcount/json")
    Observable<BaseResponse<Integer>> getCoin();

    /**
     * 获取个人积分
     */
    @GET("lg/coin/userinfo/json")
    Observable<BaseResponse<UserInfoBean>> getUserInfo();

    /**
     * 获取个人积分获取列表
     * page 1开始
     */
    @GET("lg/coin/list/{page}/json")
    Observable<BaseResponse<CoinRecordBean>> getCoinRecordList(@Path("page") int page);

    /**
     * 积分排行榜接口
     * page 1开始
     */
    @GET("coin/rank/{page}/json")
    Observable<BaseResponse<CoinRankBean>> getCoinRankList(@Path("page") int page);

    /**
     * 广场列表数据
     * 可能出现返回列表数据<每页数据，因为有自见的文章被过滤掉了。
     * page 0开始
     */
    @GET("user_article/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getUserArticleList(@Path("page") int page);

    /**
     * 分享人对应列表数据
     * page 从1开始
     */
    @GET("user/{userId}/share_articles/{page}/json")
    Observable<BaseResponse<UserPageBean>> getUserPage(@Path("userId") int userId,
                                                       @Path("page") int page);

    /**
     * 自己的分享的文章列表
     * 页码，从1开始
     */
    @GET("user/lg/private_articles/{page}/json")
    Observable<BaseResponse<UserPageBean>> getMineShareArticleList(@Path("page") int page);

    /**
     * 删除自己分享的文章
     * 文章id，拼接在链接上
     */
    @POST("lg/user_article/delete/{id}/json")
    Observable<BaseResponse<BaseBean>> deleteMineShareArticle(@Path("id") int id);

    /**
     * 分享文章
     * 注意需要登录后查看，如果为CSDN，简书等链接会直接通过审核，在对外的分享文章列表中展示。
     * title
     * link
     */
    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    Observable<BaseResponse<BaseBean>> shareArticle(@Field("title") String title,
                                                   @Field("link") String link);

}
