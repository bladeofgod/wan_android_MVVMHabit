package com.bedrock.wanandroid_mvvmhabit.entity;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.bedrock.wanandroid_mvvmhabit.entity.base.BaseEntity;

import java.util.Objects;



/**
 * @author CuiZhen
 * @date 2019/11/2
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class CollectArticleEntity extends BaseEntity {
    private boolean collect = false;
    private int articleId = -1;
    private int collectId = -1;
    private String title = "";
    private String author = "";
    private String url = "";

    public CollectArticleEntity() {
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectArticleEntity entity = (CollectArticleEntity) o;
        return Objects.equals(url, entity.url);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
