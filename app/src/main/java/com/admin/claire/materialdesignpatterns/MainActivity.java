package com.admin.claire.materialdesignpatterns;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 因為不是使用 onCreateOptionsMenu() 來載入導覽菜單，
 * 所以不能用 onOptionsItemSelected() 來設定反應，(DrawerLayout)
 * 要加上 OnNavigationItemSelectedListener 來操作。
 *
 * 旋轉螢幕等於 Configuration Change menu 和 textDrawerItemName 會被重設
 * 要記著按了的那些 menu Item ？這便要用 onSaveInstanceState() 。
 */
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle; //在Toolbar上加上「三」圖示
    TextView textDrawerItemName;
    DrawerLayout drawerLayout;
    NavigationView nav_View;
    FloatingActionButton fab;

    private static final String NAV_ITEM_ID = "nav_index";
    //用來儲存點擊了的 menuItem id,必免旋轉螢幕後 menu 和 textDrawerItemName 會被重設
    private int navItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view
        initView();
        //Handler
        initDrawerLayout();
        initNavigationView(savedInstanceState);
        initFab();

    }

    private void initView() {
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textDrawerItemName = (TextView)findViewById(R.id.textDrawerItemName);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        nav_View = (NavigationView)findViewById(R.id.navigation_view);
        fab = (FloatingActionButton)findViewById(R.id.fab);

    }

    private void initDrawerLayout() {
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initNavigationView(Bundle savedInstanceState ) {
        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Snack bar 跟 Toast 不同的是，Snack bar 是以 view 作參數，而不是以 context
                Snackbar.make(textDrawerItemName,menuItem.getTitle() + "pressed",
                        Snackbar.LENGTH_SHORT).show();

                navigateTo(menuItem);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //利用Bundle 取出儲存的資料
        if (null != savedInstanceState){
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, R.id.navigation_item_1);
        }else {
            navItemId = R.id.navigation_item_1;
        }

        navigateTo(nav_View.getMenu().findItem(navItemId));

    }


    private void navigateTo(MenuItem menuItem) {
        textDrawerItemName.setText(menuItem.getTitle());
        //這裡會用 navItemId 記著當前的 menuItem。
        navItemId = menuItem.getItemId();
        menuItem.setCheckable(true);

    }

    //之後再用 onSaveInstanceState() 來記著當前的 navItemId
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //裝目前狀態值存儲起來
        outState.putInt(NAV_ITEM_ID, navItemId);
    }

    private void initFab(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(textDrawerItemName, "Snack bar!!!!",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("Undo pressed")
                                        .setNeutralButton("OK", null)
                                        .show();
                            }
                        }).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                Snackbar.make(textDrawerItemName,item.getTitle(),Snackbar.LENGTH_SHORT).show();
                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
