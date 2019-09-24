package com.example.keyaanapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.keyaanapplication.R;
import com.example.keyaanapplication.activities.Add_Venues_Activity;
import com.example.keyaanapplication.activities.View_Venues_Activity;

public class Venues_Fragment extends Fragment implements View.OnClickListener {

    LinearLayout layout_view_venues,layout_add_venues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venues_main, container, false);

        layout_view_venues=view.findViewById(R.id.layout_view_venues);
        layout_add_venues=view.findViewById(R.id.layout_add_venues);

        layout_view_venues.setOnClickListener(this);
        layout_add_venues.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.layout_view_venues:

                Intent view_venues_intent=new Intent(getActivity(), View_Venues_Activity.class);
                view_venues_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(view_venues_intent);

                break;

            case R.id.layout_add_venues:

                Intent add_venues_intent=new Intent(getActivity(), Add_Venues_Activity.class);
                add_venues_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(add_venues_intent);
                break;

        }
    }
}
