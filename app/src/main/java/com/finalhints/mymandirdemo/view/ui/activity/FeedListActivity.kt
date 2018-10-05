package com.finalhints.mymandirdemo.view.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.finalhints.mymandirdemo.view.ui.fragment.FeedFragment
import com.finalhints.mymandirdemo.view.ui.fragment.FeedFragment2
import kotlinx.android.synthetic.main.activity_feed_list.*

private const val EXTRA_DESIGN_TYPE = "EXTRA_DESIGN_TYPE"

class FeedListActivity : AppCompatActivity() {

    companion object {
        fun startActivity(pContext: Context, typePos: Int = 0) {
            val intent = Intent(pContext, FeedListActivity::class.java)
            intent.putExtra(EXTRA_DESIGN_TYPE, typePos)
            pContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_list)
        setSupportActionBar(toolbar)


        val type = intent.getIntExtra(EXTRA_DESIGN_TYPE, 0)

        when (type) {
            0 -> loadFeedListFragment()
            1 -> loadFeedListFragment2()
        }
    }

    /**
     * load main feed list fragment
     */
    private fun loadFeedListFragment() {
        val fragment = FeedFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.fragment, fragment)
        transaction.commit()
    }

    /**
     * load main feed list fragment
     */
    private fun loadFeedListFragment2() {
        val fragment = FeedFragment2.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.fragment, fragment)
        transaction.commit()
    }

}
