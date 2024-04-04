package com.example.sam_pak_boggle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class ControlBarFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val viewModel: BlankViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control_bar, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scoreBoard = view.findViewById<TextView>(R.id.scoreBoard)
        val newGameButton = view.findViewById<Button>(R.id.newGameButton)

        val scoreObserver = Observer<Int> { newScore ->
            // Update the UI, in this case, a TextView.
            scoreBoard.text = newScore.toString()
        }
        viewModel.currentScore.observe(this.viewLifecycleOwner,scoreObserver)


        //scoreBoard.text = viewModel.currentScore.toString()
        newGameButton.setOnClickListener{
            requireActivity().recreate()
        }

    }
}