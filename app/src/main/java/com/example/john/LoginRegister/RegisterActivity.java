package com.example.john.LoginRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.john.util.OperateUserAccount;
import com.example.john.happynews.R;
import com.example.john.util.ProgressGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener{

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    boolean isSuccess;
    boolean isEmail;
    boolean isMobile;
    EditText editUser;
    EditText editEmail;
    EditText editConfirm;
    EditText editMobile;
    EditText editPassword;
    String confirm;
    String password;
    ProgressGenerator progressGenerator;
    ActionProcessButton btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUser = (EditText) findViewById(R.id.edit_Username);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editConfirm = (EditText) findViewById(R.id.edit_confirmpassword);
        editMobile = (EditText) findViewById(R.id.edit_mobile);
        editPassword = (EditText) findViewById(R.id.edit_password);
        progressGenerator = new ProgressGenerator(this);
        btnRegister = (ActionProcessButton) findViewById(R.id.btnRegister);
        btnRegister.setMode(ActionProcessButton.Mode.ENDLESS);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator = new ProgressGenerator(RegisterActivity.this);
                progressGenerator.start(btnRegister);
                String email = editEmail.getText().toString();
                String mobile = editMobile.getText().toString();
                confirm = editConfirm.getText().toString();
                password = editPassword.getText().toString();
                isEmail = checkEmail(email);
                isMobile = checkMobile(mobile);
                btnRegister.setEnabled(false);
                editEmail.setEnabled(false);
                editPassword.setEnabled(false);
                editConfirm.setEnabled(false);
                editMobile.setEnabled(false);
                editUser.setEnabled(false);
            }
        });
    }
    @Override
    public void onComplete() {
        if(!isEmail){
            Toast.makeText(this, "Please check the format of the email adress", Toast.LENGTH_LONG).show();
            failed();
            return;
        }
        if(!isMobile){
            Toast.makeText(this, "Please check the format of the phone number", Toast.LENGTH_SHORT).show();
            failed();
            return;
        }
        if(!confirm.equals(password)){
            Toast.makeText(this, "The passwords entered do not match", Toast.LENGTH_SHORT).show();
            failed();
            return;
        }
        isSuccess = OperateUserAccount.Register(editUser.getText().toString(),
                editEmail.getText().toString(),
                editMobile.getText().toString(),
                editPassword.getText().toString());
        if(!isSuccess)
        {
            Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show();
            failed();
            return;
        }
        else
        {
            Toast.makeText(this, "Register success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            intent.putExtra("email",editEmail.getText().toString());
            intent.putExtra("password",editPassword.getText().toString());
            startActivity(intent);
            finish();
            return;
        }
    }

    public void btnRegister(View view){
        Toast.makeText(RegisterActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
    }
    private void failed(){
        btnRegister.setProgress(-1);
        btnRegister.setEnabled(true);
        editEmail.setEnabled(true);
        editPassword.setEnabled(true);
        editConfirm.setEnabled(true);
        editMobile.setEnabled(true);
        editUser.setEnabled(true);
    }
    private static Boolean checkEmail(String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkMobile(String mobile) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
