package com.tuomomees.jacket_soundboard_app;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by tuomo on 3.10.2017.
 */

class SoundPlayerThread extends Thread {

    private boolean running = true;
    private MediaPlayer mp;
    private MediaPlayerThreadInterface observer;
    private int sentButtonId;
    private boolean playingAudio;
    private int listId;

    SoundPlayerThread(Context c, int resId, MediaPlayerThreadInterface o, int id, int id2)
    {
        mp = MediaPlayer.create(c, resId);
        observer = o;
        sentButtonId = id;
        listId = id2;
    }

    int getSentId()
    {
        return sentButtonId;
    }

    int getListId()
    {
        return listId;
    }

    void setRunning(boolean r)
    {
        running = r;
    }

    boolean isPlayingAudio()
    {
        return playingAudio;
    }

    public void run() {
        mp.start();
        playingAudio = true;
        try {
            while (running)
            {
                //observer.setButtonPlaying(true, sentButtonId);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        Log.d("Sound played", "stopping thread");
                        observer.setButtonPlaying(false, sentButtonId);
                        mp.release();
                        playingAudio = false;
                        running = false;
                    }
                });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Rajapinta joka tarjoaa pääsyn metodiin, joka asettaa id:llä määritetyn painikkeen toistotilaan (vaihtaa play kuvakkeen)
    interface MediaPlayerThreadInterface
    {
        void setButtonPlaying(boolean isButtonPlaying, int btnId);
    }
}
