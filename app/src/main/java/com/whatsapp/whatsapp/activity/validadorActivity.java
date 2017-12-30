package com.whatsapp.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsapp.whatsapp.R;
import com.whatsapp.whatsapp.helper.Preferencias;

import java.util.HashMap;

public class validadorActivity extends AppCompatActivity {

    private Button botao;
    private TextView codigoValidacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        botao = (Button) findViewById(R.id.bt_validar);
        codigoValidacao = (TextView) findViewById(R.id.codigo_validacao);

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher mascaraCodigo = new MaskTextWatcher(codigoValidacao, simpleMaskFormatter);

        codigoValidacao.addTextChangedListener(mascaraCodigo);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Recupera dados das preferencias

                Preferencias preferencias = new Preferencias(validadorActivity.this);
                HashMap<String, String> usuario = preferencias.getDadosUsuario();

                String token = usuario.get("token");
                String tokenDigitado = codigoValidacao.getText().toString();

                if ( tokenDigitado.equals(token) ){
                    Toast.makeText(validadorActivity.this, "Token validado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(validadorActivity.this, "Token invalidado", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
