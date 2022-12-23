package com.example.crud.message;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityBaseAddEditMessageBinding;

public class BaseAddEditMessageActivity extends BaseActivity {

    ActivityBaseAddEditMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseAddEditMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_message_menu, menu);
        return true;
    }
}