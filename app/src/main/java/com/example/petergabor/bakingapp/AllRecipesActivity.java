package com.example.petergabor.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.petergabor.bakingapp.utils.Recept;

public class AllRecipesActivity extends AppCompatActivity implements AllRecipeAdapter.ForecastAdapterOnClickHandler {

    private static final int FORECAST_LOADER_ID = 0;

    public static ProgressBar mLoadingIndicator;
    private RecyclerView recycler;
    private AllRecipeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);  // loader

        // nastavenie recyclerview
        recycler = (RecyclerView) findViewById(R.id.recepts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);

        // vyplnit recyclerview datami
        mAdapter = new AllRecipeAdapter(getBaseContext(), this);
        recycler.setAdapter(mAdapter);


        getSupportLoaderManager().initLoader(FORECAST_LOADER_ID, null, new AllRecipesLoader(this, mAdapter));

    }

    @Override
    public void onClick(Recept recept) {
        Intent intentToStartDetailActivity = new Intent(this, RecipeDescription.class);
        intentToStartDetailActivity.putExtra("recept", recept);
        startActivity(intentToStartDetailActivity);

    }
}
