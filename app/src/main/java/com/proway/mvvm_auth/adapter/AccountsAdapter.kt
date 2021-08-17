package com.proway.mvvm_auth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.proway.mvvm_auth.R
import com.proway.mvvm_auth.model.Account

class AccountsAdapter: RecyclerView.Adapter<AccountViewHolder>() {

    private var listOfAccounts: MutableList<Account> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        listOfAccounts[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOfAccounts.size

    fun refresh(newList: List<Account>) {
        listOfAccounts = arrayListOf()
        listOfAccounts.addAll(newList)
        notifyDataSetChanged()
    }
}

class AccountViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(account: Account){
        setData(account.uid, R.id.uidTextView)
        setData(account.name, R.id.nameTextView)
        setData(account.price.toString(), R.id.priceTextView)
    }

    private fun setData(value: String?, @IdRes componentId: Int) {
        itemView.findViewById<TextView>(componentId).apply {
            text = value
        }
    }
}