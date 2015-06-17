package com.nirigo.mobile.view.passcode;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nirigo.mobile.view.passcode.adapters.PasscodeAdapter;
import com.nirigo.mobile.view.passcode.adapters.PasscodeBaseAdapter;
import com.nirigo.mobile.view.passcode.models.PasscodeItem;

import java.util.ArrayList;

/**
 * Created by Sicz-Mesziár János on 2015.06.14..
 *
 * The PasscodeView is a customizable password typer widget.
 */
public class PasscodeView extends ViewGroup {

    // Populate variable
    private ArrayList<Row> rows;
    private int countColumn, countRow;

    // Items
    private PasscodeBaseAdapter adapter;

    // Default is wrap_content
    private boolean widthStretchMode = false,
            heightStretchMode = false;
    private int widthStretchTargetSize,
            heightStretchTargetSize;


    private OnItemClickListener onItemClickListener;

    // Constructor & Inits -------------------------------------------------------------------------
    public PasscodeView(Context context) {
        super(context);
        init();
    }

    public PasscodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasscodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasscodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.rows = new ArrayList<Row>();
        setAdapter(new PasscodeAdapter(getContext()));
    }

    private void initViews() {
        if (adapter != null) {
            int count = adapter.getCount();
            for (int i = 0; i < count; i++) {

                // Child view
                View currentChild = getChildCount() > i ? getChildAt(i) : null;
                if (currentChild != null) removeViewInLayout(currentChild);
                View child = adapter.getView(i, currentChild, this);

                final int index = i;
                child.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if(onItemClickListener != null)
                            onItemClickListener.onItemClick(PasscodeView.this, index, v, adapter.getItem(index));
                    }
                });

                // Child layout params
                LayoutParams layoutParams = generateDefaultLayoutParams();
                if (child.getLayoutParams() != null && child.getLayoutParams() instanceof LayoutParams)
                    layoutParams = (LayoutParams) child.getLayoutParams();

                // Add child
                addViewInLayout(child, i, layoutParams, true);

            }
            requestLayout();
        }
        this.countColumn = 3;
        this.countRow = (int) Math.floor(getChildCount() / countColumn);
    }

    private DataSetObserver observer = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            initViews();
        }
    };


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 1. Measure children
        this.widthStretchMode = checkWidthStretchMode();
        this.heightStretchMode = checkHeightStretchMode();
        this.widthStretchTargetSize = calculateWidthStretchTargetSize();
        this.heightStretchTargetSize = calculateHeightStretchTargetSize();

        final int size = getChildCount();
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        for (int i = 0; i < size; ++i) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                //noinspection ResourceType
                childWidthMeasureSpec = widthStretchMode ?
                        MeasureSpec.makeMeasureSpec(widthStretchTargetSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY) :
                        MeasureSpec.makeMeasureSpec(lp.width == -1 ?  -2 : lp.width, childMeasureMode(lp.width, widthStretchMode));
                //noinspection ResourceType
                childHeightMeasureSpec = heightStretchMode ?
                        MeasureSpec.makeMeasureSpec(heightStretchTargetSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY) :
                        MeasureSpec.makeMeasureSpec(lp.height == -1 ? -2 : lp.height, childMeasureMode(lp.height, heightStretchMode));
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }



        // 2. Populate
        populate();


        // 3. Calculate ViewGroup width and height
        int newWidth = 0;
        int newHeight = 0;
        for (Row row : rows) {
            newWidth = Math.max(row.getRowWidth(), newWidth);
            newHeight += row.getRowHeight();
        }

        // 4. Set new dimensions
        super.onMeasure(
                !widthStretchMode ? MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY) : widthMeasureSpec,
                !heightStretchMode ? MeasureSpec.makeMeasureSpec(newHeight, MeasureSpec.EXACTLY) : heightMeasureSpec
        );

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (changed) {

            //@formatter:off
            int rowHeight = 0,
                childLeft = getPaddingLeft(),
                childTop = getPaddingTop(),
                childRight = 0,
                childBottom = 0;
            //@formatter:on


            // Layout each row
            int childWidth, childHeight;
            for (Row row : rows) {
                if (row.getChildCount() > 0) {  // has child

                    rowHeight = row.getRowHeight();
                    childLeft = getPaddingLeft();

                    for (View view : row.getRowChildren()) {

                        LayoutParams params = (LayoutParams) view.getLayoutParams();

                        childWidth = widthStretchMode ? widthStretchTargetSize - params.leftMargin - params.rightMargin : view.getMeasuredWidth();
                        childHeight = heightStretchMode ? heightStretchTargetSize - params.topMargin - params.bottomMargin : view.getMeasuredHeight();

                        childLeft += params.leftMargin;
                        childTop += params.topMargin;
                        childRight = childLeft + childWidth;
                        childBottom = childTop + childHeight;

                        view.layout(childLeft, childTop, childRight, childBottom);

                        childLeft = childRight + params.rightMargin;
                        childTop -= params.topMargin; // because rowHeight contains
                    }
                    childTop += !heightStretchMode ? rowHeight : heightStretchTargetSize;
                }
            }

        } // changed

    }


    private boolean checkWidthStretchMode() {
        return getLayoutParams() != null && getLayoutParams().width != LayoutParams.WRAP_CONTENT && countColumn > 0;
    }

    private boolean checkHeightStretchMode() {
        return getLayoutParams() != null && getLayoutParams().height != LayoutParams.WRAP_CONTENT && countRow > 0;
    }

    private int calculateWidthStretchTargetSize() {
        return widthStretchMode ? getMeasuredWidth() / countColumn : LayoutParams.WRAP_CONTENT;
    }

    private int calculateHeightStretchTargetSize() {
        return heightStretchMode ? getMeasuredHeight() / countRow : LayoutParams.WRAP_CONTENT;
    }

    private int childMeasureMode(int lpParams, boolean stretchMode){
        if(lpParams == LayoutParams.MATCH_PARENT) lpParams = LayoutParams.WRAP_CONTENT;
        // return lpParams == LayoutParams.WRAP_CONTENT && !stretchMode ? MeasureSpec.AT_MOST : MeasureSpec.EXACTLY;
        return lpParams == LayoutParams.WRAP_CONTENT && !stretchMode ?
                Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN ? MeasureSpec.EXACTLY : MeasureSpec.AT_MOST :
                MeasureSpec.EXACTLY;
    }

    private void populate() {

        rows.clear();
        Row currentRow = new Row();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {

                if (i % countColumn == 0) { // move into new row
                    currentRow = new Row();
                    rows.add(currentRow);
                    currentRow.addChild(child);
                } else {
                    currentRow.addChild(child);
                }

            }
        }

        countRow = rows.size();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setAdapter(PasscodeBaseAdapter adapter) {
        this.adapter = adapter;
        this.adapter.registerDataSetObserver(observer);
        this.adapter.notifyDataSetChanged();
    }

    public PasscodeBaseAdapter getAdapter() {
        return adapter;
    }

    public interface OnItemClickListener{
        void onItemClick(PasscodeView view, int position, View item, Object o);
    }

    // ViewGroup implements ------------------------------------------------
    @Override
    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }


    // Sub classes ---------------------------------------------------------
    private final static class Row {
        private ArrayList<View> rowChildren;
        private int rowHeight;
        private int rowWidth;

        public Row() {
            this.rowChildren = new ArrayList<View>();
        }

        public void addChild(View view) {
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            this.rowHeight = Math.max(rowHeight, params.topMargin + view.getMeasuredHeight() + params.bottomMargin);
            this.rowWidth += params.leftMargin + view.getMeasuredWidth() + params.rightMargin;
            this.rowChildren.add(view);
        }

        public ArrayList<View> getRowChildren() {
            return rowChildren;
        }

        public int getChildCount() {
            return rowChildren.size();
        }

        public int getRowHeight() {
            return rowHeight;
        }

        public int getRowWidth() {
            return rowWidth;
        }
    }


    public final static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams params) {
            super(params);
        }

        public LayoutParams() {
            super(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }

    }


}
