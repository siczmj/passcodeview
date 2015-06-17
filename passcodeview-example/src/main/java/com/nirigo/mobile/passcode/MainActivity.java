package com.nirigo.mobile.passcode;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.nirigo.mobile.passcode.examples.ExampleAndroidFragment;
import com.nirigo.mobile.passcode.examples.ExampleCustomizedFragment;
import com.nirigo.mobile.passcode.examples.ExampleIOSFragment;
import com.nirigo.mobile.passcode.examples.ExamplePlainFragment;
import com.nirigo.mobile.passcode.other.ScreenUtils;
import com.nirigo.mobile.passcode.other.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<Class<? extends Fragment>> fragmentclasses = new ArrayList<Class<? extends Fragment>>();
        fragmentclasses.add(ExamplePlainFragment.class);
        fragmentclasses.add(ExampleCustomizedFragment.class);
        fragmentclasses.add(ExampleIOSFragment.class);
        fragmentclasses.add(ExampleAndroidFragment.class);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentclasses);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );
            // ViewGroup parent = (ViewGroup) viewPager.getParent();
            findViewById(android.R.id.content).setPadding(
                    0, ScreenUtils.getStatusBarHeight(this) + ScreenUtils.getActionBarHeight(this),
                    0, 0
            );
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.prev) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            return true;
        } else if (item.getItemId() == R.id.next) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
