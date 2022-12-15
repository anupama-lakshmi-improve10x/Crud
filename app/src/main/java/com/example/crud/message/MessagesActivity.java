package com.example.crud.message;

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
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {

//Todo : change messageList to messages
    private ArrayList<Message> messageList = new ArrayList<>();
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        log("onCreate");
        getSupportActionBar().setTitle("Messages");
        setupMessagesAdapter();
        setupMessagesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.messages_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.add) {
           Intent intent = new Intent(this, AddMessageActivity.class);
           startActivity(intent);
           return true;
       } else {
          return super.onOptionsItemSelected(item);
       }
    }

    private void fetchMessages() {
        showProgressBar();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideProgressBar();
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
                showToast("Successfully loaded Messages");
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load Messages");
            }
        });
    }

    private void setupMessagesAdapter(){
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
    }

    private void setupMessagesRv() {
        progressBar = findViewById(R.id.progress_bar);
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesRv.setAdapter(messagesAdapter);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    private void deleteMessage(String id) {
        showProgressBar();
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideProgressBar();
               showToast("Successfully deleted message");
               fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to delete message");
            }
        });
    }

    private void editMessage(Message message) {
        Intent intent = new Intent(this, EditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        startActivity(intent);
    }
}