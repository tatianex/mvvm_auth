package com.proway.mvvm_auth.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.proway.mvvm_auth.model.Account

class AccountRepository {

    private val dataBase = FirebaseFirestore.getInstance()

    fun fetchAccounts(callback: (List<Account>?, String?) -> Unit) {
        dataBase.collection("accounts")
            .get()
            .addOnSuccessListener { result ->

                val listOf = arrayListOf<Account>()
                result.forEach {
                    val account = Account.fromData(it)
                    listOf.add(account)
                }
                callback(listOf, null)
            }
            .addOnFailureListener { exception ->
                callback(null, exception.message)
            }
    }
}
