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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import java.io.Serializable;

import edu.mecc.race2ged.R;

/**
 * TODO: document your custom view class.
 */
public class Card extends LinearLayout implements Serializable {

    protected Context mContext;
    protected View mCardLayout;
    protected View mCardContents;
    protected ViewStub mStub;


    public Card(Context context) {
        super(context);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCardLayout = inflater.inflate(R.layout.card, this, true);
        mStub = (ViewStub)findLayoutViewById(R.id.cardStub);
    }

    public void setContents(int id) {
        mStub.setLayoutResource(id);
    }

    public View inflate(){
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCardContents = mStub.inflate();
        return mCardContents;
    }

    protected View findLayoutViewById(int id) {
        return mCardLayout.findViewById(id);
    }

    public ViewStub getStub() {
        return mStub;
    }

    public void setStub(ViewStub mStub) {
        this.mStub = mStub;
    }

    public View getCardContents() {
        return mCardContents;
    }

    public void setCardContents(View mCardContents) {
        this.mCardContents = mCardContents;
    }

    public View getCardLayout() {
        return mCardLayout;
    }

    public void setCardLayout(View mCardLayout) {
        this.mCardLayout = mCardLayout;
    }
}
