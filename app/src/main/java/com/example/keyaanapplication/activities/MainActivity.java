package com.example.keyaanapplication.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.keyaanapplication.R;
import com.example.keyaanapplication.fragments.BookedVenues_Fragment;
import com.example.keyaanapplication.fragments.Catering_Category_Fragment;
import com.example.keyaanapplication.fragments.Catering_Packages_Fragment;
import com.example.keyaanapplication.fragments.Cateringsub_Catergory_Fragment;
import com.example.keyaanapplication.fragments.Orders_Fragment;
import com.example.keyaanapplication.fragments.Products_Fragment;
import com.example.keyaanapplication.fragments.Services_Fragment;
import com.example.keyaanapplication.fragments.Vendor_Details_Fragment;
import com.example.keyaanapplication.fragments.Venues_Fragment;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private CircleImageView imgNavHeaderBg;
    CircleImageView imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;

    public static int navItemIndex = 0;

    private static final String TAG_VENDOR_DETAILS = "details";

    private static final String TAG_VENDOR_SERVICES = "services";
    private static final String TAG_VENDOR_ORDERS = "orders";
    private static final String TAG_VENDOR_VENUES = "venues";
    private static final String TAG_BOOKED_VENUES = "booked_venues";
    private static final String TAG_CATERING_CATEGORIES = "catering_categories";
    private static final String TAG_CATERING_SUB_CATEGORY = "catering_sub_category";
    private static final String TAG_VENDOR_PRODUCTS = "products";
    private static final String TAG_CATERING_PACKAGES = "packages";

    public static String CURRENT_TAG = TAG_VENDOR_SERVICES;

    private String[] activityTitles;

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        navHeader = navigationView.getHeaderView(0);


        imgNavHeaderBg = (CircleImageView) navHeader.findViewById(R.id.img_header_bg);

        navigationView.setBackgroundColor(getResources().getColor(R.color.Color_White));

        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_VENDOR_DETAILS;
            loadHomeFragment();
        }
    }

    @Override
    public void onBackPressed() {

        doExit();
    }

    private void doExit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setTitle("AppTitle");

        AlertDialog dialog = alertDialog.create();
        dialog.show();

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));

        negativeButton.setTextColor(Color.parseColor("#FFFF0400"));

    }

    private void loadHomeFragment() {
        selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        drawer.closeDrawers();

        invalidateOptionsMenu();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }


    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private Fragment getHomeFragment() {

        switch (navItemIndex) {
            case 0:
                Vendor_Details_Fragment vendor_details_fragment = new Vendor_Details_Fragment();
                return vendor_details_fragment;
            case 1:
                Services_Fragment services_fragment = new Services_Fragment();
                return services_fragment;
            case 2:
                Orders_Fragment orders_fragment = new Orders_Fragment();
                return orders_fragment;
            case 3:
                Venues_Fragment venues_fragment = new Venues_Fragment();
                return venues_fragment;
            case 4:
                BookedVenues_Fragment bookedVenues_fragment = new BookedVenues_Fragment();
                return bookedVenues_fragment;

            case 5:
                Catering_Category_Fragment catering_category_fragment = new Catering_Category_Fragment();
                return catering_category_fragment;
            case 6:
                Cateringsub_Catergory_Fragment cateringsub_catergory_fragment = new Cateringsub_Catergory_Fragment();
                return cateringsub_catergory_fragment;
            case 7:
                Products_Fragment products_fragment = new Products_Fragment();
                return products_fragment;
            case 8:
                Catering_Packages_Fragment catering_packages_fragment = new Catering_Packages_Fragment();
                return catering_packages_fragment;

            default:
                return new Vendor_Details_Fragment();
        }
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.vendor_details:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_VENDOR_DETAILS;
                        break;
                    case R.id.vendor_services:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_VENDOR_SERVICES;
                        break;
                    case R.id.vendor_orders:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_VENDOR_ORDERS;
                        break;
                    case R.id.vendor_venues:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_VENDOR_VENUES;
                        break;
                    case R.id.vendor_booked_venues:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_BOOKED_VENUES;
                        break;
                    case R.id.vendor_catering_category:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_CATERING_CATEGORIES;
                        break;
                    case R.id.vendor_catering_sub_category:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_CATERING_SUB_CATEGORY;
                        break;

                    case R.id.vendor_products:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_VENDOR_PRODUCTS;
                        break;

                    case R.id.vendor_catering_packages:
                        navItemIndex = 8;
                        CURRENT_TAG = TAG_CATERING_PACKAGES;
                        break;

                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }


}
