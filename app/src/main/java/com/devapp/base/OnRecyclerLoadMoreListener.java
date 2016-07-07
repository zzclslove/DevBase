package com.devapp.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView加载更多的类,支持LinearLayoutManager和GridLayoutManager
 * 使用:
 * RecyclerView.addOnScrollListener(new OnRecyclerLoadMoreListener(){
 *  public void onLoadMore(){...}
 *  });
 */
public abstract class OnRecyclerLoadMoreListener extends RecyclerView.OnScrollListener{

    private LinearLayoutManager mLayoutManager;
    private int mItemCount, mLastCompletely, mLastLoad;

    /**
     * load more
     */
    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            mItemCount = mLayoutManager.getItemCount();
            mLastCompletely = mLayoutManager.findLastCompletelyVisibleItemPosition();
        } else {
            return;
        }

        if (mLastLoad != mItemCount && mItemCount == mLastCompletely + 1) {
            mLastLoad = mItemCount;
            onLoadMore();
        }
    }
}
