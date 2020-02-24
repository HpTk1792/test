package com.android.alejandroquiroga.ui.sqlite;

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

public class SQLiteFragment extends Fragment {

    private SQLiteViewModel sqliteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sqliteViewModel =
                ViewModelProviders.of(this).get(SQLiteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sqlite, container, false);
        final TextView textView = root.findViewById(R.id.text_sqlite);
        sqliteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}