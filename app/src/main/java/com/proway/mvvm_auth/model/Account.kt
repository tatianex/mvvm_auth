package com.proway.mvvm_auth.model

import com.google.firebase.firestore.QueryDocumentSnapshot

data class Account(
    val uid: String?,
    val name: String?,
    val price: Double?
) {
    companion object {

        fun fromData(snapshot: QueryDocumentSnapshot): Account {
            return Account(
                uid = snapshot.id,
                name = snapshot.data["name"] as? String,
                price = snapshot.data["price"] as? Double
            )
        }
    }
}
