package com.finalhints.mymandirdemo.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.finalhints.mymandirdemo.datamodel.Attachment
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.view.adapter.FeedDetailAdapter
import com.finalhints.mymandirdemo.view.ui.activity.ExoVideoActivity
import com.finalhints.mymandirdemo.view.ui.base.BaseFragment
import com.finalhints.mymandirdemo.view.viewmodel.FeedDetailViewModel
import com.finalhints.mymandirdemo.widgets.HeaderUserView
import com.finalhints.mymandirdemo.widgets.SpaceItemDecoration
import kotlinx.android.synthetic.main.feed_detail_fragment0.*

private const val EXTRA_DATA_ITEM = "EXTRA_DATA_ITEM"

class FeedDetailFragment : BaseFragment() {

    companion object {
        fun newInstance(feeditem: FeedItem): FeedDetailFragment {
            val sArgs = Bundle()
            val feeditemFragment = FeedDetailFragment()
            sArgs.putParcelable(EXTRA_DATA_ITEM, feeditem)
            feeditemFragment.arguments = sArgs
            return feeditemFragment
        }

        fun newInstance(): FeedDetailFragment {
            val feeditemFragment = FeedDetailFragment()
            val sArgs = Bundle()
            feeditemFragment.arguments = sArgs
            return feeditemFragment
        }
    }

    private val mFeedAttachmentItems: ArrayList<Attachment> = ArrayList()
    /**
     * adapter for feed list items
     */
    private var mAdapter: FeedDetailAdapter? = null
    /**
     * get instance of view model having live data of feed item list
     */
    private val mViewModel: FeedDetailViewModel by lazy {
        ViewModelProvider(activity!!, ViewModelProvider.NewInstanceFactory()).get(FeedDetailViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.feed_detail_fragment0, container, false)
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
        mViewModel.feedItem.observe(this, Observer {
            //set title of main feed
            tvTitle.text = it.title

            //set text of feed
            tvDescription.text = it.text

            val tagslist = it.tags?.map { it -> "#${it.text}" }?.joinToString("  ") ?: ""
            tvTagList.isVisible = !tagslist.isEmpty()
            tagDivider.isVisible = !tagslist.isEmpty()
            tvTagList.text = tagslist


            clHeaderView.initData(it.sender, it.createdAt, true)

            it.attachments?.let { attachmentList ->
                mFeedAttachmentItems.clear()
                mFeedAttachmentItems.addAll(attachmentList)
                mAdapter?.notifyDataSetChanged()
            }
        })


        //set back press on header back button click
        clHeaderView.onHeaderActionListener = object : HeaderUserView.OnHeaderActionListener {
            override fun onHeaderBarClick() {
                //open user profile
            }

            //go back to main listing when user pressed back button
            override fun onBackArrowClick() {
                activity?.onBackPressed()
            }
        }
    }

    private fun initRecyclerView() {
        mAdapter = FeedDetailAdapter(context!!, mFeedAttachmentItems)
        rcvFeedDetailList.apply {
            adapter = mAdapter
            addItemDecoration(SpaceItemDecoration(20, true, true))
        }
        mAdapter?.mCallBack = object : FeedDetailAdapter.FeedDetailItemActionListener {
            override fun onVideoPlayClick(adapterPosition: Int, item: Attachment) {
                showVideoScreen(item, adapterPosition)
            }
        }
    }

    /**
     * show speficied video attachment in exo player screen with playing functionality
     */
    private fun showVideoScreen(item: Attachment, position: Int) {
        ExoVideoActivity.startActivity(context!!, item, position)
    }

}
