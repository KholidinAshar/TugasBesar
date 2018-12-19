package com.example.ashar.loginsqlite;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    //Declarasi EditTexts
    EditText editTextEmail;
    EditText editTextPassword;

    //Declarasi TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declarasi Button
    Button buttonLogin;

    //Declarasi SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //membuat login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //membuat percabangan untuk mengecek kondisi login benar apa tidak
                if (validate()) {

                    //mengambil nilai dari EditText
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    //Check user benar atau tidak
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Sukses Login!", Snackbar.LENGTH_LONG).show();

                        //jika User Login benar maka akan masuk ke mainactivity
                        Intent intent=new Intent(LoginActivity.this,MainContent.class);
                        startActivity(intent);
                        finish();

                    } else {

                        //jika User gagal
                        Snackbar.make(buttonLogin, "Gagal untuk login , coba lagi", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }
    //membuat buton text untuk ke mainActivationRegistrasi
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#fff000'>Tidak mempunyai Akun. </font><font color='#0c0099'>Register</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //Metode untuk validasi
    public boolean validate() {
        boolean valid = false;

        //Mengambil data dari EditText
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //validasi untuk email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Validasi untuk panjang minimal  password
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }


}