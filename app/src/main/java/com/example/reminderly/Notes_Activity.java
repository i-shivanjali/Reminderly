package com.example.reminderly;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reminderly.databinding.ActivityNotesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Notes_Activity extends AppCompatActivity {

    ActivityNotesBinding binding;
    private NotesAdapter notesAdapter;

    private List<NotesModel> notesModelListss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesModelListss = new ArrayList<>();

        notesAdapter = new NotesAdapter(this);
        binding.notesRecycler.setAdapter(notesAdapter);
        binding.notesRecycler.setLayoutManager(new LinearLayoutManager(this));

        binding.floatingAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes_Activity.this, AddTask.class);
                startActivity(intent);
            }
        });

        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if(text.length()>0) {
                    filter(text);
                } else{
                    notesAdapter.clear();
                    notesAdapter.filterList(notesModelListss);
                }
            }
        });
    }

        private void filter(String text){
            List<NotesModel> adapterList = notesAdapter.getList();
            List<NotesModel> notesModelList = new ArrayList<>();
            for(int i=0;i<adapterList.size();i++){
                NotesModel notesModel= adapterList.get(i);
                if(notesModel.getTitle().toLowerCase().contains(text.toLowerCase()) || notesModel.getDesc().toLowerCase().contains(text.toLowerCase())){
                    notesModelList.add(notesModel);
                }
            }

        notesAdapter.filterList(notesModelList);
    }
    @Override
    protected void onResume(){
        super.onResume();

        getData();
    }
    private void getData(){
        FirebaseFirestore.getInstance()
                .collection("notes")
                .whereEqualTo("uid", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        notesAdapter.clear();
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                        for (int i=0;i<dsList.size();i++){
                            DocumentSnapshot documentSnapshot = dsList.get(i);
                            NotesModel notesModel = documentSnapshot.toObject(NotesModel.class);
                            notesModelListss.add(notesModel);
                            notesAdapter.add(notesModel);

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Notes_Activity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}