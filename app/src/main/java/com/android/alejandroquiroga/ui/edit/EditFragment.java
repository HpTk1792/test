package com.android.alejandroquiroga.ui.edit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.alejandroquiroga.AccessData;
import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.R;

public class EditFragment extends Fragment {

    private EditViewModel mViewModel;
    private static String idElement;



    public static EditFragment newInstance() {
//        idElement = id;
        return new EditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View ui_layout = inflater.inflate(R.layout.fragment_edit, container, false);
        ExampleElement ee = AccessData.getAccessData(getContext()).getExampleElement(idElement);
        TextView tv = ui_layout.findViewById(R.id.text_edit);
        tv.setText(ee.getAttribute1());

        return ui_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditViewModel.class);
        // TODO: Use the ViewModel
    }

}
