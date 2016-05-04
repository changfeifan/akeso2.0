package com.akeso.akeso20.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/4/28.
 */
public class UserInfoActivity extends Activity implements View.OnClickListener {

    TextView tv_male;
    TextView tv_female;
    TextView tv_next;
    EditText et_birth;
    EditText et_height;
    EditText et_weight;
    EditText et_profession;
    EditText et_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_user_infomation);

        setView();
    }

    private void setView() {
        tv_next=(TextView)findViewById(R.id.tv_next);
        tv_male = (TextView) findViewById(R.id.tv_male);
        tv_female = (TextView) findViewById(R.id.tv_male);

        tv_female.setOnClickListener(this);
        tv_male.setOnClickListener(this);

        et_birth=(EditText)findViewById(R.id.et_birth);
        et_height=(EditText)findViewById(R.id.et_height);
        et_position=(EditText)findViewById(R.id.et_position);
        et_profession=(EditText)findViewById(R.id.et_profession);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_male:
                tv_male.setBackgroundResource(R.drawable.sharp_white_left);
                tv_male.setTextColor(getResources().getColor(R.color.black));
                tv_female.setBackgroundResource(R.drawable.sharp_blue_deep_right);
                tv_female.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_female:
                tv_male.setBackgroundResource(R.drawable.sharp_blue_deep_left);
                tv_male.setTextColor(getResources().getColor(R.color.white));
                tv_female.setBackgroundResource(R.drawable.sharp_white_right);
                tv_female.setTextColor(getResources().getColor(R.color.black));
                break;
            default:
                break;
        }
    }
}
