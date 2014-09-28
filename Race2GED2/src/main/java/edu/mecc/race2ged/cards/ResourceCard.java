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
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;


/**
 * TODO: document your custom view class.
 */
public class ResourceCard extends StubCard {

    private String mTitle = null;
    private String mDescription = null;
    private Uri mUri = null;

    public ResourceCard(Context context) {
        super(context);
    }

    public ResourceCard(Context context, String resourceTitle,String resourceDescription, Uri resourceLink) {
        super(context, R.layout.resource_card);
        mTitle = resourceTitle;
        mDescription = resourceDescription;
        mUri = resourceLink;
        initCard();
    }

    public ResourceCard(Context context, String resourceTitle, String resourceDescription, String resourceLink) {
        super(context, R.layout.resource_card);
        mTitle = resourceTitle;
        mDescription = resourceDescription;
        mUri = Uri.parse(resourceLink);
        initCard();
    }

    protected void initCard(){
        if (mStub != null) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent;
                    intent = new Intent(Intent.ACTION_VIEW).setData(mUri);
                    getContext().startActivity(intent);
                }
            });
            //Setup text data
            TextView mTitleView = (TextView) getStubContents().findViewById(R.id.title);
            mTitleView.setText(mTitle);
            TextView mDescriptionView = (TextView) getStubContents().findViewById(R.id.description);
            mDescriptionView.setText(mDescription);
            Utils.setRobotoBold(getContext(),mTitleView);
            Utils.setRobotoThin(getContext(),mDescriptionView);
        }

    }

    public Uri getmUri() {
        return mUri;
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
