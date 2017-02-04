package com.umeng.soexample.umengsocialize.application;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initSocialize();
    }

    private void initSocialize() {
        UMShareAPI.get(this);
//        Config.REDIRECT_URL = "http://sns.whalecloud.com";
//        Config.REDIRECT_URL = "http://www.sina.com";
    }

    {
        //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx12342956d1cab4f9", "a5ae111de7d9ea137e88a5e02c07c94d");
//        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //QQ/Qzone
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

}
