package com.whatsapp.whatsapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.whatsapp.whatsapp.R;
import com.whatsapp.whatsapp.config.ConfiguracaoFirebase;
import com.whatsapp.whatsapp.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextView nome;
    private TextView email;
    private TextView senha;
    private Button botaoCadastro;
    private FirebaseAuth autenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (TextView) findViewById(R.id.nome);
        email = (TextView) findViewById(R.id.email);
        senha = (TextView) findViewById(R.id.senha);
        botaoCadastro = (Button) findViewById(R.id.button_cadastrar);

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setNome( nome.getText().toString());
                usuario.setEmail( email.getText().toString());
                usuario.setSenha( senha.getText().toString());
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG ).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId( usuarioFirebase.getUid() );
                    usuario.salvar();
                    autenticacao.signOut();
                    finish();

                }else {
                    //Toast.makeText(CadastroUsuarioActivity.this, "Erro ao cadastrar usuário: ", Toast.LENGTH_LONG ).show();
                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    }catch( FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte, contendo mais caracteres e com letras e números!";

                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "O e-mail digitado é inválido, digite um novo e-mail";
                    } catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Esse e-mail já está em uso no App!";
                    } catch (Exception e){
                        erroExcecao = "Ao cadastrar usuario";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: "+ erroExcecao, Toast.LENGTH_SHORT).show();









                }
            }
        });
    }
}
