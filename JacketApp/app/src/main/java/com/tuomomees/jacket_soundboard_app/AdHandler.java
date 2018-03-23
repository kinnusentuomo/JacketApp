package com.tuomomees.jacket_soundboard_app;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;



import com.google.android.gms.ads.MobileAds;

/**
 * Luokan on luonut tuomo päivämäärällä 11.11.2017.
 */

class AdHandler {
    private InterstitialAd mInterstitialAd;

    AdHandler(Context context)
    {
        mInterstitialAd = new InterstitialAd(context);
        //Admobin antama ID asetetaan tähän
        MobileAds.initialize(context, "ca-app-pub-4117339910887954~3142938207");

        // set the ad unit ID
        //Admobin antama mainosyksikön tunnus
        mInterstitialAd.setAdUnitId(context.getString(R.string.FullScreenAd));
    }

    //Metodi, joka asettaa mainosyksikön näkyviin
    void showAd()
    {
        AdRequest adRequest = new AdRequest.Builder()
                //Testivaiheessa käytettävä tunnus
                //.addTestDevice("4143E9C56383B280C13BEAB61D27819D")
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);
        //Kun mainos on ladattu, se näytetään
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }
}
