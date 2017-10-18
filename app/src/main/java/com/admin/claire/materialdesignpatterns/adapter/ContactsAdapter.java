package com.admin.claire.materialdesignpatterns.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.admin.claire.materialdesignpatterns.R;
import com.admin.claire.materialdesignpatterns.model.Contact;

import java.util.List;

/**
 * 先在 ContactsAdapter 中加入 MyViewHolder
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
    private List<Contact> mContact;

    public ContactsAdapter (List<Contact> contacts){
        mContact = contacts;
    }

    /**
     * 建立自訂要顯示的view 畫面
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView ;
        //建立一個點擊recyclerView item 的interface
        public MyViewHolderClick mListener;

        public MyViewHolder(View itemView,MyViewHolderClick listener) {
            super(itemView);
             mListener = listener;
            nameTextView = (TextView)itemView.findViewById(R.id.textContact);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.clickOnView(v,getLayoutPosition());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(contactView, new MyViewHolderClick() {
            @Override
            public void clickOnView(View view, int position) {
                Contact contact = mContact.get(position);
                Snackbar.make(view, contact.getName(), Snackbar.LENGTH_SHORT).show();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = mContact.get(position);
        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(contact.getName());

    }

    @Override
    public int getItemCount() {
        return mContact.size();
    }

}
