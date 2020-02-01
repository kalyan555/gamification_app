package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GoalActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ArrayList<Integer> goalId=new ArrayList<Integer>();
    private ArrayList<String> goalName=new ArrayList<String>();
    private ArrayList<Integer> goalAmount=new ArrayList<Integer>();
    private ArrayList<Integer> savedAmount=new ArrayList<Integer>();
    private ArrayList<Integer> status=new ArrayList<Integer>();
    private JSONObject goalData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_ALL_GOALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            progressDialog.dismiss();
                            goalData=obj;
                            Toast.makeText(
                                    getApplicationContext(),
                                    response,
                                    Toast.LENGTH_LONG
                            ).show();

                            createRecyclerView();
                            initRecyclerView();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String username = SharedPrefManager.getInstance(getApplicationContext())
                        .getUsername();
                params.put("username", username);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void createRecyclerView() {
        Iterator<String> keys = goalData.keys();
        Log.e("keys",keys.toString());
        Log.e("data",goalData.toString());
        try{
            while(keys.hasNext()) {
                String key = keys.next();
                Log.e("key",key);
                if (goalData.get(key) instanceof JSONObject) {
                    // do something with jsonObject here
                    JSONObject eachData= (JSONObject) goalData.get(key);
                    goalId.add(Integer.parseInt(eachData.getString("id")));
                    goalName.add(eachData.getString("goal_name"));
                    goalAmount.add(Integer.parseInt(eachData.getString("goal_amount")));
                    savedAmount.add(Integer.parseInt(eachData.getString("saved_amount")));
                    status.add(Integer.parseInt(eachData.getString("goal_status")));
                }
            }
        }catch (JSONException e){
            Log.e("Exception -->",e.getMessage());
        }

        Log.e("data",goalId.toString());
        Log.e("data",goalAmount.toString());
        Log.e("data",goalName.toString());
        Log.e("data",savedAmount.toString());


    }

    public void creatNewGoal(View view) {
//        getAllGoals();
        startActivity(new Intent(getApplicationContext(), CreateGoalActivity.class));
    }

    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, goalId,goalName,goalAmount,savedAmount,status);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
