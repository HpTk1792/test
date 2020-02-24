package com.android.alejandroquiroga.ui.firebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.R;

import java.util.ArrayList;

public class FirebaseFragment extends Fragment {

    private FirebaseViewModel firebaseViewModel;
    private RecyclerView recyclerView;
    private IncidentAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View ui_layout = inflater.inflate(R.layout.fragment_firebase, container, false);

        firebaseViewModel = ViewModelProviders.of(this).get(FirebaseViewModel.class);

        recyclerView = ui_layout.findViewById(R.id.firebase_recycler);

        firebaseViewModel.readlistaMensajes(); // ID del Usuario para leer sus mensajes

        firebaseViewModel.getlistaMensajes().observe(this, new Observer<ArrayList<String>>() {
            @Override
        public void onChanged(ArrayList<String> strings) {
                mAdapter = new IncidentAdapter(strings);
                recyclerView.setAdapter(mAdapter);
            }
        });

        // use a linear layout manager. Con el LinearLayout le indico al recycler que se pinte en linea. Existen otras formas
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

            return ui_layout;
    }

    // Antes que la clase Adapter, creo la clase Holder
    public class IncidentViewHolder extends RecyclerView.ViewHolder{
        TextView TextIncidents; // Declaro aquellos elementos del layout que recogen datos


        public IncidentViewHolder(@NonNull View itemView) {
            super(itemView);

            TextIncidents = (TextView) itemView.findViewById(R.id.tv_incidents);
        }
    }

    public class IncidentAdapter extends RecyclerView.Adapter<IncidentViewHolder>{

        private ArrayList<String> incidentsList;

        public IncidentAdapter(ArrayList<String> incidentsList) { // Creo un constructor
            this.incidentsList = incidentsList;
        }

        @NonNull
        @Override
        public IncidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Asocio mi archivo Holder (IncidentViewHolder), le pongo el inflate y le paso el layout que he creado del holder
            // este contiene un modelo del objeto a dibujar. Le indico parent para que sepa el tamaño que ocupa y por útlimo SIEMPRE 'false'
            return new IncidentViewHolder(getLayoutInflater().inflate(R.layout.viewholder_incidents, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull IncidentViewHolder holder, int position) {
            // Indico al Holder los datos que le pasaré y su posición. En este caso le paso los datos recogidos
            // en la arrayList que he creado con el nombre incidentsList.
            holder.TextIncidents.setText(incidentsList.get(position));

        }

        @Override
        public int getItemCount() {
            // Indico que devuelva la totalidad de los datos que existen en la arrayList
            return incidentsList.size();
        }
    }
}
