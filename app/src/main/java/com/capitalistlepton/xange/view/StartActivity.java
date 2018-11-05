package com.capitalistlepton.xange.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.capitalistlepton.xange.R;

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fm = getSupportFragmentManager();
        Fragment login = fm.findFragmentById(R.id.fragment_container);

        if (login == null) {
            login = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, login)
                    .commit();
        }

    }

}
