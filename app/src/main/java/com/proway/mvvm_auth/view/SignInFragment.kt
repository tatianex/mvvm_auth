package com.proway.mvvm_auth.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.proway.mvvm_auth.MainActivity
import com.proway.mvvm_auth.R
import com.proway.mvvm_auth.view_model.SignInViewModel

class SignInFragment : Fragment(R.layout.sign_in_fragment) {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel

    // observer para user
    private val observerUser = Observer<FirebaseUser> {
        (requireActivity() as? MainActivity)?.replaceView(ContentFragment.newInstance())
    }

    // observer para erro
    private val observerError = Observer<String> {
        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        viewModel.error.observe(viewLifecycleOwner, observerError)
        viewModel.user.observe(viewLifecycleOwner, observerUser)

        /*
        * Add events on components view
        */

        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val inputEmail = view.findViewById<EditText>(R.id.inputEmailEditText)
            val inputPassword = view.findViewById<EditText>(R.id.inputPassowordEditText)
            if (!inputEmail.text.isNullOrEmpty() && !inputPassword.text.isNullOrEmpty()) {
                viewModel.signIn(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                )
            }
        }

        view.findViewById<TextView>(R.id.newAccountTextView).setOnClickListener {
            (requireActivity() as? MainActivity)?.replaceView(SignUpFragment.newInstance())
        }
    }
}