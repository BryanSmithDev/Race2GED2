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

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import edu.mecc.race2ged.GEDApplication;
import edu.mecc.race2ged.JSON.Class;
import edu.mecc.race2ged.JSON.Instructor;
import edu.mecc.race2ged.R;
import edu.mecc.race2ged.helpers.Utils;

/**
 * @author Bryan Smith
 * @date 7/1/2014.
 */
public class ClassCard extends StubCard {

    private Class mClass = null;

    public ClassCard(Context context, Class mClass) {
        super(context,R.layout.class_card);
        this.mClass = mClass;
        initCard();
    }

    protected void initCard(){
        setCardPadding(0,0,0,0);
        if (mStub != null) {
            //Setup location info
            TextView location = (TextView) getStubContents().findViewById(R.id.location);
            location.setText(mClass.getLocation());

            //Setup address info
            TextView address = (TextView) getStubContents().findViewById(R.id.address);
            address.setText(mClass.getAddress());

            //Setup time info
            TextView times = (TextView) getStubContents().findViewById(R.id.times);
            String display = "";
            try {
                ArrayList<String> days = (ArrayList<String>) mClass.getDays();
                ArrayList<String> time = (ArrayList<String>) mClass.getTimes();
                final int count = days.size();
                for (int i = 0; i < count; i++) {
                    if (i != 0) display = display + "\n";
                    display = display + days.get(i) + ":   " + time.get(i);
                }
            } catch (Exception e) {
                Log.e(this.getClass().getSimpleName(), "Error displaying time for class schedule. - " + e.getMessage());
            }
            times.setText(display);

            //Setup instructors info
            TextView instructors = (TextView) getStubContents().findViewById(R.id.instructors);
            display = "";
            try {
                ArrayList<Instructor> ins = (ArrayList<Instructor>) mClass.getInstructors();
                final int count = ins.size();
                for (int i = 0; i < count; i++) {
                    if (i != 0) display = display + "\n";
                    display = display + ins.get(i).getName();
                }
            } catch (Exception e) {
                Log.e(this.getClass().getSimpleName(), "Error displaying instructors for class schedule. - " + e.getMessage());
            }
            instructors.setText(display);
            setupButtons(getStubContents(), mClass);
        }

    }

    /**
     * Initial setup of the Maps,Email, and call buttons of the card.
     *
     * @param container The View to look for the buttons in.
     * @param mClass    The Class data object to retrieve data from.
     */
    private void setupButtons(View container, Class mClass) {
        if (container != null) {
            try {
                Button mapsButton = (Button) container.findViewById(R.id.maps);
                Button emailButton = (Button) container.findViewById(R.id.email);
                Button callButton = (Button) container.findViewById(R.id.call);
                FrameLayout mapsSep = (FrameLayout) container.findViewById(R.id.mapsSep);
                FrameLayout emailSep = (FrameLayout) container.findViewById(R.id.emailSep);
                FrameLayout buttonSep = (FrameLayout) container.findViewById(R.id.buttonSep);
                Boolean maps = true, email = true, call = true;


                if (Utils.isStringEmpty(mClass.getUrl())) {
                    mapsButton.setVisibility(View.GONE);
                    mapsSep.setVisibility(View.GONE);
                    maps = false;
                } else {
                    setupMapsButton(mapsButton, mClass.getUrl());
                }

                if (!areThereEmails(mClass)) {
                    emailButton.setVisibility(View.GONE);
                    emailSep.setVisibility(View.GONE);
                    email = false;
                } else {
                    setupEmailButton(emailButton, mClass);
                }

                if (Utils.isStringEmpty(mClass.getContact()) ||
                        ((TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
                                == TelephonyManager.PHONE_TYPE_NONE) {
                    callButton.setVisibility(View.GONE);
                    call = false;
                } else {
                    setupCallButton(callButton,mClass);
                }

                if (!maps && !email && !call) buttonSep.setVisibility(View.GONE);

            } catch (NullPointerException ex) {
                Log.e(this.getClass().getSimpleName(), "Error finding card buttons. - " + ex.getMessage());
            }
        }
    }

    /**
     * Attaches the click behaviour of the Maps button.
     *
     * @param button The button to add the behavior to.
     * @param url    The url to load into Google Maps (or a web browser).
     */
    private void setupMapsButton(Button button, String url) {
        if (button != null) {
            final Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (GEDApplication.getStoredContext() != null)
                        GEDApplication.getStoredContext().startActivity(mIntent);
                }
            });
        }
    }

    /**
     * Attaches the click behaviour of the Email button.
     *
     * @param button The button to add the behavior to.
     * @param mClass The Class data object to retrieve data from.
     */
    private void setupEmailButton(Button button, final Class mClass) {
        if (button != null && mClass.getInstructors().size() > 0 && GEDApplication.getStoredContext() != null) {

            ListView instructors = new ListView(getContext());
            ArrayAdapter<String> instructorsNames = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
            for (int i = 0; i < mClass.getInstructors().size(); i++) {
                if (!Utils.isStringEmpty(mClass.getInstructors().get(i).getEmail()))
                    instructorsNames.add(mClass.getInstructors().get(i).getName());
            }
            instructors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    sendEmail(GEDApplication.getStoredContext(),
                            mClass.getInstructors().get(position).getEmail(),
                            getContext().getResources().getString(R.string.instructor_email_subject),
                            "");
                }
            });
            instructors.setAdapter(instructorsNames);

            final Dialog emailDialog = new Dialog(GEDApplication.getStoredContext());
            emailDialog.setContentView(instructors);
            emailDialog.setTitle(R.string.instructor_to_email);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailDialog.show();
                }
            });
        }
    }

    /**
     * Setup the call buttons behaviour on click.
     * @param button The button to add the behavior to.
     * @param mClass The Class data object to retrieve data from.
     */
    private void setupCallButton(Button button, Class mClass) {
        try {
            final Intent mIntent = new Intent(Intent.ACTION_DIAL);
            mIntent.setData(Uri.parse("tel:"+mClass.getContact()));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GEDApplication.getStoredContext().startActivity(mIntent);
                }
            });

        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(),"Error dialing number. - "+e.getMessage());
        }
    }

    /**
     * Send an email with supplied address, subject, and content.
     * @param emailAddress To: Email Address
     * @param emailSubject Email Subject
     * @param emailBody Email Body Content
     */
    private static void sendEmail(Context context,String emailAddress, String emailSubject, String emailBody) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/html");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {emailAddress});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
        context.startActivity(Intent.createChooser(emailIntent, "Select your Email App:"));
    }

    /**
     * Determine if at least one of the instructors have an email.
     * @param mClass The class to check instructor emails.
     * @return True if there is at least one non-empty email address. False otherwise.
     */
    private boolean areThereEmails(Class mClass){
        if (mClass != null) {
            if (mClass.getInstructors().size() > 0) {
                for (int i = 0; i < mClass.getInstructors().size(); i++) {
                    if (!Utils.isStringEmpty(mClass.getInstructors().get(i).getEmail())) return true;
                }
            }
        }
        return false;
    }
}
