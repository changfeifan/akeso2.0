package com.akeso.akeso20.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.akeso.akeso20.MainActivity;
import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/4/19.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    boolean isLoginView = true;

    EditText et_phone;
    EditText et_pass;
    EditText et_confirm;
    TextView tv_verify;
    TextView tv_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_view:
                if (isLoginView) {
                    setContentView(R.layout.activity_register);
                    setView();
                    isLoginView = false;
                } else {
                    setContentView(R.layout.activity_login);
                    setView();
                    isLoginView = true;
                }
                break;
            case R.id.tv_next:
                if (isLoginView) {
                    if (checkLogin()) {
                        //登陆接口方法
                        MainActivity.show(this);
                    } else {

                    }
                } else {
                    if (checkRegister()) {
                        //注册接口方法
                    } else {

                    }
                }
                break;
            case R.id.tv_verify:
                //获取验证码接口
                break;
            case R.id.tv_forget:
                //忘记密码页面
                break;
            default:
                break;
        }
    }

    private void setView() {
        findViewById(R.id.tv_change_view).setOnClickListener(this);
        findViewById(R.id.tv_next).setOnClickListener(this);

        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pass = (EditText) findViewById(R.id.et_pass);


        if (!isLoginView) {
            et_confirm = (EditText) findViewById(R.id.et_confirm);
            tv_verify = (TextView) findViewById(R.id.tv_verify);
//            tv_verify.setOnClickListener(this);
        } else {
            tv_forget = (TextView) findViewById(R.id.tv_forget);
        }
    }


    private boolean checkLogin() {

        return true;
    }

    private boolean checkRegister() {

        return true;
    }
}
