package com.example.keyaanapplication.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keyaanapplication.R;

public class View_Venues_Activity extends BaseActivity {

    TextView headerTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_venue_main);

        showToolbar();
        setStatusBarTopColor();

        headerTextView = (TextView) findViewById(R.id.header);
        headerTextView.setText("Venues View");
    }
}
