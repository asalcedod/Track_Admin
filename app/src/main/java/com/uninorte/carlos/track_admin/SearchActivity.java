package com.uninorte.carlos.track_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements Adapter.RecyclerClickListner {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String TAG= "FirebaseLog";
    final ArrayList<Employee> employees= new ArrayList<>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mToolbar = (Toolbar) findViewById(R.id.toolbarSearchActivity);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewSearchActivty);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mToolbar.setTitle("Seleccione un Empleado");
        setSupportActionBar(mToolbar);
        String nombre = getIntent().getStringExtra("name");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                     ) {
                    Log.d(TAG, "onDataChange: "+data.getKey());

                    employees.add(new Employee(data.getKey()));
                    // specify an adapter (see also next example)
                    mAdapter = new Adapter(employees);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void itemClick(View view, int position) {
        Intent intent = new Intent(this, MapsActivity.class);
        TextView textView = (TextView) view.findViewById(R.id.row_textview);

        intent.putExtra("name", getIntent().getStringExtra("name"));
        startActivityForResult(intent, 0);
    }
}
