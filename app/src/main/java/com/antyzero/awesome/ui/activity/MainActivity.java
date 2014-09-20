package com.antyzero.awesome.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
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
    private ViewPager mViewPager;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

        mSectionsPagerAdapter = new SectionsPagerAdapter( getFragmentManager() );

        mViewPager = (ViewPager) findViewById( R.id.pager );
        mViewPager.setAdapter( mSectionsPagerAdapter );

        mViewPager.setOnPageChangeListener( new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected( int position ) {
                actionBar.setSelectedNavigationItem( position );
            }
        } );

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
        mViewPager.setCurrentItem( tab.getPosition() );
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
     *
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

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
     * Holds tab order, class, title
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
        Tabs( Class<? extends BaseFragment> fragmentClass, int resourceTitle ) {
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
