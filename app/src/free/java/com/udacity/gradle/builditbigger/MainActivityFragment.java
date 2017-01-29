package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private InterstitialAd interstitialAd;

    private String TAG = this.getClass().getSimpleName();

    private ProgressBar loadingBar = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        loadingBar = (ProgressBar) root.findViewById(R.id.joke_loading_spinner);
        loadingBar.setVisibility(View.GONE);

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.interstitial_test_ad_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.i(TAG,"onAdFailedToLoad");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.i(TAG,"onAdClosed");
                loadingBar.setVisibility(View.VISIBLE);
                new EndPointJokerAsyncTask().execute(getActivity());
                //loadingBar.setVisibility(View.GONE);
                newInterstitial();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.i(TAG,"onAdLoaded");
            }
        });

        newInterstitial();

        Button button = (Button) root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                else {
                    loadingBar.setVisibility(View.VISIBLE);
                    newInterstitial();
                }
            }
        });
        return root;
    }

    private void newInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
        loadingBar.setVisibility(View.GONE);
    }
}
