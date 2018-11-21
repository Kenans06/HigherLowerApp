package com.gebruiker.higher_lowerapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private val WINMESSAGE: String = "You Win!"
    private val LOSEMESSAGE: String = "You lose..."


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declare variables
        var mTextScore: TextView = findViewById(R.id.textScore)
        var mTextHighScore: TextView = findViewById(R.id.textHighScore)
        var currentImageIndex: Int
        var mLastThrow = 0
        var newThrow: Int
        var mScore = 0
        var mHighScore = 0
        val mButtonUp: ImageButton = findViewById(R.id.butUp)
        val mButtonDown: ImageButton = findViewById(R.id.butDown)
        val mImageView: ImageView = findViewById(R.id.ImageView1)
        val mImageNames: IntArray = intArrayOf(R.drawable.d1, R.drawable.d2,
                R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6)
        var mHistory: ArrayList<String> = ArrayList()
        var mAdapter: ArrayAdapter<String>
        var mListView: ListView = findViewById(R.id.Listview1)

        //initialize variables
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mHistory)
        mListView.adapter = mAdapter

        //function to randomly roll the dice and filling out the listview
        fun rollDice(): Int {
            //rolling random dice
            currentImageIndex = (Math.random() * ((5) + 1)).toInt()
            mImageView.setImageResource(mImageNames[currentImageIndex])

            //adding items to listview
            val newHistory: String = ("Your throw was: " + (currentImageIndex + 1).toString())
            mHistory.add(newHistory)
            mAdapter.notifyDataSetChanged()

            return currentImageIndex
        }

        //function to check if the answer was good or false and determine highscore
        fun checkScore() {
            if (mScore >= mHighScore) {
                mHighScore = mScore
            }
            val score = "Score: " + mScore.toString()
            val highScore = "HighScore: " + mHighScore.toString()
            mTextScore.text = score
            mTextHighScore.text = highScore

        }

        //checks if the up button is pressed
        mButtonUp.setOnClickListener {
            newThrow = rollDice()
            if (newThrow >= mLastThrow) {
                mScore++
                Snackbar.make(findViewById(android.R.id.content), WINMESSAGE, Snackbar.LENGTH_SHORT).show()
            } else {

                Snackbar.make(findViewById(android.R.id.content), LOSEMESSAGE, Snackbar.LENGTH_SHORT).show()
                mScore = 0
            }
            mLastThrow = newThrow
            checkScore()
        }

        //checks if the down button is pressed
        mButtonDown.setOnClickListener {
            newThrow = rollDice()
            if (newThrow <= mLastThrow) {
                mScore++
                Snackbar.make(findViewById(android.R.id.content), WINMESSAGE, Snackbar.LENGTH_SHORT).show()
            } else {
                mScore = 0
                Snackbar.make(findViewById(android.R.id.content), LOSEMESSAGE, Snackbar.LENGTH_SHORT).show()
            }
            mLastThrow = newThrow
            checkScore()
        }


    }

}
