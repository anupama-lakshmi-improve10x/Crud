package com.example.crud.template;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityBaseAddEditTemplateBinding;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected ActivityBaseAddEditTemplateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseAddEditTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_template_menu, menu);
        return true;
    }
}