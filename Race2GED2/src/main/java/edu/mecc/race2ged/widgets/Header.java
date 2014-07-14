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
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;

/**
 * TODO: document your custom view class.
 */
public class Header extends TextView implements Serializable {

    public Header(Context context){
        super(context);
        setupHeader(context);
    }

    public Header(Context context, String header) {
        super(context);
        setupHeader(context);
        setText(header.toUpperCase());
    }

    private void setupHeader(Context context){
        Utils.setRobotoBold(context, this);
        setTextColor(getResources().getColor(R.color.content_color));
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.headerTextSize));
        setShadowLayer(1.0f,0.0f,getResources().getDimensionPixelSize(R.dimen.header_shadow_size),getResources().getColor(R.color.content_color_shadow));
        setFocusable(false);
        setClickable(false);
        setFocusableInTouchMode(false);
        setPadding(0,0,0,getResources().getDimensionPixelSize(R.dimen.header_bottom_padding));
    }

}
