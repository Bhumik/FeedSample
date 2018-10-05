package com.finalhints.mymandirdemo.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.ui.fragment.FeedViewModel
import com.finalhints.mymandirdemo.utils.AppUtils
import com.finalhints.mymandirdemo.view.adapter.FeedListAdapter
import com.finalhints.mymandirdemo.view.callbacks.FeedItemsActionListener
import com.finalhints.mymandirdemo.view.ui.activity.FeedDetailActivity
import com.finalhints.mymandirdemo.view.ui.base.BaseFragment
import com.finalhints.mymandirdemo.widgets.EmptyStateView
import com.finalhints.mymandirdemo.widgets.SpaceItemDecoration
import kotlinx.android.synthetic.main.feed_fragment.*

class FeedFragment : BaseFragment() {

    companion object {
        fun newInstance(): FeedFragment {
            val feedFragment = FeedFragment()
            val sArgs = Bundle()
            feedFragment.arguments = sArgs
            return feedFragment
        }
    }

    private val mFeedItems: ArrayList<FeedItem> = ArrayList()

    private lateinit var mAdapter: FeedListAdapter

    /**
     * get instance of view model having live data of feed item list
     */
    private val mViewModel: FeedViewModel by lazy {
        ViewModelProvider(activity!!, ViewModelProvider.NewInstanceFactory()).get(FeedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //mViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapUIelements(view)
    }

    private fun mapUIelements(view: View) {

        initRecyclerView()

        mViewModel.feedItemList.observe(this, Observer {
            mFeedItems.clear()
            mFeedItems.addAll(it)
            mAdapter.notifyDataSetChanged()
        })

        mViewModel.errorDesc.observe(this, Observer {
            emptyState.isVisible = true
            emptyState.initView(it,
                    "Please try again after some time.",
                    R.drawable.dinasaur,
                    "Retry",
                    object : EmptyStateView.EmptyStateActionListener {
                        override fun onActionClick() {
                            mViewModel.getFeedItems()
                        }
                    })
        })

        mViewModel.emptyState.observe(this, Observer {
            when (it) {
                AppUtils.EmptyState.GONE -> {
                    emptyState.isVisible = (mFeedItems.size == 0)
                }
                AppUtils.EmptyState.LOADING -> {
                    emptyState.isVisible = true
                    emptyState.initViewAsLoading("We are building the buildings as fast as we can.")
                }
                AppUtils.EmptyState.NO_NETWORK -> {
                    emptyState.isVisible = true
                    emptyState.initView("There is no Internet connection",
                            "Oops.. We don't have dinosaur game here to play.\nPlease check your internet connection.",
                            R.drawable.dinasaur,
                            "Retry",
                            object : EmptyStateView.EmptyStateActionListener {
                                override fun onActionClick() {
                                    mViewModel.getFeedItems()
                                }
                            })
                }
            }
        })

        mViewModel.getFeedItems()
    }

    private fun initRecyclerView() {

        val mFeedItemActionCallBack = object : FeedItemsActionListener {
            override fun onTextReadMoreClick(adapterPosition: Int, item: FeedItem) {
                openImageScreen(item, adapterPosition)
            }

            override fun onHeaderClick(adapterPosition: Int, item: FeedItem) {
                //open person profile
            }

            override fun onImageItemClick(adapterPosition: Int, item: FeedItem, itemPosition: Int) {
                openImageScreen(item, itemPosition)
            }
        }

        mAdapter = FeedListAdapter(context!!, mFeedItems, mFeedItemActionCallBack)

        rcvFeedList.apply {
            adapter = mAdapter
            addItemDecoration(SpaceItemDecoration(35))
        }


    }

    private fun openImageScreen(item: FeedItem, itemPosition: Int) {
        FeedDetailActivity.startActivity(activity!!, item, itemPosition)
    }

}
