package com.group3.smarthome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Bathroom extends Fragment {

    private BathroomInfoViewModel BathroomInfoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BathroomInfoViewModel =
                ViewModelProviders.of(this).get(BathroomInfoViewModel.class);
        View root = inflater.inflate(R.layout.room_bathroom, container, false);
        final TextView textView = root.findViewById(R.id.bathroom);
        BathroomInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Button bathroomdetail1 = root.findViewById(R.id.bathroomdetail1);
        final Button bathroomdetail2 = root.findViewById(R.id.bathroomdetail2);
        bathroomdetail1.setOnClickListener(new mClick());
        bathroomdetail2.setOnClickListener(new mClick());
        return root;
    }
    class mClick implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent1=new Intent(getActivity(), NestProtect.class);
            startActivity(intent1);
        }
    }
}