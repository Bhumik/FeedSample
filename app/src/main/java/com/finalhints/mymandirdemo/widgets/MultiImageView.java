package com.finalhints.mymandirdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.finalhints.mymandirdemo.R;
import com.finalhints.mymandirdemo.datamodel.Attachment;

import java.util.ArrayList;


public class MultiImageView extends RelativeLayout {

    FeedMediaView mIvFeedImage1;
    FeedMediaView mIvFeedImage2;
    FeedMediaView mIvFeedImage3;
    FeedMediaView mIvFeedImage4;
    private ArrayList<Attachment> mAttachmentList;

    private int mViewType = 0;
    private OnImageClickListener mOnImageClickListener;
    View.OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOnImageClickListener != null) {
                mOnImageClickListener.onImageClicked((Integer) view.getTag(), view, mViewType);
            }
        }
    };

    public MultiImageView(Context context) {
        super(context);
        initView();
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttrs(context, attrs);
        initView();
    }

    public MultiImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttrs(context, attrs);
        initView();
    }


    public MultiImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadAttrs(context, attrs);
        initView();
    }

    /**
     * Load Default Properties values if set from xml file.
     *
     * @param context context
     * @param attrs   AttributeSet
     */
    private void loadAttrs(Context context, AttributeSet attrs) {
/*
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.layout_multi_image_view, 0, 0);

        mSTRTitle = a.getString(R.styleable.EmptyStateView_esvTitle);
        mSTRMessage = a.getString(R.styleable.EmptyStateView_esvMessage);
        imageRes = a.getResourceId(R.styleable.EmptyStateView_esvImage, android.R.drawable.ic_delete);
        a.recycle();
*/
    }

    private void initView() {
        mAttachmentList = new ArrayList<>();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_multi_image_view, this, true);
        mIvFeedImage1 = (FeedMediaView) view.findViewById(R.id.ivFeedImage1);
        mIvFeedImage2 = (FeedMediaView) view.findViewById(R.id.ivFeedImage2);
        mIvFeedImage3 = (FeedMediaView) view.findViewById(R.id.ivFeedImage3);
        mIvFeedImage4 = (FeedMediaView) view.findViewById(R.id.ivFeedImage4);

        mIvFeedImage1.setTag(1);
        mIvFeedImage2.setTag(2);
        mIvFeedImage3.setTag(3);
        mIvFeedImage4.setTag(4);

        mIvFeedImage1.setOnClickListener(mClickListener);
        mIvFeedImage2.setOnClickListener(mClickListener);
        mIvFeedImage3.setOnClickListener(mClickListener);
        mIvFeedImage4.setOnClickListener(mClickListener);
    }

    public void initData(ArrayList<Attachment> attachmentsList) {
        mAttachmentList.clear();
        if (attachmentsList != null) {
            mAttachmentList.addAll(attachmentsList);
        }

        mIvFeedImage1.setVisibility(View.GONE);
        mIvFeedImage2.setVisibility(View.GONE);
        mIvFeedImage3.setVisibility(View.GONE);
        mIvFeedImage4.setVisibility(View.GONE);

        mViewType = mAttachmentList.size();

        switch (mAttachmentList.size()) {
            case 4:
                mIvFeedImage4.setVisibility(View.VISIBLE);
            case 3:
                mIvFeedImage3.setVisibility(View.VISIBLE);
            case 2:
                mIvFeedImage2.setVisibility(View.VISIBLE);
            case 1:
                mIvFeedImage1.setVisibility(View.VISIBLE);
                break;
            case 0:
                this.setVisibility(View.GONE);
        }

        loadImages();
    }

    private void loadImages() {
        switch (mViewType) {
            case VIEW_TYPE.FOURWAY:
                mIvFeedImage4.initData(mAttachmentList.get(3), false, true);
            case VIEW_TYPE.THREEWAY:
                mIvFeedImage3.initData(mAttachmentList.get(2), false, true);
            case VIEW_TYPE.TWOWAY:
                mIvFeedImage2.initData(mAttachmentList.get(1), false, true);
            case VIEW_TYPE.ONEWAY:
                mIvFeedImage1.initData(mAttachmentList.get(0), false, (mViewType != VIEW_TYPE.ONEWAY));
                break;
            case 0:
                this.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setOnItemClickListener(OnImageClickListener onImageClickListener) {
        mOnImageClickListener = onImageClickListener;
    }

    interface VIEW_TYPE {
        int ONEWAY = 1;
        int TWOWAY = 2;
        int THREEWAY = 3;
        int FOURWAY = 4;
    }

    public interface OnImageClickListener {
        void onImageClicked(int position, View view, int viewType);
    }
}

