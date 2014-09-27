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
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import edu.mecc.race2ged.widgets.Card;

/**
 * TODO: document your custom view class.
 */
public abstract class StubCard extends Card {

    protected View mStubContents;
    protected ViewStub mStub;


    public StubCard(Context context) {
        super(context);
        mStub = new ViewStub(context);
        addContentView(mStub);
    }

    public StubCard(Context context,@LayoutRes int layoutId){
        super(context);
        mStub = new ViewStub(context);
        mStub.setLayoutResource(layoutId);
        addContentView(mStub);
        inflate();
    }

    public void setContents(int id) {
        mStub.setLayoutResource(id);
    }

    public View inflate(){
        mStubContents = null;
        try {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mStubContents = mStub.inflate();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(),"Error inflating StubCard's stub. - "+e.getMessage());
        }
        return mStubContents;
    }

    public ViewStub getStub() {
        return mStub;
    }

    public void setStub(ViewStub mStub) {
        this.mStub = mStub;
    }

    public View getStubContents() {
        return mStubContents;
    }
}
