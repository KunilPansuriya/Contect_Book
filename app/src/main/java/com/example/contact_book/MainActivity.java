package com.example.contact_book;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.contact_book.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    //    ArrayList<Integer> idList=new ArrayList<>();
    //    ArrayList<String> nameList=new ArrayList();
    //    ArrayList<String> numberList=new ArrayList();
    //    ArrayList<String> emailList=new ArrayList();
    ContactAdapter adapter;
    ArrayList<ContactModal> contactList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        MydataBase1 MydataBase1=new MydataBase1(MainActivity.this);
        // Showdata();
        // Showdata1();
        data();

        binding.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Contact_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void data() {
        MydataBase1 mydataBase1 = new MydataBase1(MainActivity.this);
        Cursor cursor = mydataBase1.data();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String number = cursor.getString(3);
            String imgURI=cursor.getString(4);
            ContactModal modal = new ContactModal(id, name, email, number,imgURI);
            contactList.add(modal);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerview.setLayoutManager(manager);
        ContactAdapter adapter = new ContactAdapter(MainActivity.this, contactList);
        binding.recyclerview.setAdapter(adapter);
    }
}



//    private void Showdata1() {
//        MydataBase1 mydataBase1 = new MydataBase1(MainActivity.this);
//        Cursor cursor = mydataBase1.showdata1();
//        while (cursor.moveToNext())
//        {
//            int id  = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String email = cursor.getString(2);
//            String number = cursor.getString(3);
//            ContactModal modal = new ContactModal(id,name,email,number);
//            contactList.add(modal);
//        }
//        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.recyclerview.setLayoutManager(manager);
//        ContactAdapter adapter = new ContactAdapter(MainActivity.this,contactList);
//        binding.recyclerview.setAdapter(adapter);
//    }
//}

//    private void Showdata() {
//        MydataBase1 mydataBase1 = new MydataBase1(MainActivity.this);
//        Cursor cursor = mydataBase1.showdata();
//        while (cursor.moveToNext())
//        {
//            int id  = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String email = cursor.getString(2);
//            String number = cursor.getString(3);
//            ContactModal modal = new ContactModal(id,name,email,number);
//            contactList.add(modal);
//        }
//        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.recyclerview.setLayoutManager(manager);
//        ContactAdapter adapter = new ContactAdapter(MainActivity.this,contactList);
//        binding.recyclerview.setAdapter(adapter);
//    }