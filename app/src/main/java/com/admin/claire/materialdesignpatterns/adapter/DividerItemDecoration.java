package com.admin.claire.materialdesignpatterns.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 為RecyclerView 加上項目分隔線
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration{

    private Drawable mDivider; //分配者
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    //使用系統主題中的R.attr.listDivider作為Item間的分割線
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public DividerItemDecoration (Context context, int orientation){
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    //設置螢幕方向
    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST){
            drawVertical(c, parent, state);
        } else {
            drawHorizontal(c, parent, state);
        }
    }

    //畫垂直直線 |||
    private void drawVertical(Canvas c, RecyclerView parent,RecyclerView.State state) {
        //分隔線的左邊 = paddingLeft值
        final int left = parent.getPaddingLeft();
        //分隔線的右邊 = RecyclerView 寬度 － paddingRight值
        final int right = parent.getWidth() - parent.getPaddingRight();

        final  int childCount = parent.getChildCount(); //分隔線數量=item數量
        for (int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);

            //獲得child的佈局
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            //分隔線的上邊 = item的底部 + item根標籤的bottomMargin值
            final int top = child.getBottom() + params.bottomMargin;

            //分隔線的下邊 = 分隔線的上邊 + 分隔線本身高度
            final int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }
    }

    //畫水平線 三
    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //由於Divider也有長寬高，每一個Item需要向下或者向右偏移
    //獲取Item偏移量，此方法是為每個Item四周預留出空間，從而讓分隔線的繪製在預留的空間內
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST){
            //垂直方向的分隔線：item向下偏移一個分隔線的高度
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        } else {
            //水平方向的分隔線：item向右偏移一個分隔線的寬度
            outRect.set(0,0, mDivider.getIntrinsicWidth(),0);
        }
    }
}
