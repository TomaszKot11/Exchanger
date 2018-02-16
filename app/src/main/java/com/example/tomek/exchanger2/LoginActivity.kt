package com.example.tomek.exchanger2

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity()  {


    //textLogiIn etLogEmail etLogPassword
    //btnLogIn textSIgnUp

    // var representing authentication object
    private var fireBaseAuthe : FirebaseAuth = FirebaseAuth.getInstance()
    / Progress Bar to show the progress of registration process
    private  var  progressDialog : ProgressDialog = ProgressDialog(this)
    /

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogIn.setOnClickListener(btnListener)
    }

    // btnListner with a special lambda
    private val btnListener = View.OnClickListener { view->
        // when is like Java's switch
        when(view.id){
            R.id.registerButton -> logIn()
            R.id.textSignIn -> {
                finish() // finish method runs onDestroy method (Stack)
                startActivity(Intent(this, MainActivity.class))     // ???????
            }
        }
    }


    fun logIn():Unit{
        // log in the use
        var email:String=etLogEmail.text.toString().trim()
        var password:String = etLogPassword.text.toString().trim()
        if(email.isEmpty() || password.isEmpty()){
            // user cannot go futher without filling this fields
            Toast.makeText(this,"Password or E-mail is empty", Toast.LENGTH_SHORT).show()
            return
        }

        // set message in Kotlin???
        progressDialog.setMessage("Loging in...")
        progressDialog.show()


        fireBaseAuthe.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(this, OnCompleteListener {
                    view ->
                    if(view.isSuccessful){
                        // user is successfully registered and loggwed in
                        // we will start the profile activity here
                        Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this," Failed to register",Toast.LENGTH_SHORT).show()

                    }

                })





    }



}