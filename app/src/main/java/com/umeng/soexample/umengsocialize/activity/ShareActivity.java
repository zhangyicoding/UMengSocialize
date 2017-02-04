package com.umeng.soexample.umengsocialize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.soexample.umengsocialize.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        String s = String.format("%s idea%d", "good", 1);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void share(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("title", "分享标题");
        bundle.putString("content", "分享内容");
        bundle.putString("img", "https://www.baidu.com/img/bd_logo1.png");
        bundle.putString("target", "https://www.baidu.com/");

        switch (view.getId()) {
            case R.id.sina_btn:
                shareToPlatform(SHARE_MEDIA.SINA, bundle);
                break;
            case R.id.wechat_btn:
                shareToPlatform(SHARE_MEDIA.WEIXIN, bundle);
                break;
            case R.id.wechat_circle_btn:
                shareToPlatform(SHARE_MEDIA.WEIXIN_CIRCLE, bundle);
                break;
            case R.id.wechat_favorite_btn:
                shareToPlatform(SHARE_MEDIA.WEIXIN_FAVORITE, bundle);
                break;
            case R.id.qq_btn:
                shareToPlatform(SHARE_MEDIA.QQ, bundle);
                break;
            case R.id.qzone_btn:
                shareToPlatform(SHARE_MEDIA.QZONE, bundle);
                break;
        }
    }

    // 分享指定平台
    private void shareToPlatform(SHARE_MEDIA platform, Bundle bundle) {
        String title = bundle.getString("title");
        String content = bundle.getString("content");
        String imgUrl = bundle.getString("img");
        String targetUrl = bundle.getString("target");

        new ShareAction(ShareActivity.this)
                .setPlatform(platform)
                .withText(content)
                .withTitle(title)
                .withMedia(new UMImage(this, imgUrl))
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(ShareActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
