package com.example.crud.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class MessagesActivity extends BaseActivity {
    private ArrayList<Message> messageList = new ArrayList<>();
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        log("onCreate");
        getSupportActionBar().setTitle("Messages");
        setupApiService();
        setupMessagesRv();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.messages_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.add) {
           Intent intent = new Intent(this, AddEditMessageActivity.class);
           startActivity(intent);
           showToast("Success");
           return true;
       } else {
          return super.onOptionsItemSelected(item);
       }
    }

    private void fetchData() {
        showVisible();
        setupApiService();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideVisible();
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
                showToast("Successfully loaded Message");
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideVisible();
                showToast("Failed to load Message");
            }
        });
    }

    private void setupMessagesRv() {
        progressBar = findViewById(R.id.progress_bar);
        messagesRv = findViewById(R.id.message_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messageList);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                showToast("Successfully Deleted Message");
                deleteMessage(id);
            }

            @Override
            public void onEdit(Message message) {
               showToast("Successfully Edited Message");
                editMessage(message);
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }

    private void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisible(){
        progressBar.setVisibility(View.GONE);
    }

    private void deleteMessage(String id) {
        setupApiService();
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               showToast("Successfully deleted message");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete message");
            }
        });
    }

    private void editMessage(Message message) {
        Intent intent = new Intent(this, AddEditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        startActivity(intent);
    }
}