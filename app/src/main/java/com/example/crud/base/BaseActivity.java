package com.example.crud.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

public class BaseActivity extends AppCompatActivity {

    protected CrudService crudService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        setupCrudApiService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void log(String message) {
        Log.i(this.getClass().getSimpleName(), message);
    }

    private void setupCrudApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }
}
