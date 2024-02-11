package com.example.registrationsplashscreen;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextDOB, editTextEmail, editTextPassword;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        initDatePicker();

        editTextDOB.setFocusable(false);
        editTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    private void initDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                String date = dayOfMonth + "/" + monthOfYear + "/" + year;
                editTextDOB.setText(date);
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
    }

    public void onRegisterClick(View view) {
        if (validateAll()) {
            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean NameCheck(String value, String FirstLastName, int minLen, int maxLen) {
        if (value.trim().isEmpty() || value.trim().length() < minLen || value.trim().length() > maxLen) {
            String errormessage =" Not Acceptable! Name Must be between 3-30 characters ";
            if ("FirstLastName".equals(FirstLastName)) {
                editTextFirstName.setError(errormessage);
            } else {
                editTextLastName.setError(errormessage);
            }
            return false;
        }
        return true;
    }
    private boolean validateAll() {
        if (!NameCheck(editTextFirstName.getText().toString(), "First Name", 3, 30)) {
            return false;
        }

        if (!NameCheck(editTextLastName.getText().toString(), "Last Name", 3, 30)) {
            return false;
        }

        if (editTextDOB.getText().toString().trim().isEmpty()) {
            editTextDOB.setError("Required Field !");
            return false;
        }

        if (!validateEmail(editTextEmail.getText().toString())) {
            editTextEmail.setError("Invalid! Try Again!");
            return false;
        }

        if (editTextPassword.getText().toString().trim().isEmpty() || editTextPassword.getText().toString().trim().length() < 6) {
            editTextPassword.setError("Weak Password! Try Again!");
            return false;
        }

        return true;
    }


    private boolean validateEmail(String email) {
        return Pattern.compile("[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}").matcher(email).matches();
    }
}
