package com.tuomomees.jacket_soundboard_app;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Luokan on luonut tuomo päivämäärällä 12.11.2017.
 */

public class CustomAlertDialog extends Dialog  {

    private Activity c;
    public Dialog d;
    private TextView alertDialogLabel;

    CustomAlertDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        alertDialogLabel = findViewById(R.id.alert_dialog_label);
    }

    void setAlertDialogLabel(String labelText)
    {
        if(alertDialogLabel != null)
        {
            alertDialogLabel.setText(labelText);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert_dialog);



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
}
