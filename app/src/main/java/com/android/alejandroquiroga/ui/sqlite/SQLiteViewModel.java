package com.android.alejandroquiroga.ui.sqlite;

import androidx.lifecycle.ViewModel;

import com.android.alejandroquiroga.AccessData;
import com.android.alejandroquiroga.MainActivity;
import com.android.alejandroquiroga.Models.Assistents;

import java.util.ArrayList;
import java.util.List;

public class SQLiteViewModel extends ViewModel {

    private ArrayList<String> showingList;

    public SQLiteViewModel() { showingList = new ArrayList<String>(); }

    public ArrayList<String> getElementsList() {
        return showingList;
    }

    public void readElementsList(){
        final List<Assistents> assistentsList = AccessData.getAccessData(MainActivity.getContext()).getAssistents();
        for(Assistents e : assistentsList){
            showingList.add(e.getId());
        }
    }
}