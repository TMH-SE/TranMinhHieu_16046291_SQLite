package com.example.tranminhhieu_16046291_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtSubject;
    Spinner spnClassName;
    Button btnThem, btnXem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final StudentManager studentManager = new StudentManager(this, "SVDB");

        Lop lop1 = new Lop("DHKTPM12A");
        studentManager.themLop(lop1);

        Lop lop2 = new Lop("DHKTPM12B");
        studentManager.themLop(lop2);

        Lop lop3 = new Lop("DHCNTT12A");
        studentManager.themLop(lop3);

        Lop lop4 = new Lop("DHCNTT12B");
        studentManager.themLop(lop4);

        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentManager.getAllLop());

        final Intent intent = new Intent(MainActivity.this, ViewStudentActivity.class);

        btnXem = findViewById(R.id.btnXem);
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        edtName = findViewById(R.id.edtName);
        spnClassName = findViewById(R.id.spnClassName);
        spnClassName.setAdapter(lopArrayAdapter);
        edtSubject = findViewById(R.id.edtSubject);

        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String class_name = ((Lop) spnClassName.getSelectedItem()).getName();
                String subject = edtSubject.getText().toString();
                if (name.equalsIgnoreCase("") || class_name.equalsIgnoreCase("") || subject.equalsIgnoreCase("")) {
                    Toast.makeText(view.getContext(), "Please fill in all input!", Toast.LENGTH_LONG).show();
                } else {
                    studentManager.themSV(new SV(name, class_name, subject));
                    Toast.makeText(view.getContext(), "Success!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
}
