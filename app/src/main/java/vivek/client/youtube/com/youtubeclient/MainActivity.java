package vivek.client.youtube.com.youtubeclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import vivek.client.youtube.com.youtubeclient.activities.AboutDeveloperActivity;
import vivek.client.youtube.com.youtubeclient.adapters.CustomActivityViewPagerAdapter;
import vivek.client.youtube.com.youtubeclient.fragments.ChannelListFragment;

public class MainActivity extends AppCompatActivity implements ChannelListFragment.IChannelSubscription {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.activity_view_pager)
    ViewPager activityViewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    private CustomActivityViewPagerAdapter customActivityViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.youtube.com/channel/UCfsQ8iYqwHri0O3irtoAfCw"));
                startActivity(intent);

            }
        });
        setSupportActionBar(toolbar);
        setupTabs();

    }

    private void setupTabs() {
        customActivityViewPagerAdapter = new CustomActivityViewPagerAdapter(getSupportFragmentManager());
        activityViewPager.setAdapter(customActivityViewPagerAdapter);
        tabs.setupWithViewPager(activityViewPager);

    }

    @Override
    public void sendSubscriptionInstance() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent = new Intent(MainActivity.this, AboutDeveloperActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
