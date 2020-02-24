package com.android.alejandroquiroga.ui.firebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.alejandroquiroga.Models.ExampleElement;
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

    public LiveData<ArrayList<String>> getlistaMensajes() { return showingList; }

    public void readlistaMensajes() {

        final ArrayList<String> exampleElementList = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("ExamenDb").child("ExampleElements").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    ExampleElement exampleElement = i.getValue(ExampleElement.class);
                    exampleElementList.add(exampleElement.getId());
                }
                showingList.postValue(exampleElementList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void WriteOnFirebase(ExampleElement exampleElement){

        exampleElement = new ExampleElement("1", "2", "3");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("ExamenDb").child("ExampleElements").push();
        msg_reference.setValue(exampleElement);

//                "pVBEOgINbVOGdkne7Sjhg3ycW6h2", msg));
//        db.child("ExamenDb").child("usuarios").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("mensaje")
//                .setValue(msg_reference.getKey());

    }
}