package vivek.client.youtube.com.youtubeclient.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import vivek.client.youtube.com.youtubeclient.BuildConfig;
import vivek.client.youtube.com.youtubeclient.R;

/**
 * Created by vivekneel on 1/1/17.
 */

public class AboutDeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Your description here")
                .setImage(R.drawable.about_header)
                .addItem(new Element().setTitle(String.valueOf("Version : " +BuildConfig.VERSION_CODE)))
                .addGroup("Connect with me")
                .addEmail("letsgoshiva@gmail.com")
                .addWebsite("www.facebook.com/letsgoshiva")
                .addFacebook("letsgoshiva")
                .addTwitter("medyo80")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addInstagram("medyo80")
                .create();

        setContentView(aboutPage);
    }
}
