package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.StartAppActivities.MainActivity;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 *  Allow patient to edit profile detail.
 */
public class EditProfileActivity extends AppCompatActivity {

    private TextView UserIdText, EmailText, PhoneText;
    private Button CancelButton, SaveChangesButton, logOutButton, displayQRButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        String User_Id = Backend.getInstance().getPatientProfile().getID();
        String Email = Backend.getInstance().getPatientProfile().getEmail();
        String Phone = Backend.getInstance().getPatientProfile().getPhone();

        UserIdText = findViewById(R.id.user_id_textview);
        UserIdText.setText(getString(R.string.user_id) + User_Id); // TODO: fix this so IDE is happy
        EmailText = findViewById(R.id.emailText);
        EmailText.setText(Email);
        PhoneText = findViewById(R.id.phoneText);
        PhoneText.setText(Phone);

        CancelButton = findViewById(R.id.cancelButton);
        SaveChangesButton = findViewById(R.id.saveChangesButton);
        logOutButton = findViewById(R.id.logOutButton);
        ImageView qrCode = findViewById(R.id.qrImage);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(User_Id, BarcodeFormat.QR_CODE, 220,220);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backend.getInstance().clearPatientData();
                // https://stackoverflow.com/questions/6330260/finish-all-previous-activities
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        SaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Backend.getInstance().isConnected()) {
                    // in case uploading fails
                    String temp_email = Backend.getInstance().getPatientProfile().getEmail();
                    String temp_phone = Backend.getInstance().getPatientProfile().getPhone();
                    // try to upload
                    Backend.getInstance().getPatientProfile().setEmail(EmailText.getText().toString());
                    Backend.getInstance().getPatientProfile().setPhone(PhoneText.getText().toString());
                    if (Backend.getInstance().UpdatePatient())
                    {
                        finish();
                    } else // if fail revert changes
                    {
                        Backend.getInstance().getPatientProfile().setEmail(temp_email);
                        Backend.getInstance().getPatientProfile().setPhone(temp_phone);
                        Toast.makeText(getApplicationContext(), (String) "Could not save profile!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
