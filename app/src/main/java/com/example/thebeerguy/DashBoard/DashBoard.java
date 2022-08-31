package com.example.thebeerguy.DashBoard;

import com.example.Profile.EditProfile;
import com.example.Signup.SignUp;
import com.example.login.Login;
import com.example.thebeerguy.DashBoard.Home.Home;
import com.example.thebeerguy.DashBoard.Home.SubCategory.SubCategory;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavContactUs;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavDeliveryRate;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavFranchiseSponsorship;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavHelp;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavOrderAlcohol;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavPartyDrinkCalculator;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavSocialResponsibility;
import com.example.thebeerguy.DashBoard.NavigationDrawerItems.NavTestimonials;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebeerguy.R;
import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    NavigationView navigationView;
    private Menu menu1;
    private Fragment fragment = null;
    private ListView nav_list;
    private ImageView imageViewnav;
    private TextView nav_version;
    private TextView navUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        Button cart_icon = toolbar.findViewById(R.id.cart_icon);
        cart_icon.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

            if (Islogin) {
                startActivity(new Intent(DashBoard.this, ReviewCart.class));


            } else {
                Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Login.class));
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(DashBoard.this);

        menu1 = navigationView.getMenu();

//        disableNavigationViewScrollbars(navigationView);
//
        navigationView.setVerticalScrollBarEnabled(false);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hammenu);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + "name" + "</font>"));

        getSupportActionBar().setElevation(10f);
        getSupportActionBar().setIcon(R.drawable.logo2);
//        getSupportActionBar().set

//        nav_list = (ListView) findViewById(R.id.lv_main_nav);

        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.nav_header_name);
        imageViewnav = headerView.findViewById(R.id.nav_header_imageView);
        nav_version = headerView.findViewById(R.id.nav_version);

//        mapGridData();


        setFragment(new Home());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {

            switch (item.getItemId()) {
                case R.id.theBeerGuy_home: {
                    setFragment(new Home());
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.theBeerGuy_fav: {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

                    if (Islogin) {
                        setFragment(new Favourites());

                    } else {
                        Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Login.class));
                    }
//                    Toast.makeText(this, "Favourites", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.theBeerGuy_recent: {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

                    if (Islogin) {
                        setFragment(new Recent());

                    } else {
                        Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Login.class));
                    }
//                    Toast.makeText(this, "Recent", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.theBeerGuy_order: {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

                    if (Islogin) {
                        setFragment(new Orders());

                    } else {
                        Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Login.class));
                    }

//                    Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.theBeerGuy_account: {


                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

                    if (Islogin) {

                        setFragment(new Account());
                        Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Login.class));
                    }

                    break;
                }


                default:
                    Toast.makeText(this, "wrong choice", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

    }

    void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_dash, fragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        // Set action bar title

        setTitle(menuItem.getTitle());


        int id = menuItem.getItemId();

        if (id == R.id.nav_item_home) {
            startActivity(new Intent(getApplicationContext(), DashBoard.class));
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_item_beer) {

            Intent intent = new Intent(this, SubCategory.class);
            intent.putExtra("subCatId", "3968");
            intent.putExtra("typeID", "1");
            startActivity(intent);

            Toast.makeText(this, "Beer", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_item_liquor) {

            Intent intent = new Intent(this, SubCategory.class);
            intent.putExtra("subCatId", "1432");
            intent.putExtra("typeID", "3");
            startActivity(intent);
            Toast.makeText(this, "Liquor", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_item_wine) {

            Intent intent = new Intent(this, SubCategory.class);
            intent.putExtra("subCatId", "24235");
            intent.putExtra("typeID", "2");
            startActivity(intent);
            Toast.makeText(this, "Wine", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_item_recent) {

            setFragment(new Recent());
            Toast.makeText(getApplicationContext(), "Recent Viewed", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_create_account) {


            startActivity(new Intent(this, SignUp.class));
            Toast.makeText(this, "Create Account", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_signin) {

            startActivity(new Intent(this, Login.class));
            Toast.makeText(this, "Sign In", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_help) {

            startActivity(new Intent(getApplicationContext(), NavHelp.class));
            Toast.makeText(getApplicationContext(), "Help and FAQ", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_contactus) {

            startActivity(new Intent(getApplicationContext(), NavContactUs.class));
            Toast.makeText(getApplicationContext(), "Contact Us", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_delivery_rate) {

            startActivity(new Intent(getApplicationContext(), NavDeliveryRate.class));
            Toast.makeText(getApplicationContext(), "Delivery Rate", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_social_resposibility) {

            startActivity(new Intent(getApplicationContext(), NavSocialResponsibility.class));
            Toast.makeText(getApplicationContext(), "Social Responsibility", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_testimonials) {

            startActivity(new Intent(getApplicationContext(), NavTestimonials.class));
            Toast.makeText(getApplicationContext(), "Testimonials", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_party_drink_calculator) {

            startActivity(new Intent(getApplicationContext(), NavPartyDrinkCalculator.class));
            Toast.makeText(getApplicationContext(), "Party Drink Calculator", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.order_alcohol) {

            startActivity(new Intent(getApplicationContext(), NavOrderAlcohol.class));
            Toast.makeText(getApplicationContext(), "How to Order Alcohol", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.franchise_sponsorship) {

            startActivity(new Intent(getApplicationContext(), NavFranchiseSponsorship.class));
            Toast.makeText(getApplicationContext(), "Franchise and Sponsorship", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();

        }

        // Close the navigation drawer

        drawer.closeDrawers();

        return false;
    }

   /* private void mapGridData() {
        NavBarAdapter navBarAdapter = new NavBarAdapter(DashBoard.this, Constant.nav_menu_txt, Constant.nav_menu_icon);
        nav_list.setAdapter(navBarAdapter);
        nav_list.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {

                case 0: {

//                    Intent intent = new Intent(this, Login.class);
//                    startActivity(intent);
//                    finish();
//                    finishAffinity();
//                    startActivity(new Intent(this, Login.class));
//                        Toast.makeText(getApplicationContext(), R.string.dash_home, Toast.LENGTH_SHORT).show();
                    break;
                }

                case 1: {
//                    Fragment selectedFrag = new HomeFragment();
//
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_dash, selectedFrag).commit();
//                        Toast.makeText(getApplicationContext(), R.string.dash_home, Toast.LENGTH_SHORT).show();
                    break;
                }

                case 2: {
//                    SharedPreferences sharedPreferences11 = PreferenceManager.getDefaultSharedPreferences(this);
//                    String userid1 = sharedPreferences11.getString("id","");
//
//                    if (userid1.equalsIgnoreCase("")){
//                        startActivity(new Intent(this, Login.class));
//                    }else {
//                        startActivity(new Intent(getApplicationContext(), OrderHistory.class));
//                    }
                    //  Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
                    break;
                }

                case 3: {
//                    startActivity(new Intent(getApplicationContext(), Feedback.class));
                    //  Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
                    break;
                }

                case 4: {
//                    startActivity(new Intent(getApplicationContext(), TermsConditions.class));
                    //  Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 5: {
//                    SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
//                    editor1.putString("accessLogin","");
//                    editor1.putString("userdid","");
//                    editor1.putString("login", "");
//                    editor1.apply();
//                    String accessToken = sharedPreferences.setS("accessLogin",baseAccessToken);
//                    String userid = sharedPreferences.getString("userdid","");





                    break;
                }*//*
                case 2: {
                    startActivity(new Intent(getApplicationContext(), ChangePassword.class));
                    //  Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 3 : {
                    startActivity(new Intent(Dashboard.this,Transaction.class));
//                        Toast.makeText(Dashboard.this, "transections", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 4: {
                    startActivity(new Intent(getApplicationContext(), AboutUs.class));
                    //  Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 5:{
                    startActivity(new Intent(Dashboard.this,HelpAndSupport.class));
                    break;
                } case 6: {
                    startActivity(new Intent(Dashboard.this,Feedback.class));
                    break;
                }
                case 7: {
                    startActivity(new Intent(getApplicationContext(), ContactUs.class));
                    break;
                }

                *//**//*case 8 : {
                    startActivity(new Intent(Dashboard.this,ReferAndEarn.class));
                    break;
                }*//**//*


                case 8: {
                    startActivity(new Intent(Dashboard.this,TermsAndConditions.class));
                    Toast.makeText(Dashboard.this, "Terms and conditions", Toast.LENGTH_SHORT).show();
                    break;
                }*//*
     *//* case 9: {

                    final Dialog dialog = new Dialog(Dashboard.this);
                    dialog.setContentView(R.layout.dialoglogout);
                    dialog.setTitle("Logout");
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.setCancelable(false);
//
                    Button yes = (Button) dialog.findViewById(R.id.YES);
//

                    Button no = (Button) dialog.findViewById(R.id.NO);
//
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            apilogout();
                            progressDialog.show();

                            dialog.dismiss();

                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                    break;
                }*//*

                default:
                    break;
            }
            drawer.closeDrawers();
        });


    }*/


}