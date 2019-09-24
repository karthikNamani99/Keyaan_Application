package com.example.keyaanapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keyaanapplication.R;

public class Add_Services_Activity extends BaseActivity {

    TextView headerTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_service_main);

        showToolbar();
        setStatusBarTopColor();

        headerTextView = (TextView) findViewById(R.id.header);
        headerTextView.setText("Services Add");
    }
}
