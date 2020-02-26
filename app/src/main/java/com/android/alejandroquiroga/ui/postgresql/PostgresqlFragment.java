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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.alejandroquiroga.AccessData;
import com.android.alejandroquiroga.Models.Esdeveniment;
import com.android.alejandroquiroga.R;
import com.android.alejandroquiroga.ui.edit.EditFragment;

import java.util.ArrayList;

public class PostgresqlFragment extends Fragment {

    private PostgresqlViewModel postgresqlViewModel;
    private RecyclerView recyclerView;
    private PostgresqlAdapter mAdapter;
    private static Esdeveniment passEsdeveniment;

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
    public static Esdeveniment getEsdeveniment(){
        return passEsdeveniment;
    }

    public class PostgresqlAdapter extends RecyclerView.Adapter<PostgresqlViewHolder> {

        private ArrayList<String> postgresqlElementsLits;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recyclerView.getChildLayoutPosition(v);
                String item = postgresqlElementsLits.get(itemPosition);

                Esdeveniment esv = AccessData.getAccessData(getContext()).getEsdevenimentById(item);
                passEsdeveniment = esv != null ? esv : new Esdeveniment();

                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_nav_postgresql_to_editFragment);
//
//                Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
            }
        };

        public PostgresqlAdapter(ArrayList<String> postgresqlElementsLits) {
            this.postgresqlElementsLits = postgresqlElementsLits;
        }

        @NonNull
        @Override
        public PostgresqlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_postgresql, parent, false);
            view.setOnClickListener(mOnClickListener);
            return new PostgresqlViewHolder(view);
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