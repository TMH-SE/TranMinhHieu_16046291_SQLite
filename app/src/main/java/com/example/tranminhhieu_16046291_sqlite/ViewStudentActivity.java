package com.example.tranminhhieu_16046291_sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    private ListView listSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final StudentManager studentManager = new StudentManager(this, "SVDB");

        listSV = findViewById(R.id.listSV);
        List<SV> svs = studentManager.getAllSVs();
        final ArrayAdapter<SV> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , svs);
        listSV.setAdapter(arrayAdapter);

        listSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to delete item?");
                builder.setCancelable(true);
                final SV sv = arrayAdapter.getItem(i);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrayAdapter.remove(sv);
                                studentManager.xoaSV(sv);
                            }
                        }
                );
                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }
                );
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ViewStudentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
