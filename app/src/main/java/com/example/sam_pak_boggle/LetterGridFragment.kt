package com.example.sam_pak_boggle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import java.io.File
import java.io.InputStream

class LetterGridFragment : Fragment() {

    private val viewModel: BlankViewModel by viewModels()

    //var totalScore = viewModel.currentScore
    var totalScore = 0
    var usedWords = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_letter_grid, container, false)
    }

    fun getRandomLetter() : String {
        val allowedChars = ('A'..'Z')
        return allowedChars.random().toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ioStream = requireActivity().assets.open("dictionary.txt")
        //val dictFile = File(requireActivity().filesDir, "dictionary.txt")
        //val inputStream: InputStream = dictFile.inputStream()
        val lineList = mutableListOf<String>()

        ioStream.bufferedReader().forEachLine { lineList.add(it) }

        val button00 = view.findViewById<Button>(R.id.button00)
        val button10 = view.findViewById<Button>(R.id.button10)
        val button20 = view.findViewById<Button>(R.id.button20)
        val button30 = view.findViewById<Button>(R.id.button30)
        val button01 = view.findViewById<Button>(R.id.button01)
        val button11 = view.findViewById<Button>(R.id.button11)
        val button21 = view.findViewById<Button>(R.id.button21)
        val button31 = view.findViewById<Button>(R.id.button31)
        val button02 = view.findViewById<Button>(R.id.button02)
        val button12 = view.findViewById<Button>(R.id.button12)
        val button22 = view.findViewById<Button>(R.id.button22)
        val button32 = view.findViewById<Button>(R.id.button32)
        val button03 = view.findViewById<Button>(R.id.button03)
        val button13 = view.findViewById<Button>(R.id.button13)
        val button23 = view.findViewById<Button>(R.id.button23)
        val button33 = view.findViewById<Button>(R.id.button33)
        val clearButton = view.findViewById<Button>(R.id.clearButton)
        val submitButton = view.findViewById<Button>(R.id.submitButton)
        val currentWord = view.findViewById<TextView>(R.id.currentWord)
        val scoreDisplay = view.findViewById<TextView>(R.id.currentScore)

        button00.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button10.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button20.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button30.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button01.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button11.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button21.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button31.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button02.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button12.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button22.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button32.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button03.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button13.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button23.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
        button33.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))


        var clicked00 = false
        var clicked10 = false
        var clicked20 = false
        var clicked30 = false
        var clicked01 = false
        var clicked11 = false
        var clicked21 = false
        var clicked31 = false
        var clicked02 = false
        var clicked12 = false
        var clicked22 = false
        var clicked32 = false
        var clicked03 = false
        var clicked13 = false
        var clicked23 = false
        var clicked33 = false

        var cursorX = -1
        var cursorY = -1

        clearButton.setOnClickListener {
            clicked00 = false
            clicked10 = false
            clicked20 = false
            clicked30 = false
            clicked01 = false
            clicked11 = false
            clicked21 = false
            clicked31 = false
            clicked02 = false
            clicked12 = false
            clicked22 = false
            clicked32 = false
            clicked03 = false
            clicked13 = false
            clicked23 = false
            clicked33 = false

            cursorX = -1
            cursorY = -1

            button00.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button10.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button20.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button30.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button01.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button11.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button21.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button31.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button02.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button12.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button22.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button32.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button03.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button13.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button23.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))
            button33.setBackgroundColor(resources.getColor(com.google.android.material.R.color.button_material_dark))

            currentWord.text = ""
        }

        submitButton.setOnClickListener {
            val wordToSubmit = currentWord.text.toString()
            var score = -10
            if(wordToSubmit.length>=4) {
                score = 0
                var vowelCount = 0
                var doubleVal = false
                for (c: Char in wordToSubmit) {
                    when (c) {
                        'A' -> {
                            vowelCount += 1
                            score += 5
                        }
                        'E' -> {
                            vowelCount += 1
                            score += 5
                        }
                        'I' -> {
                            vowelCount += 1
                            score += 5
                        }
                        'O' -> {
                            vowelCount += 1
                            score += 5
                        }
                        'U' -> {
                            vowelCount += 1
                            score += 5
                        }
                        'S' -> {
                            doubleVal = true
                            score += 1
                        }
                        'Z' -> {
                            doubleVal = true
                            score += 1
                        }
                        'P' -> {
                            doubleVal = true
                            score += 1
                        }
                        'X' -> {
                            doubleVal = true
                            score += 1
                        }
                        'Q' -> {
                            doubleVal = true
                            score += 1
                        }
                        else -> score += 1
                    }
                }
                if(doubleVal){
                    score *= 2
                }
                if(vowelCount<2){
                    val toast = Toast.makeText(this.context,"Not enough vowels",Toast.LENGTH_SHORT)
                    toast.show()
                    score = -10
                }
                if(usedWords.contains(wordToSubmit)){
                    score = 0
                    val toast = Toast.makeText(this.context,"word already used",Toast.LENGTH_SHORT)
                    toast.show()
                }else{
                    usedWords.add(wordToSubmit)
                }
                if(!lineList.contains(wordToSubmit)){
                    score = -10
                    val toast = Toast.makeText(this.context,"word does not exist",Toast.LENGTH_SHORT)
                    toast.show()
                }
            }else{
                val toast = Toast.makeText(this.context,"Word is too short",Toast.LENGTH_SHORT)
                toast.show()
            }
            val toast = Toast.makeText(this.context,"Points: $score",Toast.LENGTH_SHORT)
            toast.show()

            totalScore += score
            viewModel.currentScore.setValue(totalScore)
            scoreDisplay.text = totalScore.toString()
            clearButton.callOnClick()
        }

        button00.text = getRandomLetter()
        button10.text = getRandomLetter()
        button20.text = getRandomLetter()
        button30.text = getRandomLetter()
        button01.text = getRandomLetter()
        button11.text = getRandomLetter()
        button21.text = getRandomLetter()
        button31.text = getRandomLetter()
        button02.text = getRandomLetter()
        button12.text = getRandomLetter()
        button22.text = getRandomLetter()
        button32.text = getRandomLetter()
        button03.text = getRandomLetter()
        button13.text = getRandomLetter()
        button23.text = getRandomLetter()
        button33.text = getRandomLetter()

        button00.setOnClickListener {
            if(!clicked00) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 0
                    cursorY = 0
                }
                if(cursorX <= 1 && cursorY <= 1) {
                    cursorX = 0
                    cursorY = 0
                    clicked00 = true
                    button00.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button00.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button10.setOnClickListener {
            if(!clicked10) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 1
                    cursorY = 0
                }
                if(cursorX in 0..2 && cursorY <= 1) {
                    cursorX = 1
                    cursorY = 0
                    clicked10 = true
                    button10.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button10.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button20.setOnClickListener {
            if(!clicked20) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 2
                    cursorY = 0
                }
                if(cursorX in 1..3 && cursorY <= 1) {
                    cursorX = 2
                    cursorY = 0
                    clicked20 = true
                    button20.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button20.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button30.setOnClickListener {
            if(!clicked30) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 3
                    cursorY = 0
                }
                if(cursorX >= 2 && cursorY <=1) {
                    cursorX = 3
                    cursorY = 0
                    clicked30 = true
                    button30.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button30.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button01.setOnClickListener {
            if(!clicked01) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 0
                    cursorY = 1
                }
                if(cursorX <= 1 && cursorY in 0..2) {
                    cursorX = 0
                    cursorY = 1
                    clicked01 = true
                    button01.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button01.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button11.setOnClickListener {
            if(!clicked11) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 1
                    cursorY = 1
                }
                if(cursorX in 0..2 && cursorY in 0..2) {
                    cursorX = 1
                    cursorY = 1
                    clicked11 = true
                    button11.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button11.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button21.setOnClickListener {
            if(!clicked21) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 2
                    cursorY = 1
                }
                if(cursorX in 1..3 && cursorY in 0..2) {
                    cursorX = 2
                    cursorY = 1
                    clicked21 = true
                    button21.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button21.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button31.setOnClickListener {
            if(!clicked31) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 3
                    cursorY = 1
                }
                if(cursorX >= 2 && cursorY in 0..2) {
                    cursorX = 3
                    cursorY = 1
                    clicked31 = true
                    button31.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button31.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button02.setOnClickListener {
            if(!clicked02) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 0
                    cursorY = 2
                }
                if(cursorX <= 1 && cursorY in 1..3) {
                    cursorX = 0
                    cursorY = 2
                    clicked02 = true
                    button02.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button02.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button12.setOnClickListener {
            if(!clicked12) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 1
                    cursorY = 2
                }
                if(cursorX in 0..2 && cursorY in 1..3) {
                    cursorX = 1
                    cursorY = 2
                    clicked12 = true
                    button12.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button12.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button22.setOnClickListener {
            if(!clicked22) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 2
                    cursorY = 2
                }
                if(cursorX in 1..3 && cursorY in 1..3) {
                    cursorX = 2
                    cursorY = 2
                    clicked22 = true
                    button22.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button22.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button32.setOnClickListener {
            if(!clicked32) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 3
                    cursorY = 2
                }
                if(cursorX >= 2 && cursorY in 1..3) {
                    cursorX = 3
                    cursorY = 2
                    clicked32 = true
                    button32.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button32.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button03.setOnClickListener {
            if(!clicked03) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 0
                    cursorY = 3
                }
                if(cursorX <= 1 && cursorY >= 2) {
                    cursorX = 0
                    cursorY = 3
                    clicked03 = true
                    button03.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button03.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button13.setOnClickListener {
            if(!clicked13) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 1
                    cursorY = 3
                }
                if(cursorX in 0..2 && cursorY >= 2) {
                    cursorX = 1
                    cursorY = 3
                    clicked13 = true
                    button13.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button13.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button23.setOnClickListener {
            if(!clicked23) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 2
                    cursorY = 3
                }
                if(cursorX in 1..3 && cursorY >= 2) {
                    cursorX = 2
                    cursorY = 3
                    clicked23 = true
                    button23.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button23.text.toString()
                    currentWord.text = currentText
                }
            }
        }
        button33.setOnClickListener {
            if(!clicked33) {
                if(cursorX == -1 && cursorY == -1){
                    cursorX = 3
                    cursorY = 3
                }
                if(cursorX >= 2 && cursorY >= 2) {
                    cursorX = 3
                    cursorY = 3
                    clicked33 = true
                    button33.setBackgroundColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
                    var currentText = currentWord.text.toString()
                    currentText += button33.text.toString()
                    currentWord.text = currentText
                }
            }
        }


    }

}