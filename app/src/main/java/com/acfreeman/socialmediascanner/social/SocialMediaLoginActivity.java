package com.acfreeman.socialmediascanner.social;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.acfreeman.socialmediascanner.CustomDialogFragment;
import com.acfreeman.socialmediascanner.MainActivity;
import com.acfreeman.socialmediascanner.R;
import com.acfreeman.socialmediascanner.db.LocalDatabase;
import com.acfreeman.socialmediascanner.db.Owner;
import com.acfreeman.socialmediascanner.social.login.FacebookFragment;
import com.acfreeman.socialmediascanner.social.login.LinkedInFragment;
import com.acfreeman.socialmediascanner.social.login.SpotifyFragment;
import com.acfreeman.socialmediascanner.social.login.TwitterFragment;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.List;

public class SocialMediaLoginActivity extends AppCompatActivity implements CustomDialogFragment.NoticeDialogListener {



    public LocalDatabase database;
    public List<Owner> owners;
    public Owner owner;

    private static Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new LocalDatabase(getApplicationContext());
        owners = database.getAllOwner();
        owner = owners.get(0);

        final Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.twitter_blue));

        }


        setContentView(R.layout.activity_social_media_login_container);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TwitterFragment twitterFragment = new TwitterFragment();

        FragmentManager fm = getSupportFragmentManager();

        fm.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
                        Log.i("BACKSTACK","Backstack entry count: " + getSupportFragmentManager().getBackStackEntryCount());
                        for (Fragment fragment : allFragments) {
                            if (fragment instanceof TwitterFragment) {
                                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
                                Drawable d = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_forward_white_24dp);
                                Drawable d2 = d.getConstantState().newDrawable();
                                d2.mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.twitter_blue), PorterDuff.Mode.MULTIPLY);
                                fab.setImageDrawable(d2);
                            } else if (fragment instanceof LinkedInFragment) {
                                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
                                Drawable d = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_forward_white_24dp);
                                Drawable d2 = d.getConstantState().newDrawable();
                                d2.mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.linkedin_blue), PorterDuff.Mode.MULTIPLY);
                                fab.setImageDrawable(d2);
                            } else if (fragment instanceof SpotifyFragment) {
                                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
                                Drawable d = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_forward_white_24dp);
                                Drawable d2 = d.getConstantState().newDrawable();
                                d2.mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.spotify_green), PorterDuff.Mode.MULTIPLY);
                                fab.setImageDrawable(d2);
                            } else if (fragment instanceof FacebookFragment) {
                                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
                                Drawable d = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_forward_white_24dp);
                                Drawable d2 = d.getConstantState().newDrawable();
                                d2.mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.com_facebook_blue), PorterDuff.Mode.MULTIPLY);
                                fab.setImageDrawable(d2);
                            }
                        }
                    }
                });


        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        ft.addToBackStack("twitter");
        ft.replace(R.id.content, twitterFragment);
        ft.commit();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        Drawable d = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_forward_white_24dp);
        Drawable d2 = d.getConstantState().newDrawable();
        d2.mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.twitter_blue), PorterDuff.Mode.MULTIPLY);
        fab.setImageDrawable(d2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Fragment> allFragments = getSupportFragmentManager().getFragments();
                for (Fragment fragment : allFragments) {
                    if (fragment instanceof TwitterFragment) {
                        LinkedInFragment linkedinFragment = new LinkedInFragment();

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                        ft.addToBackStack("linkedin");
                        ft.replace(R.id.content, linkedinFragment);
                        ft.commit();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.linkedin_blue));
                        }
                    } else if (fragment instanceof LinkedInFragment) {
                        SpotifyFragment spotifyFragment = new SpotifyFragment();

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                        ft.addToBackStack("spotify");
                        ft.replace(R.id.content, spotifyFragment);
                        ft.commit();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.spotify_green));
                        }
                    } else if (fragment instanceof SpotifyFragment) {
                        FacebookFragment facebookFragment = new FacebookFragment();

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                        ft.addToBackStack("facebook");
                        ft.replace(R.id.content, facebookFragment);
                        ft.commit();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.com_facebook_blue));
                        }
                    } else {
                        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(startIntent);
                    }
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<android.support.v4.app.Fragment> allFragments = getSupportFragmentManager().getFragments();
        for (android.support.v4.app.Fragment fragment : allFragments) {
            if (fragment instanceof TwitterFragment) {
                ((TwitterFragment) fragment).onActivityResult(requestCode, resultCode, data);
            } else if (fragment instanceof LinkedInFragment) {
                ((LinkedInFragment) fragment).onActivityResult(requestCode, resultCode, data);
            } else if (fragment instanceof SpotifyFragment) {
                ((SpotifyFragment) fragment).onActivityResult(requestCode, resultCode, data);
            } else if (fragment instanceof FacebookFragment) {
                ((FacebookFragment) fragment).onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void showNoticeDialog(String social_title, String uri) {
        DialogFragment dialog = new CustomDialogFragment();


        Bundle args = new Bundle();
        args.putString("dialog_title", "You must install the " + social_title + " app in order to login");
        args.putString("uri", uri);
        args.putString("action", "appInstall");


        dialog.setArguments(args);
        dialog.show(getFragmentManager(), "CustomDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //Add user intent
        //Go to next social media dialog

        Bundle mArgs = dialog.getArguments();
        String uri = mArgs.getString("uri");

        Intent startIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(startIntent);


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Go to next social media dialog
//        Bundle mArgs = dialog.getArguments();
//        String name = mArgs.getString("name");

    }

    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }


}



