/*
 * Copyright 2014 Regional Adult Education Program of Lee, Scott, Wise, and Norton Public Schools
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package edu.mecc.race2ged.cards;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;
import edu.mecc.race2ged.widgets.Card;

/**
 * TODO: document your custom view class.
 */
public class ExpandableCard extends Card {

    protected LinearLayout mLayout;
    protected LinearLayout mContent;
    protected TextView mTitleView;
    protected boolean mExpanded = false;

    protected OnClickListener expandClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mExpanded){
                onCollapse();
            } else {
                onExpand();
            }
        }
    };

    public ExpandableCard(Context context){
        super(context);
        setupExpandableCard("");
    }

    public ExpandableCard(Context context, String title){
        super(context);
        setupExpandableCard(title);
    }

    public ExpandableCard(Context context, String title, View view) {
        super(context);
        setupExpandableCard(title);
        addViewInExpandableArea(view);
    }

    public ExpandableCard(Context context,String title, ArrayList<View> views) {
        super(context);
        setupExpandableCard(title);
        if (views.isEmpty()) return;
        for (View view : views) {
            mLayout.addView(view);
        }
    }

    public ExpandableCard(Context context, String title, String message){
        super(context);
        setupExpandableCard(title);
        TextView textView = new TextView(context);
        if (Utils.isStringEmpty(message)) message = "TODO";
        textView.setText(message);
        addViewInExpandableArea(textView);
    }

    private void setupExpandableCard(String title){
        mContent = new LinearLayout(getContext());
        mContent.setOrientation(LinearLayout.VERTICAL);
        setupTitle(title);
        addContentView(mContent);
        LinearLayout sep = new LinearLayout(getContext());
        sep.setBackgroundColor(getResources().getColor(R.color.card_divider));
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
        params.setMargins(0,getResources().getDimensionPixelSize(R.dimen.card_inside_side_padding),0,getResources().getDimensionPixelSize(R.dimen.card_inside_side_padding));
        sep.setLayoutParams(params);
        addViewInExpandableArea(sep);
        if (!mExpanded) onCollapse();
    }

    protected void setupTitle(String title){
        mTitleView = new TextView(getContext());
        mTitleView.setText(title);
        mTitleView.setCompoundDrawablePadding(0);
        if (mExpanded) {

            mTitleView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(R.drawable.ic_collapse), null);
        } else {
            mTitleView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(R.drawable.ic_expand), null);
        }
        MarginLayoutParams params = new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mTitleView.getCompoundDrawables()[2].getIntrinsicHeight());
        params.setMargins(0,0,0,getResources().getDimensionPixelSize(R.dimen.card_expandable_title_bottom_margin));
        mTitleView.setLayoutParams(params);
        mTitleView.setOnClickListener(expandClick);
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.card_expandable_title_text_size));
        Utils.setRobotoThin(getContext(), mTitleView, Typeface.BOLD);
        mTitleView.setTypeface(null, Typeface.BOLD);
        addContentView(mTitleView);
    }

    protected void addViewInExpandableArea(View view){
        if (mContent != null) mContent.addView(view);
    }

    protected void onExpand() {
        if (mContent != null){
            mExpanded = true;
            mTitleView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(R.drawable.ic_collapse), null);
            expand(mContent);
        }
    }

    protected void onCollapse() {
        if (mContent != null){
            mExpanded = false;
            mTitleView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(R.drawable.ic_expand), null);
            collapse(mContent);
        }
    }

    public static void expand(final View v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

}
