package com.tuomomees.jacket_soundboard_app;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Luokan on luonut tuomo päivämäärällä 12.11.2017.
 */

class NotificationAndRingtoneHandler {

    private Context context;
    private RingtoneManager ringtoneManager;
    private Activity activity;

    NotificationAndRingtoneHandler(Context context, Activity activity)
    {
        this.activity = activity;
        this.context = context;
        ringtoneManager = new RingtoneManager(context);
    }

    void saveas(int ressound){
        byte[] buffer=null;
        InputStream fIn = context.getResources().openRawResource(ressound);
        int size=0;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return;
        }

        //String path="/sdcard/media/audio/ringtones/";
        String path = Environment.getExternalStorageDirectory().getPath() + "/media/audio/ringtones/";
        String filename=context.getResources().getResourceEntryName(ressound)+".ogg";

        boolean exists = (new File(path)).exists();
        if (!exists){new File(path).mkdirs();}

        FileOutputStream save;
        try {
            save = new FileOutputStream(path+filename);
            save.write(buffer);
            save.flush();
            save.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return;
        }

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+path+filename)));

        File k = new File(path, filename);

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, "tuomomees_app_lab");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/ogg");
        values.put(MediaStore.Audio.Media.ARTIST, "BainApp ");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        //Insert it into the database
        context.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);
    }

    void startRingtonePicker(int ringtoneId)
    {        final String filename=context.getResources().getResourceEntryName(ringtoneId)+".ogg";



        final CustomAlertDialog customAlertDialog = new CustomAlertDialog(activity);
        customAlertDialog.show();


        Button buttonPositive = customAlertDialog.findViewById(R.id.btn_positive);
        Button buttonNegative = customAlertDialog.findViewById(R.id.btn_negative);

        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clicked", "Positive");
                String path = Environment.getExternalStorageDirectory().getPath() + "/media/audio/ringtones/";
                //String filename=context.getResources().getResourceEntryName(notificationId)+".ogg";
                ringtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE, Uri.parse("file://"+path+filename));
                customAlertDialog.dismiss();
            }
        });

        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clicked", "Negative");
                customAlertDialog.dismiss();
            }
        });




    /*
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(context.getResources().getString(R.string.are_you_sure));
        builder.setMessage(filename + " " + context.getResources().getString(R.string.is_going_to_be_your_ringtone));
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String path = Environment.getExternalStorageDirectory().getPath();
                        path = path + "/media/audio/ringtones/";
                        //String filename=context.getResources().getResourceEntryName(notificationId)+".ogg";
                        ringtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE, Uri.parse("file://"+path+filename));
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        */
    }

    void startNotificationPicker(final int notificationId)
    {
        final String filename=context.getResources().getResourceEntryName(notificationId)+".ogg";

        final CustomAlertDialog customAlertDialog = new CustomAlertDialog(activity);
        customAlertDialog.show();


        Button buttonPositive = customAlertDialog.findViewById(R.id.btn_positive);
        Button buttonNegative = customAlertDialog.findViewById(R.id.btn_negative);

        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clicked", "Positive");
                String path = Environment.getExternalStorageDirectory().getPath();
                path = path + "/media/audio/ringtones/";
                //String filename=context.getResources().getResourceEntryName(notificationId)+".ogg";
                ringtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION, Uri.parse("file://"+path+filename));
                customAlertDialog.dismiss();
            }
        });

        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clicked", "Negative");
                customAlertDialog.dismiss();
            }
        });


    }
}
