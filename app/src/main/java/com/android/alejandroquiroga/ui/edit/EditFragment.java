package com.android.alejandroquiroga.ui.edit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.alejandroquiroga.Models.Esdeveniment;
import com.android.alejandroquiroga.R;
import com.android.alejandroquiroga.ui.postgresql.PostgresqlFragment;

public class EditFragment extends Fragment {

    private Esdeveniment esdeveniment;
    private EditViewModel mViewModel;
    private static String idElement;

    private TextView tv_title;
    private TextView tv_date;
    private TextView tv_place;
    private TextView tv_email;
    private TextView tv_room;
    private TextView tv_price;
    private TextView tv_people;
    private TextView tv_description;

    private Button bt_update;

    public static EditFragment newInstance() {
//        idElement = id;
        return new EditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View ui_layout = inflater.inflate(R.layout.fragment_event_detail, container, false);

        esdeveniment = PostgresqlFragment.getEsdeveniment();

        tv_title = ui_layout.findViewById(R.id.nombreDetailTextView);
        tv_date = ui_layout.findViewById(R.id.fechaDetailTextView);
        tv_place = ui_layout.findViewById(R.id.lugarDetailTextView);
        tv_email = ui_layout.findViewById(R.id.organizadorTextView);
        tv_room = ui_layout.findViewById(R.id.salaTextView);
        tv_price = ui_layout.findViewById(R.id.precioTextView);
        tv_people = ui_layout.findViewById(R.id.aforoTextView);
        tv_description = ui_layout.findViewById(R.id.descripcionTextView);

        tv_title.setText(esdeveniment.title);
        tv_date.setText(esdeveniment.date.toString());
        tv_place.setText(esdeveniment.place);
        tv_email.setText(esdeveniment.email);
        tv_room.setText(esdeveniment.room);
        tv_price.setText(Integer.toString(esdeveniment.price));
        tv_people.setText(Integer.toString(esdeveniment.people));
        tv_description.setText(esdeveniment.description);

        bt_update = ui_layout.findViewById(R.id.editImageButton);
        return ui_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditViewModel.class);
    }
}
