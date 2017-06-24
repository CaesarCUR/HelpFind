package com.example.codejam.helpfind;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhouming on 2017/6/19.
 */

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    public static final String USER_NAME = "username";
    public static final String PASS_TOKEN = "password";

    @BindView(R.id.input_sign_account) EditText _account;
    @BindView(R.id.input_sign_email) EditText _email;
    @BindView(R.id.input_sign_password) EditText _password;
    @BindView(R.id.btn_signup) Button _signup;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        // Setting click event for Sign up button
        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: execute sign up action at here
                signup();
            }
        });

        // Setting link to event for Sign in link
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement link to sign in action at here
                finish();  // kill current activity, back to sign in activity
            }
        });
    }

    /**
     * Sign up method implemented for sign up action
     *
     * accept three params:
     * 1) account name
     * 2) email address
     * 3) password input
     *
     * before we submit the sign up action, we need verify the valid of account (exactly the email
     * address)
     */
    private void signup() {
        if (!isValid()) {
            // TODO: execute sign up failure action
            onSignupFailed();
            return;
        }

        // Disable the signup button for misusing
        _signup.setEnabled(false);

        // launch progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Create account...");
        progressDialog.show();

        String account = _account.getText().toString();
        String email = _email.getText().toString();
        String password = _password.getText().toString();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: execute sign up authentic logic and sign up success action at here
                progressDialog.dismiss();
                onSignupSuccess();
            }
        }, 3000);
    }

    /**
     * On sign up success, we back to login activity and fill sign up information automatically for
     * our user
     */
    private void onSignupSuccess() {
        _signup.setEnabled(true);
        setResult(RESULT_OK, null);

        // TODO: execute back to login activity logic
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.putExtra(USER_NAME, _account.getText().toString());
        intent.putExtra(PASS_TOKEN, _password.getText().toString());
        startActivity(intent);

        finish();
    }

    private void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();
        _signup.setEnabled(true);
    }

    /**
     * Valid method offers a way to verify the validation of current sign up account information
     *
     * @return true if valid, or false if invalid
     */
    private boolean isValid() {
        boolean valid = true;
        String account = _account.getText().toString();
        String email = _email.getText().toString();
        String password = _password.getText().toString();

        if (account.isEmpty() || account.length() < 6) {
            _account.setError("your account's length must larger than 6");
            valid = false;
        } else {
            _account.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _email.setError("address must be your@domain");
            valid = false;
        } else {
            _email.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            _password.setError("your password's length must larger than 6");
            valid = false;
        } else {
            _password.setError(null);
        }
        return valid;
    }
}
