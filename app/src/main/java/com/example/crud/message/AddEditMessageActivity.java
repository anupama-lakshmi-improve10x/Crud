package com.example.crud.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMessageActivity extends AppCompatActivity {
    private Message message;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private EditText messageTxt;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        initView();
        setupApiService();
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            this.message = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Message");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.done) {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            if(this.message == null) {
                addMessage(name, phoneNumber, message);
            } else {
                updateMessage(this.message.id, name, phoneNumber, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }

    private void addMessage(String name, String phoneNumber, String message) {
        this.message = new Message(name, phoneNumber, message);
        setupApiService();
        Call<Message> call = crudService.createMessage(this.message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                setupToast("Successfully added message");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                setupToast("Failed to add Message");
            }
        });
    }

    private void showData() {
        nameTxt.setText(this.message.name);
        phoneNumberTxt.setText(this.message.mobileNumber);
        messageTxt.setText(this.message.message);
    }

    private void updateMessage(String id, String name, String phoneNumber, String message) {
        this.message = new Message(name, phoneNumber, message);
        setupApiService();
        Call<Void> call = crudService.updateMessage(id,this.message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setupToast("Successfully loaded Message");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setupToast("Failed to Delete Message");
            }
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}