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
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.widget.TextView;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;

/**
 * Text card is simply a card that has a textview for simple info cards.
 * @author Bryan Smith
 * @date 7/1/2014.
 */
public class TextCard extends StubCard {

    private String mText = null;

    public TextCard(Context context){
        super(context);
    }

    public TextCard(Context context, String textData) {
        super(context, R.layout.text_card);
        this.mText = textData;
        initCard();
    }

    public TextCard(Context context,@StringRes int textResourceID) {
        super(context, R.layout.text_card);
        try {
            this.mText = context.getResources().getString(textResourceID);
        } catch (Resources.NotFoundException e) {
            this.mText = getResources().getString(R.string.not_found_exception);
        }
        initCard();
    }

    protected void initCard(){
        if (mStub != null) {
            //Setup text data
            TextView text = (TextView) getStubContents().findViewById(R.id.text);
            text.setText(mText);
            Utils.setRobotoThin(getContext(),text);
        }

    }

}
