package com.project.whj.androidlib.view.statuspagelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.project.whj.androidlib.R;

import java.util.ArrayList;

/**
 * 多状态切换视图
 */

@SuppressWarnings("unused")
public class StatusPageLayout extends RelativeLayout {

    private static final RelativeLayout.LayoutParams DEFAULT_LAYOUT_PARAMS =
            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);

    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;
    private View mNoNetworkView;
    private View mContentView;
    private int  mEmptyViewResId;
    private int  mErrorViewResId;
    private int  mLoadingViewResId;
    private int  mNoNetworkViewResId;
    private int  mContentViewResId;
    private static final int NULL_RESOURCE_ID = -1;
    private LayoutInflater mInflater;

    public static final int STATUS_CONTENT    = 0x00;
    public static final int STATUS_LOADING    = 0x01;
    public static final int STATUS_EMPTY      = 0x02;
    public static final int STATUS_ERROR      = 0x03;
    public static final int STATUS_NO_NETWORK = 0x04;
    private int mViewStatus;

    private final ArrayList<Integer> mOtherIds = new ArrayList<>();
    private OnClickListener mOnRetryClickListener;

    public StatusPageLayout(Context context) {
        this(context,null);
    }

    public StatusPageLayout(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public StatusPageLayout(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        final TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.StatusPageLayout,defStyleAttr,0);
        mEmptyViewResId= typedArray.getResourceId(R.styleable.StatusPageLayout_emptyView,R.layout.layout_empty_view);
        mErrorViewResId=typedArray.getResourceId(R.styleable.StatusPageLayout_errorView,R.layout.layout_error_view);
        mLoadingViewResId=typedArray.getResourceId(R.styleable.StatusPageLayout_loadingView,R.layout.layout_loading_view);
        mNoNetworkViewResId=typedArray.getResourceId(R.styleable.StatusPageLayout_noNetworkView,R.layout.layout_empty_view);
        mContentViewResId=typedArray.getResourceId(R.styleable.StatusPageLayout_contentView,NULL_RESOURCE_ID);
        typedArray.recycle();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showContent();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear(mEmptyView,mLoadingView,mErrorView,mNoNetworkView);
        if (null!=mOtherIds){
            mOtherIds.clear();
        }
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null;
        }
        mInflater=null;
    }

    /**
     * 获取当前状态
     * @return
     */
    public int getViewStatus(){
        return mViewStatus;
    }

    /**
     * 设置重试点击事件
     * @param onRetryClickListener
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener){
        this.mOnRetryClickListener = onRetryClickListener;
    }

    /**
     * 显示空视图
     */
    public final void showEmpty() {
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示空视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    public final void showEmpty(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showEmpty(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示空视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    public final void showEmpty(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "Empty view is null!");
        mViewStatus = STATUS_EMPTY;
        if (null == mEmptyView) {
            mEmptyView = view;
            View emptyRetryView = mEmptyView.findViewById(R.id.empty_retry_view);
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mEmptyView.getId());
            addView(mEmptyView, 0, layoutParams);
        }
        showViewById(mEmptyView.getId());
    }

    /**
     * 显示错误视图
     */
    public final void showError() {
        showError(mErrorViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示错误视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    public final void showError(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showError(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示错误视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    public final void showError(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "Error view is null!");
        mViewStatus = STATUS_ERROR;
        if (null == mErrorView) {
            mErrorView = view;
            View errorRetryView = mErrorView.findViewById(R.id.error_retry_view);
            if (null != mOnRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mErrorView.getId());
            addView(mErrorView, 0, layoutParams);
        }
        showViewById(mErrorView.getId());
    }

    /**
     * 显示加载中视图
     */
    public final void showLoading() {
        showLoading(mLoadingViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示加载中视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    public final void showLoading(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showLoading(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示加载中视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    public final void showLoading(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "Loading view is null!");
        mViewStatus = STATUS_LOADING;
        if (null == mLoadingView) {
            mLoadingView = view;
            mOtherIds.add(mLoadingView.getId());
            addView(mLoadingView, 0, layoutParams);
        }
        showViewById(mLoadingView.getId());
    }

    /**
     * 显示无网络视图
     */
    public final void showNoNetwork() {
        showNoNetwork(mNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示无网络视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    public final void showNoNetwork(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showNoNetwork(inflateView(layoutId), layoutParams);
    }

    /**
     * 显示无网络视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    public final void showNoNetwork(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "No network view is null!");
        mViewStatus = STATUS_NO_NETWORK;
        if (null == mNoNetworkView) {
            mNoNetworkView = view;
            View noNetworkRetryView = mNoNetworkView.findViewById(R.id.no_network_retry_view);
            if (null != mOnRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mNoNetworkView.getId());
            addView(mNoNetworkView, 0, layoutParams);
        }
        showViewById(mNoNetworkView.getId());
    }

    //-------------------------------------------------------

    /**
     * 显示内容视图
     */
    public final void showContent(){
        mViewStatus = STATUS_CONTENT;
        if (null==mContentView && mContentViewResId!=NULL_RESOURCE_ID){
            mContentView=mInflater.inflate(mContentViewResId,null);
            addView(mContentView,0,DEFAULT_LAYOUT_PARAMS);
        }
        showContentView();
    }

    private void showContentView(){
        final int childCount=getChildCount();
        for (int i=0;i<childCount;i++){
            View view=getChildAt(i);
            view.setVisibility(mOtherIds.contains(view.getId())?View.GONE:VISIBLE);
        }
    }

    private View inflateView(int layoutId){
        return mInflater.inflate(layoutId,null);
    }

    private void showViewById(int viewId){
        final int childCount=getChildCount();
        for (int i=0;i<childCount;i++){
            View view=getChildAt(i);
            view.setVisibility(view.getId()==viewId ? View.VISIBLE :GONE);
        }
    }

    private void checkNull(Object object,String hint){
        if (null==object){
            throw new NullPointerException(hint);
        }
    }

    private void clear(View... views){
        if (null==views){
            return;
        }
        try {
            for (View view:views){
                if (null!=view){
                    removeView(view);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
