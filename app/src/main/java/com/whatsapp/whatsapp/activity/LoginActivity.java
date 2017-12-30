package com.whatsapp.whatsapp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.whatsapp.whatsapp.R;
import com.whatsapp.whatsapp.config.ConfiguracaoFirebase;
import com.whatsapp.whatsapp.helper.Permissao;
import com.whatsapp.whatsapp.helper.Preferencias;


import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void abriCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity( intent);
        finish();
    }


}
