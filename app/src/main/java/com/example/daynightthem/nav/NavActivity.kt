package com.example.daynightthem.nav

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daynightthem.R.layout

const val INTENT_USER = "user_intent"
const val INTENT_USER_ID = "user_intent_id"
const val INTENT_USER_NAME = "user_intent_name"

class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_nav)
    }

    companion object {

        fun launch(context: Context, userId: Int, userName: String) {

            val intent = Intent(context, NavActivity::class.java).apply {
                putExtra(INTENT_USER_ID, userId)
                putExtra(INTENT_USER_NAME, userName)
            }
            context.startActivity(intent)

        }
    }

}