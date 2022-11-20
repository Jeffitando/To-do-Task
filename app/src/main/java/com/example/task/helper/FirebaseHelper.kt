package com.example.task.helper

import com.example.task.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {
    //Companion Object para poder chamar facilmente sem a necessidade de instanciar essa classe do firebaseHelper
    companion object {

        //Para recuperar a nossa instancia do banco de dados
        fun getDatabase() = FirebaseDatabase.getInstance().reference

        //Para recuperar a instancia de autenticação do usuario (saber se esta logado, pegar o id)
        private fun getAuth() = FirebaseAuth.getInstance()

        //Para recuperar o id do usuario que esta logado
        fun getIdUser() = getAuth().uid

        //Para verificar se o usuario esta autenticado no app
        fun isAutenticated() = getAuth().currentUser != null

        //Pegar o erro do firebase, identificar essas mensagens e mostrar elas em portugues para o usuario
        fun validError(error: String): Int {
            return when {
                error.contains("There is no user record corresponding to this identifier") -> {
                    R.string.account_not_registered_register_fragment
                }
                error.contains("The email address is badly formatted") -> {
                    R.string.Invalid_email_register_fragment
                }
                error.contains("The password is invalid or the user does not have a password") -> {
                    R.string.Invalid_password_register_fragment
                }
                error.contains("The email address is already in use by another account") -> {
                    R.string.email_in_use_register_fragment
                }
                error.contains("Password should be at least 6 characters") -> {
                    R.string.strong_password_register_fragment
                }
                else -> {
                    R.string.error_generic
                }
            }
        }
    }
}