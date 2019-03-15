package com.example.john.LoginRegister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.john.happynews.NewsActivity;
import com.example.john.util.OperateUserAccount;
import com.example.john.happynews.R;
import com.example.john.util.ProgressGenerator;

import org.litepal.LitePal;

public class LoginActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener {
    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    private String isSuccess;
    private EditText editEmail;
    private EditText editPassword;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton btnSignIn;
    private String username;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox remember;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.getDatabase();
        editEmail = (EditText) findViewById(R.id.edit_email);
        editPassword = (EditText) findViewById(R.id.edit_password);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        remember = (CheckBox)findViewById(R.id.checkbox);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            String Email = pref.getString("Email","");
            String Password = pref.getString("Password","");
            editEmail.setText(Email);
            editPassword.setText(Password);
            remember.setChecked(true);
        }
        Intent intent = getIntent();
        if(intent.hasExtra("email")){
            editEmail.setText(intent.getStringExtra("email"));
        }
        if(intent.hasExtra("password")){
            editPassword.setText(intent.getStringExtra("password"));
        }
        progressGenerator = new ProgressGenerator(this);
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.getBoolean(EXTRAS_ENDLESS_MODE)) {
            btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        } else {
            btnSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
        }
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator = new ProgressGenerator(LoginActivity.this);
                progressGenerator.start(btnSignIn);
                btnSignIn.setEnabled(false);
                editEmail.setEnabled(false);
                editPassword.setEnabled(false);
                isSuccess = OperateUserAccount.Login(editEmail.getText().toString(),editPassword.getText().toString());
            }
        });
    }

    @Override
    public void onComplete() {
        if(isSuccess!=null){
            editor = pref.edit();
            if(remember.isChecked()){
                editor.putBoolean("remember_password",true);
                editor.putString("Email",editEmail.getText().toString());
                editor.putString("Password",editPassword.getText().toString());
            }
            else {
                editor.clear();
            }
            editor.apply();
            Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,NewsActivity.class);
            intent.putExtra("username",isSuccess);
            startActivity(intent);
            finish();
        }
        else{
            btnSignIn.setProgress(-1);
            Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
            btnSignIn.setEnabled(true);
            editEmail.setEnabled(true);
            editPassword.setEnabled(true);
        }
    }

    public void btnRegister(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    private static Boolean checkEmail(String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return true;
        }
        else {
            return false;
        }
    }
}
