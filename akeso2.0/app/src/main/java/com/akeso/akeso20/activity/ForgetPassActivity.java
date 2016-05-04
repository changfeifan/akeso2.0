package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akeso.akeso20.MainActivity;
import com.akeso.akeso20.R;
import com.akeso.akeso20.thread.PostLogin;
import com.akeso.akeso20.thread.PostResetPassword;
import com.akeso.akeso20.thread.PostSendCode;

import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/27.
 */
public class ForgetPassActivity extends Activity implements View.OnClickListener {

    TextView tv_next;
    TextView tv_verify;
    EditText et_phone;
    EditText et_pass;
    EditText et_confirm;
    EditText et_verify;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, ForgetPassActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        setView();
    }

    private void setView() {
        tv_next = (TextView) findViewById(R.id.tv_next);
        tv_verify = (TextView) findViewById(R.id.tv_verify);
        et_confirm = (EditText) findViewById(R.id.et_confirm);
        tv_verify = (TextView) findViewById(R.id.tv_verify);
        tv_verify.setOnClickListener(this);
        et_verify = (EditText) findViewById(R.id.et_verify);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pass = (EditText) findViewById(R.id.et_pass);

        findViewById(R.id.tv_next).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.iv_back:
                finish();
                break;
            case R.id.tv_verify:
                new PostSendCode(et_phone.getText().toString()) {
                    @Override
                    protected void onPostExecute(JSONObject object) {
                        super.onPostExecute(object);
                        if (object.optString("code").equals("201")) {
                            Toast.makeText(ForgetPassActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgetPassActivity.this, "验证码发送失败，请检查手机号", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute();
                break;
            case R.id.tv_next:
                if (check()) {
                    if (et_verify.getText().toString().equals("")) {
                        Toast.makeText(ForgetPassActivity.this, "验证码为空，请重新填写", Toast.LENGTH_SHORT).show();
                    } else {
                        new PostResetPassword(et_phone.getText().toString(),
                                et_verify.getText().toString(), et_pass.getText().toString()) {
                            @Override
                            protected void onPostExecute(JSONObject object) {
                                super.onPostExecute(object);
                                if (object.optInt("code") == 201) {
                                    new PostLogin(et_phone.getText().toString(), et_pass.getText().toString()) {
                                        @Override
                                        protected void onPostExecute(JSONObject object) {
                                            super.onPostExecute(object);
                                            if (object.optInt("code") == 200) {
                                                SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
                                                sharedPreferences.edit().putString("token", object.optString("access_token")).commit();
                                                MainActivity.show(ForgetPassActivity.this);
                                                finish();
                                            }
                                        }
                                    }.execute();
                                } else {
                                    Toast.makeText(ForgetPassActivity.this, "重置密码失败，请重新尝试", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }.execute();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean check() {
        if (et_phone.getText().toString().equals("")) {
            Toast.makeText(ForgetPassActivity.this, "电话为空，请重新填写", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_pass.getText().toString().equals("")) {
            Toast.makeText(ForgetPassActivity.this, "密码为空，请重新尝试登陆", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!et_pass.getText().toString().equals(et_confirm.getText().toString())) {
            Toast.makeText(ForgetPassActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        } else {

        }
        return true;
    }
}
