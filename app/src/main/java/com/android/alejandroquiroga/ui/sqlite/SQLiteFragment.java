package com.android.alejandroquiroga.ui.sqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.alejandroquiroga.R;

import java.util.ArrayList;

public class SQLiteFragment extends Fragment {

    private SQLiteViewModel sqliteViewModel;
    private RecyclerView recyclerView;
    private SQLiteAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View ui_layout = inflater.inflate(R.layout.fragment_sqlite, container, false);

        sqliteViewModel = ViewModelProviders.of(this).get(SQLiteViewModel.class);

        sqliteViewModel.readElementsList();
        recyclerView = ui_layout.findViewById(R.id.sqlite_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new SQLiteAdapter(sqliteViewModel.getElementsList());
        recyclerView.setAdapter(mAdapter);
        return ui_layout;
    }

    public class SQLiteViewHolder extends RecyclerView.ViewHolder{
        TextView textSQLiteElements;

        public SQLiteViewHolder(@NonNull View itemView){
            super(itemView);

            textSQLiteElements = itemView.findViewById(R.id.tv_sqlite);
        }
    }


    public class SQLiteAdapter extends RecyclerView.Adapter<SQLiteViewHolder> {


        private ArrayList<String> sqliteElementsLits;

        public SQLiteAdapter(ArrayList<String> sqliteElementsLits) {
            this.sqliteElementsLits = sqliteElementsLits;
        }

        @NonNull
        @Override
        public SQLiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SQLiteViewHolder(getLayoutInflater().inflate(R.layout.viewholder_sqlite, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SQLiteViewHolder holder, int position) {
            String test = sqliteElementsLits.get(position);
            holder.textSQLiteElements.setText(test);
        }

        @Override
        public int getItemCount() { return sqliteElementsLits.size(); }
    }
}