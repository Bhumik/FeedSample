package com.finalhints.mymandirdemo.view.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  setSupportActionBar(toolbar)

    }

    /**
     * load main feed list fragment
     */
    public fun loadDesign1(view: View) {
        FeedListActivity.startActivity(this, 0)
    }

    /**
     * load main feed list fragment
     */
    public fun loadDesign2(view: View) {
        FeedListActivity.startActivity(this, 1)
    }

}
