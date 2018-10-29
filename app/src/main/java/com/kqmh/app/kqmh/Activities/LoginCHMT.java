package com.kqmh.app.kqmh.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.kqmh.app.kqmh.Forms.Assessment_InfoEx;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;


public class LoginCHMT extends AppCompatActivity {
    private EditText email, password;
    private ProgressDialog progressDialog;
    private AppCompatCheckBox checkbox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        checkbox = (AppCompatCheckBox) findViewById(R.id.checkbox);
        Button login = findViewById(R.id.bt_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setCancelable(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // hide password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    private boolean check() {
        if (email.getText().toString().isEmpty()) {
            email.setError(getString(R.string.error_email_required));
            return false;
        } else if (password.getText().toString().isEmpty()) {
            password.setError(getString(R.string.error_password_required));
            return false;
        }
        return true;
    }

    private void login(final String encoded) {
        final SessionManager sessionManager = new SessionManager(getBaseContext());
        progressDialog.show();
        JSONObject jsonObject = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, UrlConstants.LOGIN_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sessionManager.setKeyBearerToken(encoded);
                sessionManager.setLoggedIn(true);
                sessionManager.setUserName(email.getText().toString());
                sessionManager.setKeyPassword(password.getText().toString());
                finishLogin();
                Toast.makeText(LoginCHMT.this, "Successfull", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                closeProgressbar();
                if (error instanceof AuthFailureError) {
                    Toast.makeText(LoginCHMT.this, "Wrong email and password combination", Toast.LENGTH_SHORT).show();
                }
                closeProgressbar();
                Toast.makeText(LoginCHMT.this, "Cannot Log in at this time", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", encoded);
                return headers;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }


    public void submit() {
        if (check()) {
            try {
                login(AuthBuilder.encode(email.getText().toString(), password.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void finishLogin() {
        closeProgressbar();
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Assessment_InfoEx.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
