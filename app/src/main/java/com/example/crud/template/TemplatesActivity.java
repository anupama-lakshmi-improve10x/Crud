package com.example.crud.template;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {
    private ArrayList<Template> templates = new ArrayList<>();
    private RecyclerView templatesRv;
    private TemplatesAdapter templatesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        log("onCreate");
        getSupportActionBar().setTitle("Templates");
        initView();
        setupTemplatesRv();
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.add) {
           Intent intent = new Intent(this, AddTemplateActivity.class);
           startActivity(intent);
           showToast("Success");
           return true;
       } else{
           return super.onOptionsItemSelected(item);
       }
    }

    private void initView() {
        templatesRv = findViewById(R.id.templates_rv);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void editMessage(Template template) {
        Intent intent = new Intent(this, EditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, template);
        startActivity(intent);
    }

    private void fetchData() {
        showVisible();
        setupApiService();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideVisible();
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
                showToast("successfully loaded data");
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideVisible();
                showToast("Failed to load data");
            }
        });
    }

    private void setupTemplatesRv() {
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
               showToast("Successfully Deleted");
                deleteMessage(id);
            }

            @Override
            public void onEdit(Template template) {
                showToast("Message Selected");
                editMessage(template);
            }
        });
        templatesRv.setAdapter(templatesAdapter);
    }

    private void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisible(){
        progressBar.setVisibility(View.GONE);
    }

    private void deleteMessage(String id) {
        setupApiService();
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               showToast("Successfully loaded Message");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed Delete Message");
            }
        });
    }
}