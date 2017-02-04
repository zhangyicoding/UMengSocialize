package com.umeng.soexample.umengsocialize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.umengsocialize.R;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public void auth(View view) {
        switch (view.getId()) {
            case R.id.sina_btn:
                authFromPlatform(SHARE_MEDIA.SINA);
                break;
            case R.id.wechat_btn:
                authFromPlatform(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.qq_btn:
                authFromPlatform(SHARE_MEDIA.QQ);
                break;
        }
    }


    private void authFromPlatform(SHARE_MEDIA platfom) {
        UMShareAPI mShareAPI = UMShareAPI.get(AuthActivity.this);
        mShareAPI.doOauthVerify(AuthActivity.this, platfom, umAuthListener);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            Log.d("ee", "data size : " + data.size());
            Set<Map.Entry<String, String>> entries = data.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d("ee", "key : " + key + ", value : " + value);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
