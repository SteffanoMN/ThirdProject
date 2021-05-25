package com.example.thirdproject.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.thirdproject.R;
import com.example.thirdproject.adapter.TeamAdapter;
import com.example.thirdproject.model.TeamModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment1 extends AppCompatActivity {

    private String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?s=Soccer&c=Spain";
    private TeamAdapter adapter;
    private ArrayList<TeamModel> arrayList;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    protected void onCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        recyclerView = findViewById(R.id.recview);
        progressBar = findViewById(R.id.progress_bar);

        addData();

        return view;
    }

    private void addData() {
        AndroidNetworking.get(BASE_URL)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            arrayList = new ArrayList<>();
                            JSONArray teamsArray = response.getJSONArray("teams");
                            Log.d("RBA", "OnResponse: " + response);
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String name = teamObject.getString("strTeam");
                                String description = teamObject.getString("strDescriptionEN");
                                String image = teamObject.getString("strTeamBadge");
                                arrayList.add(new TeamModel(image, name, description));
                            }
                            adapter = new TeamAdapter(getApplicationContext(), arrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            Log.d("error", e.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.toString());
                    }
                });
    }
}
