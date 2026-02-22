package com.example.patientinformationapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PatientDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        TextView name = findViewById(R.id.tvName);
        TextView age = findViewById(R.id.tvAge);
        TextView phone = findViewById(R.id.tvPhone);
        TextView gender = findViewById(R.id.tvGender);
        TextView illness = findViewById(R.id.tvIllness);
        TextView date = findViewById(R.id.tvDate);

        Button call = findViewById(R.id.btnCall);
        Button sms = findViewById(R.id.btnSms);
        Button email = findViewById(R.id.btnEmail);

        String n = getIntent().getStringExtra("name");
        String a = getIntent().getStringExtra("age");
        String p = getIntent().getStringExtra("phone");
        String g = getIntent().getStringExtra("gender");
        String ill = getIntent().getStringExtra("illness");
        String d = getIntent().getStringExtra("date");

        name.setText("Name: " + n);
        age.setText("Age: " + a);
        phone.setText("Phone: " + p);
        gender.setText("Gender: " + g);
        illness.setText("Illness: " + ill);
        date.setText("Date of Visit: " + d);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PatientDetailsActivity.this)
                        .setTitle("Call Doctor")
                        .setMessage("Are you sure you want to call the doctor?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + p));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PatientDetailsActivity.this)
                        .setTitle("Send SMS")
                        .setMessage("Are you sure you want to send an SMS to the doctor?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("sms:" + p));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"doctor@example.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Patient Information");
                intent.putExtra(Intent.EXTRA_TEXT, "Name: " + n + "\nAge: " + a + "\nPhone: " + p + "\nGender: " + g + "\nIllness: " + ill + "\nDate of Visit: " + d);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }
}
