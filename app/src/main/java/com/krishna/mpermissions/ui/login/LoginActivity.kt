package com.krishna.mpermissions.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.krishna.mpermissions.databinding.ActivityLoginBinding
import com.krishna.mpermissions.ui.dashboard.DashboardActivity
import com.krishna.mpermissions.utils.*
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.String

class LoginActivity : AppCompatActivity(), LoginNavigator {

    private lateinit var mLoginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    companion object {
        fun getInstance(activity: Activity): Intent {
            return Intent(activity, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, ProjectLayouts.activity_login)

        mLoginViewModel = LoginViewModel(this, this)

        binding.loginViewModel = mLoginViewModel
        binding.loginDataModel = LoginReqModel()


        val s = String.format(resources.getString(ProjectStrings.proceed_with_your_login))
        binding.tvProceedTitle.text = getSpannedText(s)

    }

    override fun onLoginSuccess(loginResModel: LoginResModel) {
        til_email.error = ""
        til_password.error = ""
        til_email.isEnabled = true
        til_password.isEnabled = true
        card_login.isEnabled = true
        login_progress.visibility = View.INVISIBLE
        tv_btn_login.visibility = View.VISIBLE

        when (SUCCESS) {
            loginResModel.status_code -> {
                ToastUtils.showShortToast(this, loginResModel.message)
                finish()
                AppPreference.saveAccessToken(this, loginResModel.data.accesstoken)
                AppPreference.saveParentData(this, loginResModel)
                startActivity(DashboardActivity.getInstance(this))
            }
            else -> {
                ToastUtils.showShortToast(this, loginResModel.message)
            }
        }
    }

    override fun onLoginFailed(error: kotlin.String) {
        ToastUtils.showShortToast(this, error)
        til_email.isEnabled = true
        til_password.isEnabled = true
        card_login.isEnabled = true
        login_progress.visibility = View.INVISIBLE
        tv_btn_login.visibility = View.VISIBLE
    }

    override fun onForgetPassword() {
    }

    override fun inValidEmail(error: kotlin.String) {
        til_email.error = error
        til_email.requestFocus()
    }

    override fun inValidPassword(error: kotlin.String) {
        til_email.error = ""
        til_password.error = error
        til_password.requestFocus()
    }

    override fun clearErrors() {
        til_email.error = ""
        til_password.error = ""
        til_email.isEnabled = false
        til_password.isEnabled = false
        card_login.isEnabled = false
        login_progress.visibility = View.VISIBLE
        tv_btn_login.visibility = View.INVISIBLE
    }

}