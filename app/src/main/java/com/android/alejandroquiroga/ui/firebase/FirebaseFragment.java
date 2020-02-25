package com.android.alejandroquiroga.ui.firebase;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.alejandroquiroga.R;

import java.util.ArrayList;

public class FirebaseFragment extends Fragment {

    private FirebaseViewModel firebaseViewModel;
    private RecyclerView recyclerView;
    private FirebaseAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View ui_layout = inflater.inflate(R.layout.fragment_firebase, container, false);

        firebaseViewModel = ViewModelProviders.of(this).get(FirebaseViewModel.class);

        recyclerView = ui_layout.findViewById(R.id.firebase_recycler);

        firebaseViewModel.readElementsList();

        firebaseViewModel.getElementsList().observe(this, new Observer<ArrayList<String>>() {

            @Override
            public void onChanged(ArrayList<String> strings) {
                mAdapter = new FirebaseAdapter(strings);
                recyclerView.setAdapter(mAdapter);
            }
        });

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return ui_layout;
    }

    // Antes que la clase Adapter, creo la clase Holder
    public class FirebaseViewHolder extends RecyclerView.ViewHolder{
        TextView textFirebaseElements;

        public FirebaseViewHolder(@NonNull View itemView) {
            super(itemView);

            textFirebaseElements = itemView.findViewById(R.id.tv_firebase);
        }
    }

    public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseViewHolder>{

        private ArrayList<String> firebaseElementsList;

        public FirebaseAdapter(ArrayList<String> firebaseElementsList) {
            this.firebaseElementsList = firebaseElementsList;
        }

        @NonNull
        @Override
        public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FirebaseViewHolder(getLayoutInflater().inflate(R.layout.viewholder_firebase, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position) {
            holder.textFirebaseElements.setText(firebaseElementsList.get(position));
        }

        @Override
        public int getItemCount() {
            return firebaseElementsList.size();
        }
    }
}
