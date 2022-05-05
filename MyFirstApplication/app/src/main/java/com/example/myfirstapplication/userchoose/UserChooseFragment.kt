package com.example.myfirstapplication.userchoose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R


class UserChooseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_userchoose, container, false)


        view.findViewById<CardView>(R.id.parent_card_view).setOnClickListener {
            findNavController().navigate(R.id.action_user_choose_to_sign_up)
        }

        view.findViewById<CardView>(R.id.child_card_view).setOnClickListener {
            findNavController().navigate(R.id.action_user_choose_to_login_in)
        }
        return view
    }

}