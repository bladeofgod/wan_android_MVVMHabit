package com.bedrock.wanandroid_mvvmhabit.ui.login;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.bedrock.wanandroid_mvvmhabit.data.MainRepository;
import com.bedrock.wanandroid_mvvmhabit.entity.LoginBean;

import java.util.Observable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @author LiJiaqi
 * @date 2020/2/15
 * Description:
 * MainRepository: 内部进行 网络API接口调用  和 本地缓存调用,以获取数据
 */
public class LoginViewModel extends BaseViewModel<MainRepository> {

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    //默认值是0
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable{
        //密码可见的开关
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull Application application, MainRepository model) {
        super(application, model);
        //从缓存中取得数据
        userName.set("13141191081");
        password.set("ljq594566");
//        userName.set(model.getUserName());
//        password.set(model.getPassword());
    }

    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand pwdShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || ! uc.pSwitchEvent.getValue());
        }
    });

    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //登录
            login();
        }
    });



    private void login(){
        if(TextUtils.isEmpty(userName.get())){
            ToastUtils.showShort("请输入账号!");
            return;
        }
        if(TextUtils.isEmpty(password.get())){
            ToastUtils.showShort("请输入密码!");
            return;
        }

        //将 viewModel 与当前activity 进行生命周期的绑定
        addSubscribe(
                //网络登录请求
                model.login(userName.get(), password.get())
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //请求前置性
                        showDialog();

                    }
                })
                .subscribe(new Consumer<BaseResponse<LoginBean>>() {
                    @Override
                    public void accept(BaseResponse<LoginBean> o) throws Exception {
                        dismissDialog();
                        KLog.i("login " + o.getMessage());
                        //ToastUtils.showShort("登陆成功 " + o.getResult().getUsername());
                    }
                }));

    }



}























