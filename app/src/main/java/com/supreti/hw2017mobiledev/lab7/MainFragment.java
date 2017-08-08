package com.supreti.hw2017mobiledev.lab7;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // Get references to text views to display database data.
        final TextView rootTextView = v.findViewById(R.id.root_text_view);
        final TextView subTreeTextView = v.findViewById(R.id.subtree_text_view);

        // Add database code here.
        

        return v;
    }

}
