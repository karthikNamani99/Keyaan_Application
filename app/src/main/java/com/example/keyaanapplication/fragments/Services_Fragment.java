package com.example.keyaanapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.keyaanapplication.R;
import com.example.keyaanapplication.activities.Add_Services_Activity;
import com.example.keyaanapplication.activities.Add_Venues_Activity;
import com.example.keyaanapplication.activities.View_Services_Activity;
import com.example.keyaanapplication.activities.View_Venues_Activity;

public class Services_Fragment extends Fragment implements View.OnClickListener {

    LinearLayout layout_view_sevices,layout_add_services;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.services_main, container, false);

        layout_view_sevices=view.findViewById(R.id.layout_view_sevices);
        layout_add_services=view.findViewById(R.id.layout_add_services);

        layout_view_sevices.setOnClickListener(this);
        layout_add_services.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.layout_view_sevices:

                Intent view_venues_intent=new Intent(getActivity(), View_Services_Activity.class);
                view_venues_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(view_venues_intent);

                break;

            case R.id.layout_add_services:

                Intent add_venues_intent=new Intent(getActivity(), Add_Services_Activity.class);
                add_venues_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(add_venues_intent);
                break;

        }
    }
}
