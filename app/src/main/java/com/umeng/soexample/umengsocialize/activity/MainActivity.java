package com.umeng.soexample.umengsocialize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.soexample.umengsocialize.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void share(View view) {
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }

    public void auth(View view) {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

}
