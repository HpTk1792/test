package com.android.alejandroquiroga.ui.firebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.Models.Missatges;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> showingList;

    public FirebaseViewModel() {
        showingList = new MutableLiveData<>();
    }

    public LiveData<ArrayList<String>> getElementsList() { return showingList; }

    public void readElementsList() {

        final ArrayList<String> missatgesList = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("ExamenDb").child("Missatges").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Missatges missatges = i.getValue(Missatges.class);
                    missatgesList.add(missatges.getMissatge());
                }
                showingList.postValue(missatgesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void WriteOnFirebase(Missatges missatge){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("ExamenDb").child("Missatges").push();
        msg_reference.setValue(missatge);
    }
}