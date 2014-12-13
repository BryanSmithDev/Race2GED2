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

package edu.mecc.race2ged.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.io.Serializable;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;

/**
 * TODO: document your custom view class.
 */
public abstract class Card extends LinearLayout implements Serializable {

    protected LinearLayout mLayout;

    public Card(Context context){
        super(context);
        setupCard();
        Utils.setRobotoThin(context, mLayout);
    }

    private void setupCard(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout)inflater.inflate(R.layout.card, this, true).findViewById(R.id.card);
        int pad = getResources().getDimensionPixelSize(R.dimen.card_inside_side_padding);
        setCardPadding(pad,pad,pad,pad);
        setCardMargin(0,0,0,getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin));
    }

    protected void addContentView(View view){
        mLayout.addView(view);
    }

    protected void setCardPadding(int left, int top, int right, int bottom){
        mLayout.setPadding(left, top, right, bottom);
    }

    protected void setCardMargin(int left, int top, int right, int bottom){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left, top, right, bottom);
        mLayout.setLayoutParams(layoutParams);
    }

}
