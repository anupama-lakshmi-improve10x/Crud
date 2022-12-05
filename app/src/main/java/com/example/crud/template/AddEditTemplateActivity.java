package com.example.crud.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {
    public Template template;
    public EditText messageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        messageTxt = findViewById(R.id.message_txt);
        if (getIntent().hasExtra("template")) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) getIntent().getSerializableExtra("template");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Template");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String message = messageTxt.getText().toString();

            if (this.template == null) {
                addMessage(message);
            } else {
                updateMessage(template.id, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void addMessage(String message) {
        Template template = new Template();
        template.messageText = message;

        TemplatesApi templateApi = new TemplatesApi();
        TemplatesService templateService = templateApi.createTemplateService();
        Call<Template> call = templateService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully Added Message", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed Add Message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showData() {
        messageTxt.setText(template.messageText);
    }

    public void updateMessage(String id, String message) {
        Template template = new Template();
        template.messageText = message;

        TemplatesApi templateApi = new TemplatesApi();
        TemplatesService templateService = templateApi.createTemplateService();
        Call<Void> call = templateService.updateTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully Updated Message", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed to Update Message", Toast.LENGTH_SHORT).show();
            }
        });
    }
}