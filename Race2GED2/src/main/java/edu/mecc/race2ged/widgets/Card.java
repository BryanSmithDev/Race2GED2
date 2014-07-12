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
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;

/**
 * TODO: document your custom view class.
 */
public class Card extends LinearLayout implements Serializable {

    protected LinearLayout mLayout;

    public Card(Context context){
        super(context);
        setupCard(context);
        Utils.setRobotoThin(context, mLayout);
    }

    public Card(Context context, View view) {
        super(context);
        setupCard(context);
        mLayout.addView(view);
        Utils.setRobotoThin(context, mLayout);
    }

    public Card(Context context, ArrayList<View> views) {
        super(context);
        setupCard(context);
        if (views.isEmpty()) return;
        for(int i=0;i<views.size();i++) {
            mLayout.addView(views.get(i));
        }
        Utils.setRobotoThin(context, mLayout);
    }

    public Card(Context context, String message){
        super(context);
        setupCard(context);
        TextView textView = new TextView(context);
        if (Utils.isStringEmpty(message)) message = "TODO";
        textView.setText(message);
        mLayout.addView(textView);
        Utils.setRobotoThin(context, mLayout);
    }

    private void setupCard(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout)inflater.inflate(R.layout.card, this, true).findViewById(R.id.card);
        int pad = getResources().getDimensionPixelSize(R.dimen.card_inside_side_padding);
        setCardPadding(pad,pad,pad,pad);
    }

    protected void addContentView(View view){
        mLayout.addView(view);
    }

    protected void setCardPadding(int left, int top, int right, int bottom){
        mLayout.setPadding(left, top, right, bottom);
    }

}
