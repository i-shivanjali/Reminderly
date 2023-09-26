package com.example.reminderly;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
//
//    toDoListActivity to_do_list_activity;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button to_do_btn = view.findViewById(R.id.to_do);
        //Button schedluer_btn = view.findViewById(R.id.scheduler);
        Button journal_btn = view.findViewById(R.id.journal);

        to_do_btn.setOnClickListener(new View.OnClickListener() {
//            toDoListActivity=(to_do_list_activity)getActivity();
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),to_do_list_activity.class);
                startActivity(i);
            }
        });

//        schedluer_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(),scheduler_activity.class);
//                startActivity(i);
//            }
//        });
        journal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Notes_Activity.class);
                startActivity(i);
            }
        });
        return view;
    }
}