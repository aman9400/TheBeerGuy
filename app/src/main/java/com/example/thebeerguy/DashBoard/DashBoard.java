package com.example.thebeerguy.DashBoard;

import com.example.Databse.MyDatabase;
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
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.NotLogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
    private TextView nav_header_email;
    private TextView navUsername;
    RelativeLayout badgeLayout;
   public static Dialog dialog;
    TextView tv;
    private int newCartNumber1;

    public static int ID ;


    @Override
    public void onBackPressed() {

        customExitDialog();
    }

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

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(DashBoard.this);

        menu1 = navigationView.getMenu();
        navigationView.setVerticalScrollBarEnabled(false);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hammenu);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + "name" + "</font>"));
        getSupportActionBar().setElevation(10f);

        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.nav_header_name);
        imageViewnav = headerView.findViewById(R.id.nav_header_imageView);
        nav_header_email = headerView.findViewById(R.id.nav_header_email);

        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String nameLoggedIn = prefs1.getString("LoginName", "Welcome Guest");
        String email = prefs1.getString("email", "");

        navUsername.setText(nameLoggedIn);
        nav_header_email.setText(email);

        setFragment(new Home());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {

            switch (item.getItemId()) {
                case R.id.theBeerGuy_home: {
                    setFragment(new Home());
//                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.theBeerGuy_fav: {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

                    if (Islogin) {
                        setFragment(new Favourites());

                    } else {
                        setFragment(new NotLogin());
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

                        setFragment(new NotLogin());
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

                        setFragment(new NotLogin());
                    }

//                    Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.theBeerGuy_account: {


                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

                    if (Islogin) {

                        setFragment(new Account());
//                        Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();

                    } else {

                        setFragment(new NotLogin());
                    }

                    break;
                }


                default:
                    Toast.makeText(this, "wrong choice", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status


        if (Islogin){

            Menu navigationMenu = navigationView.getMenu();
            navigationMenu.findItem(R.id.nav_create_account).setVisible(false);
            navigationMenu.findItem(R.id.nav_signin).setVisible(false);
        }else {

            Menu navigationMenu = navigationView.getMenu();
            navigationMenu.findItem(R.id.nav_SignOut).setVisible(false);
            navigationMenu.findItem(R.id.nav_profile).setVisible(false);
            navigationMenu.findItem(R.id.nav_recentOrder).setVisible(false);
        }

    }

    private void customExitDialog() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.exit_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView exit_TV_cancle, exit_TV_yes;

        exit_TV_cancle = dialog.findViewById(R.id.exit_TV_cancle);
        exit_TV_yes = dialog.findViewById(R.id.exit_TV_yes);

        exit_TV_cancle.setOnClickListener(v -> dialog.dismiss());

        exit_TV_yes.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
            finishAffinity();
            System.exit(0);
        });

        dialog.show();

    }

    void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_dash, fragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean Islogin = prefs.getBoolean("Islogin", false);


        setTitle(menuItem.getTitle());


        ID = menuItem.getItemId();

        if (ID == R.id.nav_item_home) {
            startActivity(new Intent(getApplicationContext(), DashBoard.class));
//            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

        } else if (ID == R.id.nav_item_beer) {

            Intent intent = new Intent(this, SubCategory.class);
            intent.putExtra("subCatId", "3968");
            intent.putExtra("typeID", "1");
            intent.putExtra("name", "Beer");
            startActivity(intent);

//            Toast.makeText(this, "Beer", Toast.LENGTH_SHORT).show();

        } else if (ID == R.id.nav_item_liquor) {

            Intent intent = new Intent(this, SubCategory.class);
            intent.putExtra("subCatId", "1432");
            intent.putExtra("typeID", "3");
            intent.putExtra("name", "Liquor");
            startActivity(intent);
//            Toast.makeText(this, "Liquor", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_item_wine) {

            Intent intent = new Intent(this, SubCategory.class);
            intent.putExtra("subCatId", "24235");
            intent.putExtra("typeID", "2");
            intent.putExtra("name", "Wine");
            startActivity(intent);
//            Toast.makeText(this, "Wine", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_item_recent) {

            setFragment(new Recent());
//            Toast.makeText(getApplicationContext(), "Recent Viewed", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_create_account) {



            startActivity(new Intent(this, SignUp.class));
//            Toast.makeText(this, "Create Account", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_signin) {



            startActivity(new Intent(this, Login.class));
//            Toast.makeText(this, "Sign In", Toast.LENGTH_SHORT).show();


        }else if (ID == R.id.nav_profile) {

//            startActivity(new Intent(this, Account.class));
//            Toast.makeText(this, "Sign In", Toast.LENGTH_SHORT).show();
            setFragment(new Account());


        }else if (ID == R.id.nav_recentOrder) {

            setFragment(new Orders());


        }else if (ID == R.id.nav_SignOut) {

            SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(DashBoard.this);
            prefs1.edit().putBoolean("Islogin", false).commit();
            prefs1.edit().putString("LoginName", "Welcome Guest").commit();// islogin is a boolean value of your login status
            prefs1.edit().putString("email","").commit();//
            prefs1.edit().putString("name","").commit();//

            startActivity(new Intent(this, LandingScreen.class));
//            Toast.makeText(this, "Sign In", Toast.LENGTH_SHORT).show();
            finish();


        } else if (ID == R.id.nav_help ) {



            startActivity(new Intent(getApplicationContext(), NavHelp.class));
//            Toast.makeText(getApplicationContext(), "Help and FAQ", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_contactus) {

            startActivity(new Intent(getApplicationContext(), NavContactUs.class));
//            Toast.makeText(getApplicationContext(), "Contact Us", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_delivery_rate) {

            startActivity(new Intent(getApplicationContext(), NavDeliveryRate.class));
//            Toast.makeText(getApplicationContext(), "Delivery Rate", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_social_resposibility) {

            startActivity(new Intent(getApplicationContext(), NavSocialResponsibility.class));
//            Toast.makeText(getApplicationContext(), "Social Responsibility", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_testimonials) {

            startActivity(new Intent(getApplicationContext(), NavTestimonials.class));
//            Toast.makeText(getApplicationContext(), "Testimonials", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.nav_party_drink_calculator) {

            startActivity(new Intent(getApplicationContext(), NavPartyDrinkCalculator.class));
//            Toast.makeText(getApplicationContext(), "Party Drink Calculator", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.order_alcohol) {

            startActivity(new Intent(getApplicationContext(), NavOrderAlcohol.class));
//            Toast.makeText(getApplicationContext(), "How to Order Alcohol", Toast.LENGTH_SHORT).show();


        } else if (ID == R.id.franchise_sponsorship) {

            startActivity(new Intent(getApplicationContext(), NavFranchiseSponsorship.class));
//            Toast.makeText(getApplicationContext(), "Franchise and Sponsorship", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();

        }

        // Close the navigation drawer

        drawer.closeDrawers();

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        MenuItem item = menu.findItem(R.id.badge);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);

         badgeLayout = (RelativeLayout)    MenuItemCompat.getActionView(item);

        newCartNumber1 = MyDatabase.getDatabase(DashBoard.this).patientDAO().getW() ;
        tv = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText(""+newCartNumber1);
        
        tv.setOnClickListener(v-> {
//            Toast.makeText(this, ""+Common.cartNumber, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ReviewCart.class);
            startActivity(intent);
        });

        ImageView imageView = (ImageView) badgeLayout.findViewById(R.id.clickCartIconMenu);
        imageView.setOnClickListener(v->{
            if(newCartNumber1 == 0){
                Toast.makeText(this, "No Product added", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(this, ReviewCart.class);
                startActivity(intent);
            }
        });

        return true;
    }

}