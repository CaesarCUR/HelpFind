package com.example.codejam.helpfind;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int REQUEST_LOGIN = 0;

    @BindView(R.id.input_account) EditText _userName;
    @BindView(R.id.input_password) EditText _password;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Setting Click Event for Login Button
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Setting click event for Signup Link
        _signupLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: add link-to action at here
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: execute sign up successful logic at here
                // by default, we just finish this activity
                this.finish();
            }
        } else if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        moveTaskToBack(true);
    }

    /**
     * Login action method
     *
     * before a user login, we should check whether the valid of this login action
     * if login action is invalid: login failure
     * if login action is valid: do something
     */
    private void login() {
        if (!checkValid()) {
            //TODO: execute login failure action
            onLoginFailed();
            return;
        }

        // disabled login button for misusing
        _loginButton.setEnabled(false);

        // launch progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String account = _userName.getText().toString();
        String password = _password.getText().toString();

        //TODO: implement authentication logical action at here

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // remove progress dialog
                        progressDialog.dismiss();
                        // After called, call either login success or login failed
                        onLoginSuccess();
                    }
                }, 3000);

    }

    /**
     * CheckValid method
     *
     * this method execute the task of checking valid of this login, we should consider the valid
     * from these aspects:
     * 1) account and password cannot be blank, return false if blank
     * 2) account and password should mathed with each other
     */
    private boolean checkValid() {
        //TODO: implement the logical task of checking valid
        String account = _userName.getText().toString();
        String password = _password.getText().toString();

        if (account.isEmpty() || password.isEmpty()) {
            _userName.setError("Please enter a valid account !");
            return false;
        } else {
            _userName.setError(null);
        }

        return true;
    }

    /**
     * When user login success, jump to BroadcastSquareActivity
     */
    private void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), BroadcastSquareActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login falied", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }
}
