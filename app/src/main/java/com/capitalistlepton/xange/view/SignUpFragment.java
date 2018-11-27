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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.capitalistlepton.xange.R;
import com.capitalistlepton.xange.util.Network;

import org.json.JSONException;
import org.json.JSONObject;


public class SignUpFragment extends Fragment {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mPasswordConfirmationText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        mUsernameText = v.findViewById(R.id.signup_username);
        mPasswordText = v.findViewById(R.id.signup_password);
        mPasswordConfirmationText = v.findViewById(R.id.signup_password_confirmation);

        Button signUp = v.findViewById(R.id.signup_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        return v;
    }

    private void signUp() {
        String password = mPasswordText.getText().toString();
        if (!password.equals(mPasswordConfirmationText.getText().toString())) {
            Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject user = new JSONObject();
            try {
                user.accumulate("username", mUsernameText.getText().toString());
                user.accumulate("password", mPasswordText.getText().toString());
                JsonObjectRequest req = new JsonObjectRequest
                        (Request.Method.POST, Network.BASE_URL + "/users", user,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Go back to login page
                                        Fragment login = new LoginFragment();
                                        FragmentManager fm = getFragmentManager();
                                        if (fm != null) {
                                            FragmentTransaction transaction = fm.beginTransaction();
                                            transaction.replace(R.id.fragment_container, login);
                                            transaction.addToBackStack(null);
                                            transaction.commit();
                                        }
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error.networkResponse.statusCode == 422) {
                                    Toast.makeText(getContext(), "Could not create user",
                                            Toast.LENGTH_SHORT).show();
                                }
                                error.printStackTrace();
                            }
                        });
                Network.getInstance(getContext()).addToRequestQueue(req);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
