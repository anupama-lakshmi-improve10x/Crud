package com.example.crud.message;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

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
        if (item.getItemId() == R.id.done) {
            String name = binding.nameTxt.getText().toString();
            String phoneNumber = binding.phoneNumberTxt.getText().toString();
            String message = binding.messageTextTxt.getText().toString();
            updateMessage(this.message.id, name, phoneNumber, message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        binding.setMessage(message);
    }

    private void updateMessage(String id, String name, String phoneNumber, String messageText) {
        Message message = new Message(name, phoneNumber, messageText);
        Call<Void> call = crudService.updateMessage(id, message);
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
