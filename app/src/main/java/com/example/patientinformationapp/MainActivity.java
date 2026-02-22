package com.example.patientinformationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText name, age, phone;
    RadioGroup gender;
    Spinner illness;
    DatePicker datePicker;
    Button submit;

    String illnessList[] = {"Fever","Cold","Headache","Diabetes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etName);
        age = findViewById(R.id.etAge);
        phone = findViewById(R.id.etPhone);
        gender = findViewById(R.id.radioGroup);
        illness = findViewById(R.id.spinnerIllness);
        datePicker = findViewById(R.id.datePicker);
        submit = findViewById(R.id.btnSubmit);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                illnessList);

        illness.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name.getText().toString();
                String a = age.getText().toString();
                String p = phone.getText().toString();

                int selected = gender.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selected);

                String g = rb.getText().toString();

                String ill = illness.getSelectedItem().toString();

                String date =
                        datePicker.getDayOfMonth()+"/"+
                                (datePicker.getMonth()+1)+"/"+
                                datePicker.getYear();

                Intent i = new Intent(MainActivity.this,
                        PatientDetailsActivity.class);

                i.putExtra("name",n);
                i.putExtra("age",a);
                i.putExtra("phone",p);
                i.putExtra("gender",g);
                i.putExtra("illness",ill);
                i.putExtra("date",date);

                startActivity(i);

            }
        });

    }
}