package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akeso.akeso20.MainActivity;
import com.akeso.akeso20.R;
import com.akeso.akeso20.thread.GetMe;
import com.akeso.akeso20.thread.PostCheckPhone;
import com.akeso.akeso20.thread.PostLogin;
import com.akeso.akeso20.thread.PostRegister;
import com.akeso.akeso20.thread.PostSendCode;

import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/19.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    boolean isLoginView = true;

    EditText et_phone;
    EditText et_pass;
    EditText et_confirm;
    EditText et_verify;
    TextView tv_verify;
    TextView tv_forget;

    Handler handler = new Handler();
    int sec = 60;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setView();
        SharedPreferences sharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);

        //免登陆，请求token，返回200为成功
        new GetMe(sharedPreferences.getString("token", "")) {
            @Override
            protected void onPostExecute(JSONObject object) {
                super.onPostExecute(object);
                if (object.optInt("code") == 200) {


//                    MainActivity.show(LoginActivity.this);
//                    finish();
                } else {

                }
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_view:
                if (isLoginView) {
                    setContentView(R.layout.activity_register);
                    isLoginView = false;
                    setView();
                } else {
                    setContentView(R.layout.activity_login);
                    isLoginView = true;
                    setView();
                }
                break;
            case R.id.tv_next:
                if (isLoginView) {
                    if (checkLogin()) {
                        //登陆接口方法
                        if (et_phone.getText().toString().equals("")) {
                            Toast.makeText(LoginActivity.this, "用户名为空，请重新填写", Toast.LENGTH_SHORT).show();
                        } else if (et_pass.getText().toString().equals("")) {
                            Toast.makeText(LoginActivity.this, "密码为空，请重新尝试登陆", Toast.LENGTH_SHORT).show();
                        } else {
                            new PostLogin(et_phone.getText().toString(), et_pass.getText().toString()) {
                                @Override
                                protected void onPostExecute(JSONObject object) {
                                    super.onPostExecute(object);
                                    if (object.optInt("code") == 200) {
                                        SharedPreferences sharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
                                        sharedPreferences.edit().putString("token",object.optString("token_type")+" "+ object.optString("access_token")).commit();
                                        MainActivity.show(LoginActivity.this);
//                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "用户名或者密码错误，请重新尝试登陆", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }.execute();
                        }
                    } else {

                    }
                } else {
                    if (checkRegister()) {
                        //注册接口方法
                        if (et_verify.getText().toString().equals("")) {
                            Toast.makeText(LoginActivity.this, "验证码为空，请重新填写", Toast.LENGTH_SHORT).show();
                        } else {
                            new PostRegister(et_verify.getText().toString(), et_phone.getText().toString(), et_pass.getText().toString()) {
                                @Override
                                protected void onPostExecute(JSONObject object) {
                                    super.onPostExecute(object);
                                    if (object.optInt("code") == 201) {
                                        new PostLogin(et_phone.getText().toString(), et_pass.getText().toString()) {
                                            @Override
                                            protected void onPostExecute(JSONObject object) {
                                                super.onPostExecute(object);
                                                if (object.optInt("code") == 200) {
                                                    SharedPreferences sharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
                                                    sharedPreferences.edit().putString("token", object.optString("access_token")).commit();
                                                    MainActivity.show(LoginActivity.this);
//                                                    finish();
                                                }
                                            }
                                        }.execute();
//                                        MainActivity.show(LoginActivity.this);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }.execute();
                        }
                    } else {

                    }
                }
                break;
            case R.id.tv_verify:
                //获取验证码接口
                //先检测手机状态
                new PostCheckPhone(et_phone.getText().toString()) {
                    @Override
                    protected void onPostExecute(JSONObject object) {
                        super.onPostExecute(object);
                        if (object.optInt("code") == 201) {
                            new PostSendCode(et_phone.getText().toString()) {
                                @Override
                                protected void onPostExecute(JSONObject object) {
                                    super.onPostExecute(object);
                                    if (object.optString("code").equals("201")) {
                                        Toast.makeText(LoginActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "验证码发送失败，请检查手机号", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }.execute();
                            handler.postDelayed(runnable, 500);
                            tv_verify.setClickable(false);
                        }

                    }
                }.execute();


                break;
            case R.id.tv_forget:
                //忘记密码页面
                ForgetPassActivity.show(LoginActivity.this);
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
            tv_verify.setOnClickListener(this);
            et_verify = (EditText) findViewById(R.id.et_verify);
        } else {
            tv_forget = (TextView) findViewById(R.id.tv_forget);
            tv_forget.setOnClickListener(this);
        }
    }


    private boolean checkLogin() {

        return true;
    }

    private boolean checkRegister() {
        if (et_phone.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "电话为空，请重新填写", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_pass.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "密码为空，请重新尝试登陆", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!et_pass.getText().toString().equals(et_confirm.getText().toString())) {
            Toast.makeText(LoginActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        } else {

        }
        return true;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (sec == 0) {
                    tv_verify.setText("获取验证码");
                    sec = 60;
                    tv_verify.setClickable(true);
                } else {
                    tv_verify.setText(sec + "s");
                    sec--;
                    handler.postDelayed(runnable, 1000);
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    };
}
