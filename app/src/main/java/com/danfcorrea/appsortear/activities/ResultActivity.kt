package com.danfcorrea.appsortear.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danfcorrea.appsortear.R
import com.danfcorrea.appsortear.adapters.ResultAdapter

class ResultActivity : AppCompatActivity() {

    private lateinit var adapter: ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val numberList = intent.getIntegerArrayListExtra("NUMBER_LIST")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerResult)
        val numberOfColumns = 3
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = numberList?.let { ResultAdapter(this, it) }!!
        recyclerView.adapter = adapter
    }

}
