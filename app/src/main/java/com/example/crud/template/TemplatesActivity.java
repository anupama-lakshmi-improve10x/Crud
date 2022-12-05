package com.example.crud.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {
    public ArrayList<Template> templates = new ArrayList<>();
    public RecyclerView templatesRv;
    public TemplateAdapter templateAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        initView();
        setupTemplatesRv();
    }

    protected void onResume() {
        super.onResume();
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
           Intent intent = new Intent(this, AddEditTemplateActivity.class);
           startActivity(intent);
           Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
           return true;
       } else{
           return super.onOptionsItemSelected(item);
       }
    }

    public void initView() {
        templatesRv = findViewById(R.id.templates_rv);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void editMessage(Template template) {
        Intent intent = new Intent(this, AddEditTemplateActivity.class);
        intent.putExtra("message", template);
        startActivity(intent);
    }

    public void fetchData() {
        showVisible();
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<List<Template>> call = templateService.fetchMessages();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideVisible();
                List<Template> templates = response.body();
                templateAdapter.setData(templates);
                Toast.makeText(TemplatesActivity.this, "successfully loaded data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideVisible();
                Toast.makeText(TemplatesActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupTemplatesRv() {
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templateAdapter = new TemplateAdapter();
        templateAdapter.setData(templates);
        templateAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                Toast.makeText(TemplatesActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                deleteMessage(id);
            }

            @Override
            public void onEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "Message Selected", Toast.LENGTH_SHORT).show();
                editMessage(template);
            }
        });
        templatesRv.setAdapter(templateAdapter);
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisible(){
        progressBar.setVisibility(View.GONE);
    }

    public void deleteMessage(String id) {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Void> call = templateService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully Deleted Message", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed Delete Message", Toast.LENGTH_SHORT).show();
            }
        });
    }
}