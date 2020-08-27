package com.interview.interviewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.interview.interviewproject.list.ListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InterviewApplication.getInstance()
        main_next_page.setOnClickListener {
            startActivity(Intent(this,
                ListActivity::class.java))
        }

    }
}