package com.example.john.happynews;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.john.util.OperateUserAccount;
import com.sayagodshala.authui.AuthUIFragment;
import com.sayagodshala.authui.AuthUIFragment.AuthUIFragmentListener;
import com.sayagodshala.authui.AuthUISettings;
import com.sayagodshala.authui.AuthUIView;
import com.sayagodshala.authui.MaterialTheme;

public class MainActivity extends AppCompatActivity implements AuthUIFragmentListener{

    protected Handler mHandler =  new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
        AuthUISettings authUISettings = new AuthUISettings();
        authUISettings.setSocialPlatformRequired(false);
        authUISettings.setAppLogoRequired(true);
        authUISettings.setTermsRequired(true);
        authUISettings.setSignupRequired(true);
        authUISettings.setFacebookLoginRequired(false);
        authUISettings.setGoogleLoginRequired(false);
        authUISettings.setForgotPasswordRequired(true);
        authUISettings.setAppLogo(R.mipmap.newsicon);
        authUISettings.setLoginTitle("Login using your registered email and password.");
        authUISettings.setSignupTitle("You are just few steps a head. Register and start a head.");
        authUISettings.setForgotPasswordTitle("Put in your email id for password reset link");
        authUISettings.setLoginTerms("By Logging in I agree to the Terms of Use");
        authUISettings.setSignupTerms("By Signing up I agree to the Terms of Use");
        authUISettings.setLoginToggleTitle("Have an account? LOGIN");
        authUISettings.setSignupToggleTitle("Don\'t have an account? SIGN UP");
        authUISettings.setDefaultView(AuthUIView.LOGIN);
        authUISettings.setMaterialTheme(MaterialTheme.WHITE);
        AuthUIFragment authUIFragment;
        authUIFragment = AuthUIFragment.newInstance(authUISettings);

        AuthUIFragment.loadFragment(this, authUIFragment, R.id.frame);
    }

    @Override
    public void onLoginClicked(String username, String password) {
//        DragSortDialog dialog = new DragSortDialog(this);
//        dialog.setTopItemViews("ABCDEFG".split("\\B"));
//        dialog.setBottomItemViews("OPQRST".split("\\B"));
//        dialog.show();
        String isSuccess = OperateUserAccount.Login(username,password);
        if(isSuccess!=null){
            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,NewsActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSignupClicked(String name, String email, String mobile, String password) {
        Log.d("MainActivity","onSignupClicked");
        boolean isSuccess = OperateUserAccount.Register(name, email, mobile, password);
        if(isSuccess){
            Toast.makeText(MainActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onForgotPasswordClicked(String email) {
        Log.d("MainActivity","onForgotPasswordClicked");
        Toast.makeText(MainActivity.this,"onForgotPasswordClicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookClicked(boolean isRegistration) {
        Log.d("MainActivity","onFacebookClicked");
    }

    @Override
    public void onGoogleClicked(boolean isRegistration) {
        Log.d("MainActivity","onGoogleClicked");
    }
}
