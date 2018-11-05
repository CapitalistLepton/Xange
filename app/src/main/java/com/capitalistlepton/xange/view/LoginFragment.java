package com.capitalistlepton.xange.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capitalistlepton.xange.R;
import com.capitalistlepton.xange.model.User;

public class LoginFragment extends Fragment {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private Button  mLoginButton;
    private Button  mSignUpButton;

    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //sets view to fragment_login
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        //finds xml resources:
        mUsernameText = v.findViewById(R.id.login_username);
        mPasswordText = v.findViewById(R.id.login_password);
        mLoginButton = v.findViewById(R.id.login_button);
        mSignUpButton = v.findViewById(R.id.login_signup);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        return v;
    }

    private void login() {
        String username = mUsernameText.getText().toString();
        String password = mPasswordText.getText().toString();

        user = User.login(username, password);
        if (user == null) {
            Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(getContext(), "Logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void signUp() {
        Toast.makeText(getContext(), "Sign Up", Toast.LENGTH_SHORT).show();
    }

}
