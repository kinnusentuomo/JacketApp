package com.tuomomees.jacket_soundboard_app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Luokan on luonut tuomo päivämäärällä 10.10.2017.
 */

class CustomAboutDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    private Activity c;

    CustomAboutDialogClass(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_about_dialog);
        Button feedback = findViewById(R.id.btn_feedback);
        Button close = findViewById(R.id.btn_close);
        feedback.setOnClickListener(this);
        close.setOnClickListener(this);

        try
        {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setWindowAnimations(R.style.dialog_animation_fade);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_feedback:
                Toast.makeText(c.getApplicationContext(),c.getResources().getString(R.string.support_text), Toast.LENGTH_LONG).show();

                String subject = null;
                try {
                    subject = c.getResources().getString(R.string.app_name) + c.getPackageManager()
                            .getPackageInfo(c.getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String emailAddress = c.getResources().getString(R.string.feedback_address);
                String templateBody = c.getResources().getString(R.string.feedback_template);
                String whichEmailToUse =  c.getResources().getString(R.string.choose_feedback_delivering);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",emailAddress, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, templateBody);
                c.startActivity(Intent.createChooser(emailIntent, whichEmailToUse));

                break;
            case R.id.btn_close:
                //Toast.makeText(c.getApplicationContext(), c.getResources().getString(R.string.notsupport_text), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        dismiss();
    }
}
