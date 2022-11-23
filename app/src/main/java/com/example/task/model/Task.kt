package com.example.task.model

import android.accounts.AuthenticatorDescription
import android.os.Parcelable
import com.example.task.helper.FirebaseHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id: String = "",
    var description: String = "",
    var status: Int = 0
) : Parcelable {
    init {
        //Para cada tarefa ter um Id unico no firebase
        this.id = FirebaseHelper.getDatabase().push().key ?: ""
    }
}
