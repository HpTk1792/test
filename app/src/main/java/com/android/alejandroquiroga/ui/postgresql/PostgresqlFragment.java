package com.android.alejandroquiroga.ui.postgresql;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.alejandroquiroga.R;

public class PostgresqlFragment extends Fragment {

    private PostgresqlViewModel postgresqlViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postgresqlViewModel =
                ViewModelProviders.of(this).get(PostgresqlViewModel.class);
        View root = inflater.inflate(R.layout.fragment_postgresql, container, false);
        final TextView textView = root.findViewById(R.id.text_postgresql);
        postgresqlViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}