package com.encarguitos.devf.encarguitos;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class HomeActivity extends ActionBarActivity {

    private static final String POST_SANDBOX = "http://requestb.in/1juzstn1";
    private ProgressDialog progressDialog;
    public static final JSONObject jsonObject = new JSONObject() {
        {
            try {
                put("pedido", "Papitas y coca light");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EditText edText = (EditText)findViewById(R.id.editText);
        edText.getText().toString();

    }

    public void sendToServer(View v) {
        progressDialog = ProgressDialog.show(this, "",
                "Sending data to server");
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, POST_SANDBOX, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // do something)
                        Log.i("***", response.toString());
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("***", error.getMessage());
                Toast.makeText(HomeActivity.this, "Hubo pedos", Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);


    }

}