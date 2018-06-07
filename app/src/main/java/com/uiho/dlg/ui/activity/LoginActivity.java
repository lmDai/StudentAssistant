package com.uiho.dlg.ui.activity;

import android.support.v4.content.ContextCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.uiho.dlg.MainActivity;
import com.uiho.dlg.R;
import com.uiho.dlg.base.ui.BaseMvpActivity;
import com.uiho.dlg.ui.widget.ClearableEditText;
import com.uiho.dlg.util.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity {


    @BindView(R.id.edit_num)
    ClearableEditText editNum;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.iv_showPassword)
    ImageView ivShowPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_tour)
    Button btnTour;
    private Boolean showPassword = true;//密码是否明文显示

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    @OnClick({R.id.iv_showPassword, R.id.btn_login, R.id.btn_tour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_showPassword:
                if (showPassword) {// 显示密码
                    ivShowPassword.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_visible));
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editPassword.setSelection(editPassword.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    ivShowPassword.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_invisible));
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editPassword.setSelection(editPassword.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;
            case R.id.btn_login:
                break;
            case R.id.btn_tour:
                UIUtils.getInstance().goActivity(mContext, MainActivity.class);
                finish();
                break;
        }
    }
}
