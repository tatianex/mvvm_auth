package com.proway.mvvm_auth.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.mvvm_auth.model.Account
import com.proway.mvvm_auth.repository.AccountRepository

class ContentViewModel : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val accountRepository = AccountRepository()

    fun fetchAccounts(){
        accountRepository.fetchAccounts { accounts, error ->
            if (error != null) {
                _error.value = error
            }
            else {
                _accounts.value = accounts
            }
        }
    }
}