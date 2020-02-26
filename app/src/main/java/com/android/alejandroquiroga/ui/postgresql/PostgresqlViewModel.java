package com.android.alejandroquiroga.ui.postgresql;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.alejandroquiroga.AccessData;
import com.android.alejandroquiroga.MainActivity;
import com.android.alejandroquiroga.Models.Esdeveniment;
import com.android.alejandroquiroga.Models.ExampleElement;

import java.util.ArrayList;
import java.util.List;

public class PostgresqlViewModel extends ViewModel {

    private ArrayList<String> showingList;

    public PostgresqlViewModel() { showingList = new ArrayList<String>(); }

    public ArrayList<String> getElementsList() {
        return showingList;
    }

    public void readElementsList(){
        final List<Esdeveniment> esdevenimentList = AccessData.getAccessData(MainActivity.getContext()).getEsdevenimentsList();
        if(showingList.size() != esdevenimentList.size())
        for(Esdeveniment e : esdevenimentList){
            showingList.add(e.title);
        }
    }
}