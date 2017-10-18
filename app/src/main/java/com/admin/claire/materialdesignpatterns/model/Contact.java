package com.admin.claire.materialdesignpatterns.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claire on 2017/10/17.
 */

public class Contact {
    private String name;

    public Contact(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //為RecyclerView 新增範例資料
    public static List<Contact> contactsSampleList(){
        List<Contact> contactList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Contact contact = new Contact();
            contact.setName("Contacts Person -" + i);
            contactList.add(contact);
        }
        return contactList;
    }
}
