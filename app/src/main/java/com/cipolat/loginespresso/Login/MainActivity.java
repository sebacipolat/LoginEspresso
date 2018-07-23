package com.cipolat.loginespresso.Login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cipolat.loginespresso.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.subLabel)
    TextView subLabel;
    @BindView(R.id.brandLabel)
    TextView brandLabel;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.inputUser)
    TextInputEditText inputUser;
    @BindView(R.id.inputPassw)
    TextInputEditText inputPassw;
    @BindView(R.id.signUpLbl)
    TextView signUpLbl;
    @BindView(R.id.inputUserInputLayout)
    TextInputLayout inputEmailInputLayout;
    @BindView(R.id.inputPasswInputLayout)
    TextInputLayout inputPasswInputLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        inputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (inputEmailInputLayout.getError() != null)
                    inputEmailInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        inputUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEmailInput(inputUser.getText().toString());
                }
            }
        });
    }

    @OnClick(R.id.loginBtn)
    public void signIn() {
        Keyboard.hideKeyboard(this);
        boolean validEmail = validateEmailInput(inputUser.getText().toString());
        boolean validPassw = validatePasswInput(inputPassw.getText().toString());
        if (validEmail && validPassw) {
            if (!checkLoginData(inputUser.getText().toString(), inputPassw.getText().toString())) {
                showErrorMessage();
                cleanInputs();
            } else
                showOkMessage();
        }
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void showErrorMessage() {
        int unicode = 0x270B;
        String message = getString(R.string.invalidCredentials_message) + "\n" + getEmojiByUnicode(unicode);
        DialogFactory.showInfoDialog(getString(R.string.invalidCredentials_title), message, this);
    }

    private void showOkMessage() {
        int unicode = 0x1F44D;
        String message = getString(R.string.validCredentials_message) + "\n" + getEmojiByUnicode(unicode);
        DialogFactory.showInfoDialog(getString(R.string.validCredentials_title), message, this);
    }

    private void cleanInputs() {
        inputUser.setText("");
        inputPassw.setText("");
    }

    private boolean validateEmailInput(String email) {
        boolean status = false;
        if (!email.isEmpty()) {
            if (!ValidationsUtils.isValidEmail(email))
                inputEmailInputLayout.setError(getString(R.string.invalid_email_error));
            else
                status = true;
        } else
            inputEmailInputLayout.setError(getString(R.string.empty_email_error));

        return status;
    }


    private boolean validatePasswInput(String passw) {
        boolean status = false;
        if (!passw.isEmpty())
            status = true;
        else
            inputPasswInputLayout.setError(getString(R.string.empty_pass_error));
        return status;
    }

    private boolean checkLoginData(String username, String passw) {
        if (username.equals("sebastiancipolat@gmail.com") && passw.equals("android2018"))
            return true;
        else
            return false;
    }
}
