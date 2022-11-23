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

public class Kitchen extends Fragment {
    private KitchenInfoViewModel KitchenInfoViewMode1;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        KitchenInfoViewMode1 =
                ViewModelProviders.of(this).get(KitchenInfoViewModel.class);
        View root = inflater.inflate(R.layout.room_kitchen, container, false);
        final TextView textView = root.findViewById(R.id.kitchen);
        KitchenInfoViewMode1.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final Button kitchendetail1 = root.findViewById(R.id.kitchendetail1);
        final Button kitchendetail2 = root.findViewById(R.id.kitchendetail2);
        kitchendetail1.setOnClickListener(new mClick());
        kitchendetail2.setOnClickListener(new mClick());
        return root;
    }
    class mClick implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent1=new Intent(getActivity(),NestProtect.class);
            startActivity(intent1);
        }
    }
}

