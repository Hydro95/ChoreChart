package net.sudormrf.chorechart;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private enum Page {
        Tasks(0),
        ShoppingList(1),
        Users(2);

        private int value;
        Page(int input) { value = input; }
        int getValue() { return value; }
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // this was commented out by me
        //setSupportActionBar(toolbar); // so was this
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Facade.getInstance().createListeners(); //for the database

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_add, menu);
        //getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.out.println(id);

        /*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.out.println("hi");
            return true;
        }
        */
        //else if(id == R.id.action_add) {
        if(id == R.id.action_add) {
            TabLayout t = (TabLayout) findViewById(R.id.tabs);
            int y = t.getSelectedTabPosition();

            if(y == Page.Tasks.getValue()) {
                Intent intent = new Intent(this, EditTaskActivity.class);
                startActivity(intent);
            }
            else if(y == Page.Users.getValue()) {
                Intent intent = new Intent(this, UserModifierActivity.class);
                startActivity(intent);
            }
            else if(y == Page.ShoppingList.getValue()) {
                Intent intent = new Intent(this, AddList.class);
                startActivity(intent);
            }
        }
        else if(id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0)
                return new TasksFragment();
          
            else if (position == 1)
                return new ShoppingListFragment();

            else if (position == 2)
                return new PeopleFragment();
          
            else
                return new TasksFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
