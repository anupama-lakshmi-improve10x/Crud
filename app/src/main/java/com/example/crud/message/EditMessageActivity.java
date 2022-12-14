package com.example.crud.message;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.message.BaseAddEditMessageActivity;
import com.example.crud.message.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity {

    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Message");
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            message = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.done) {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            updateMessage(this.message.id, name, phoneNumber, message);
            return true;
            } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        nameTxt.setText(message.name);
        phoneNumberTxt.setText(message.mobileNumber);
        messageTxt.setText(message.message);
    }

    private void updateMessage(String id, String name, String phoneNumber, String messageTxt) {
        Message message = new Message(name, phoneNumber, messageTxt);
        Call<Void> call = crudService.updateMessage(id , message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the Message");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update the Message");
            }
        });
    }
}
