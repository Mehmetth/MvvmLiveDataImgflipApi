package com.mpetek.memesgenerator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mpetek.memesgenerator.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_meme.*
import kotlinx.android.synthetic.main.memes_rv_item.view.*

class ShowMemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_meme)

        val url = intent.getStringExtra("url").toString()
        val id = intent.getStringExtra("id").toString()
        val name = intent.getStringExtra("name").toString()

        Picasso.get()
            .load(url)
            .into(image)

        id_textview.text = id
        name_textview.text = name
    }
}