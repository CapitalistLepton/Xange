package com.capitalistlepton.xange.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.capitalistlepton.xange.R;
import com.capitalistlepton.xange.model.User;
import com.capitalistlepton.xange.util.Network;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

    private EditText mUsernameText;
    private EditText mPasswordText;

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
        Button loginButton = v.findViewById(R.id.login_button);
        Button signUpButton = v.findViewById(R.id.login_signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        return v;
    }

    private void login() {
        String username = mUsernameText.getText().toString();
        final String password = mPasswordText.getText().toString();

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, Network.BASE_URL + "/users/" + username, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    user = User.verifyLogin(response.getString("username"),
                                            password, response.getString("password_digest"));
                                    if (user == null) {
                                        Toast.makeText(getContext(),
                                                "Invalid username or password",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "Logged in",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        Network.getInstance(getContext()).addToRequestQueue(req);
    }

    private void signUp() {
        Fragment signUp = new SignUpFragment();
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_container, signUp);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
