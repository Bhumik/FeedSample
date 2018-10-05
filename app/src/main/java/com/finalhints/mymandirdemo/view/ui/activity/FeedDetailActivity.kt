package com.finalhints.mymandirdemo.view.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.view.ui.fragment.FeedDetailFragment
import com.finalhints.mymandirdemo.view.viewmodel.FeedDetailViewModel

private const val EXTRA_KEY_DATA = "EXTRA_KEY_DATA"
private const val EXTRA_KEY_POSITION = "EXTRA_KEY_POSITION"

class FeedDetailActivity : AppCompatActivity() {
    companion object {
        fun startActivity(pContext: Context, feeditem: FeedItem, itemPosition: Int = 0) {
            val intent = Intent(pContext, FeedDetailActivity::class.java)
            intent.putExtra(EXTRA_KEY_DATA, feeditem)
            intent.putExtra(EXTRA_KEY_POSITION, itemPosition)
            pContext.startActivity(intent)
        }
    }

    /**
     * get instance of view model having live data of feed item list
     */
    private val mViewModel: FeedDetailViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FeedDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_list)

        //get feed item from parcel and set into view model which will be sued by feeddetail fragment using same viewmodel by passing activity context
        if (intent.hasExtra(EXTRA_KEY_DATA)) {
            val feedItem = intent.getParcelableExtra(EXTRA_KEY_DATA) as FeedItem;
            mViewModel.feedItem.value = feedItem
        }

        //set start position from which to show images
        mViewModel.currentPos = intent.getIntExtra(EXTRA_KEY_POSITION, 0)

        loadFeedFragment()
    }

    /**
     * method to load fragment
     */
    private fun loadFeedFragment() {
        val fragment = FeedDetailFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.fragment, fragment)
        transaction.commit()
    }

}
