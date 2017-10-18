package com.admin.claire.materialdesignpatterns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.admin.claire.materialdesignpatterns.adapter.ContactsAdapter;
import com.admin.claire.materialdesignpatterns.adapter.DividerItemDecoration;
import com.admin.claire.materialdesignpatterns.model.Contact;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    Toolbar toolbar_recycler;
    RecyclerView mRvContacts;
    ContactsAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutMag;
    List<Contact> mContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        toolbar_recycler = (Toolbar)findViewById(R.id.toolbar_recycler);
        setSupportActionBar(toolbar_recycler);
        // add back arrow to toolbar <-
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRvContacts = (RecyclerView)findViewById(R.id.recycler_View);
        mRvContacts.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutMag = new LinearLayoutManager(this);
        mRvContacts.setLayoutManager(mLayoutMag);

        //為RecyclerView 新增範例資料
        mContactList = Contact.contactsSampleList();
        mAdapter = new ContactsAdapter(mContactList);
        mRvContacts.setAdapter(mAdapter);

        //為RecyclerView 加上項目分隔線
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRvContacts.addItemDecoration(itemDecoration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rv_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                addContact();
                return true;
            case R.id.action_remove:
                removeContacts();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addContact() {
        Contact contact = new Contact();
        contact.setName("Robert John");
        mContactList.add(1, contact);
        mAdapter.notifyItemInserted(1);
    }

    private void removeContacts(){
        mContactList.remove(mContactList.size()-1);
        mAdapter.notifyItemRemoved(mContactList.size());
    }
}
