package com.antyzero.awesome.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.antyzero.awesome.R;
import com.antyzero.awesome.ui.fragment.BaseFragment;
import com.antyzero.awesome.ui.fragment.JsonFragment;
import com.antyzero.awesome.ui.fragment.LogFileFragment;
import com.antyzero.awesome.ui.fragment.RssFragment;

/**
 * Main application activity
 */
public class MainActivity extends Activity implements ActionBar.TabListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager viewPager;
    private ActionBar actionBar;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        actionBar = getActionBar();

        if( actionBar == null ) {
            throw new IllegalStateException( "Theme used for activity does not contain ActionBar" );
        }

        actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

        mSectionsPagerAdapter = new SectionsPagerAdapter( getFragmentManager() );

        viewPager = (ViewPager) findViewById( R.id.pager );
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new OnPageChangeListener());

        for( int i = 0; i < mSectionsPagerAdapter.getCount(); i++ ) {

            actionBar.addTab( actionBar.newTab()
                    .setText( mSectionsPagerAdapter.getPageTitle( i ) )
                    .setTabListener( this ) );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTabSelected( ActionBar.Tab tab, FragmentTransaction fragmentTransaction ) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTabUnselected( ActionBar.Tab tab, FragmentTransaction fragmentTransaction ) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTabReselected( ActionBar.Tab tab, FragmentTransaction fragmentTransaction ) {
        // do nothing
    }

    /**
     * Listen to page change state
     */
    private final class OnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected( int position ) {
            actionBar.setSelectedNavigationItem( position );
        }
    }

    /**
     * Tabs adapter
     */
    private final class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter( FragmentManager fragmentManager ) {
            super( fragmentManager );
        }

        @Override
        public Fragment getItem( int position ) {
            return Tabs.values()[position].newInstance();
        }

        @Override
        public int getCount() {
            return Tabs.values().length;
        }

        @Override
        public CharSequence getPageTitle( int position ) {
            return getString( Tabs.values()[position].getResourceTitle() );
        }
    }

    /**
     * Holds tab order, fragment class, title
     */
    public enum Tabs {

        LOG( LogFileFragment.class, R.string.main_navigation_tab_log ),

        RSS( RssFragment.class, R.string.main_navigation_tab_rss ),

        JSON( JsonFragment.class, R.string.main_navigation_tab_json );


        private final Class<? extends BaseFragment> fragmentClass;

        private final int resourceTitle;

        /**
         * Default constructor
         *
         * @param fragmentClass for tab fragment creation
         * @param resourceTitle fot tab title
         */
        Tabs( @NonNull Class<? extends BaseFragment> fragmentClass, @StringRes int resourceTitle ) {

            this.fragmentClass = fragmentClass;
            this.resourceTitle = resourceTitle;
        }

        /**
         * Creates fragment instance
         *
         * @return Fragment object
         */
        public Fragment newInstance() {

            try {
                return fragmentClass.newInstance();
            } catch( InstantiationException | IllegalAccessException e ) {
                throw new RuntimeException( "Fragment creation for tab navigation failed", e );
            }
        }

        /**
         * Get title resource
         *
         * @return id
         */
        public int getResourceTitle() {
            return resourceTitle;
        }
    }
}
