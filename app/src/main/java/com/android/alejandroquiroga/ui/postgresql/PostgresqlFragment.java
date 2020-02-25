package com.android.alejandroquiroga.ui.postgresql;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.alejandroquiroga.R;

import java.util.ArrayList;

public class PostgresqlFragment extends Fragment {

    private PostgresqlViewModel postgresqlViewModel;
    private RecyclerView recyclerView;
    private PostgresqlAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View ui_layout = inflater.inflate(R.layout.fragment_postgresql, container, false);

        postgresqlViewModel = ViewModelProviders.of(this).get(PostgresqlViewModel.class);

        postgresqlViewModel.readElementsList();
        recyclerView = ui_layout.findViewById(R.id.postgresql_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new PostgresqlAdapter(postgresqlViewModel.getElementsList());
        recyclerView.setAdapter(mAdapter);
        return ui_layout;
    }

    public class PostgresqlViewHolder extends RecyclerView.ViewHolder{
        TextView textPostgresqlElements;

        public PostgresqlViewHolder(@NonNull View itemView){
            super(itemView);

            textPostgresqlElements = itemView.findViewById(R.id.tv_postgresql);
        }
    }


    public class PostgresqlAdapter extends RecyclerView.Adapter<PostgresqlViewHolder> {

        private ArrayList<String> postgresqlElementsLits;

        public PostgresqlAdapter(ArrayList<String> postgresqlElementsLits) {
            this.postgresqlElementsLits = postgresqlElementsLits;
        }

        @NonNull
        @Override
        public PostgresqlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PostgresqlViewHolder(getLayoutInflater().inflate(R.layout.viewholder_postgresql, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PostgresqlViewHolder holder, int position) {
            String test = postgresqlElementsLits.get(position);
            holder.textPostgresqlElements.setText(test);
        }

        @Override
        public int getItemCount() { return postgresqlElementsLits.size(); }
    }
}