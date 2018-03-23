package com.tuomomees.jacket_soundboard_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Luokan on luonut tuomo päivämäärällä 11.11.2017.
 */
//Luokka, jonka tarkoituksena on käsitellä tarvittavat tiedostojen jaot
class ResourceShareHandler {

    private Context context;

    ResourceShareHandler(Context c)
    {
        this.context = c;
    }

    //Metodi, jolla voi jakaa halutun RAW resurssin
    void shareResource(int resource)
    {
        try {
            //Copy file to external ExternalStorage.
            //String mediaPath = copyFiletoExternalStorage(R.raw.bain_careful_now, "bain_careful_now.ogg");
            String filename = context.getResources().getResourceEntryName(resource) + ".mp3";
            filename = filename.replace("az", "ä"); //muotoillaan tiedoston nimi uudelleen -> ääkköset
            filename = filename.replace("oz", "ö"); // -''-
            String mediaPath = copyFiletoExternalStorage(resource, filename );

            Log.d("mediapath", mediaPath);
            Intent shareMedia = new Intent(Intent.ACTION_SEND);
            //set WhatsApp application.
            //shareMedia.setPackage("com.whatsapp");
            shareMedia.setType("audio/mp3");

            File audioFileToShare = new File(mediaPath + filename);
            Uri uri = Uri.fromFile(audioFileToShare);

            Log.d("File", String.valueOf(audioFileToShare));
            Log.d("Uri", String.valueOf(uri));

            //set path of media file in ExternalStorage.
            //shareMedia.putExtra(Intent.EXTRA_STREAM, Uri.parse(mediaPath));
            shareMedia.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(Intent.createChooser(shareMedia, context.getResources().getString(R.string.share_audio_text)));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.share_audio_error_text), Toast.LENGTH_LONG).show();
        }
    }

    private String copyFiletoExternalStorage(int resourceId, String resourceName){


        //String pathSDCard = Environment.getExternalStorageDirectory().getPath() + "/media/audio/ringtones/" +  resourceName;
        String pathSDCard = Environment.getExternalStorageDirectory().getPath() + "/media/audio/ringtones/";

        boolean exists = (new File(pathSDCard)).exists();
        if (!exists){new File(pathSDCard).mkdirs();}


        try{
            InputStream in = context.getResources().openRawResource(resourceId);
            FileOutputStream out = new FileOutputStream(pathSDCard + resourceName);

            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  pathSDCard;
    }
}
