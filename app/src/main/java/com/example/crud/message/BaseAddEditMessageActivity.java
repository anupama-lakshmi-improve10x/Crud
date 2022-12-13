package com.example.crud.message;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditMessageActivity extends BaseActivity {

    protected EditText nameTxt;
    protected EditText phoneNumberTxt;
    protected EditText messageTxt;
    protected CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        initViews();
        setupApiService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }

    private void initViews() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }
}