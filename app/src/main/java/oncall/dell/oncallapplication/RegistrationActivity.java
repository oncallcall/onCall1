package oncall.dell.oncallapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import oncall.dell.oncallapplication.ui.registrationfragment2.RegistrationFragment2;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email,password, name, phone;
    private Button register, already_registred;
    private String mUser,mPass, mName, mPhone;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registration);
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        register=findViewById(R.id.register);
        already_registred=findViewById(R.id.already_registred);
       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mUser=email.getText().toString().trim();
               mPass=password.getText().toString().trim();
               mName=name.getText().toString().trim();
               mPhone=phone.getText().toString().trim();
               if(TextUtils.isEmpty(mUser)){
                   email.setError("Please provid a mail ID");
                   return;
               }
               if(TextUtils.isEmpty(mName)){
                   name.setError("Please Enter Your Name");
                   return;
               }
               if(TextUtils.isEmpty(mPhone)){
                   phone.setError("Please Enter Your Phone No");
                   return;
               }
               if (!isValidEmail(mUser)) {
                   email.setError("Invalid Email Address");
                   return;
               }
               if(TextUtils.isEmpty(mPass)|| mPass.length()<6){
                   password.setError("Please provide a valid Password should have at lest 6 characters");
                   return;
               }
               mAuth.createUserWithEmailAndPassword(mUser,mPass).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                           Intent loginIntent=new Intent(RegistrationActivity.this,LoginActivity.class);
                           startActivity(loginIntent);
                       }
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       e.printStackTrace();
                   }
               });
           }
       });
       already_registred.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
               startActivity(intent);
           }
       });
        }
    public boolean isValidEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }
    }

