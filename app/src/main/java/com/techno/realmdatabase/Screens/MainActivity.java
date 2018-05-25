package com.techno.realmdatabase.Screens;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techno.realmdatabase.Adapter.DataListAdapter;
import com.techno.realmdatabase.Listener.RecyclerItemClickListener;
import com.techno.realmdatabase.Model.Book;
import com.techno.realmdatabase.Model.BookModel;
import com.techno.realmdatabase.R;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Realm realm;
    BookModel bookModel;
    RecyclerView rvList;
    EditText etName, etMobile;
    Button btnAdd, btnView, btnUpdate, btnDelete;
    int tableId = 1;
    String strName = "", strMobile = "";
    Book book;
    DataListAdapter dataListAdapter;
    ArrayList<Book> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //get realm instance
            realm = Realm.getDefaultInstance();
            bookModel = new BookModel();
            book = new Book();
            initViews();
            lieteners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initViews() {
        try {
            rvList = findViewById(R.id.rvList);
            etName = findViewById(R.id.etName);
            etMobile = findViewById(R.id.etMobile);

            btnAdd = findViewById(R.id.btnAdd);
            btnView = findViewById(R.id.btnView);
            btnUpdate = findViewById(R.id.btnUpdate);
            btnDelete = findViewById(R.id.btnDelete);

            rvList.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lieteners() {
        try {
            btnAdd.setOnClickListener(this);
            btnView.setOnClickListener(this);
            btnUpdate.setOnClickListener(this);
            btnDelete.setOnClickListener(this);

            rvList.addOnItemTouchListener(new RecyclerItemClickListener(this, rvList, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    try {
                        book = bookArrayList.get(position);
                        etName.setText(book.getName());
                        etMobile.setText(book.getMobile());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onItemLongClick(View view, int position) {
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnAdd:
                    strName = etName.getText().toString();
                    strMobile = etMobile.getText().toString();
                    if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strMobile)) {
                        book = new Book(tableId, strName, strMobile);
                        bookModel.addRecord(realm, book);
                        tableId++;
                        recordList(realm);
                    } else {
                        Toast.makeText(this, getString(R.string.add_record_field_empty_msg), Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btnView:
                    if (realm != null) {
                        //getting list from Database
                        recordList(realm);
                    }
                    break;
                case R.id.btnUpdate:
                    if (realm != null) {
                        //book object is selected from recycler view click
                        if (book != null) {
                            strName = etName.getText().toString();
                            strMobile = etMobile.getText().toString();
                            if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strMobile)) {
                                book.setName(strName);
                                book.setMobile(strMobile);
                                //Realm function call here
                                bookModel.updateRecord(realm, book);
                                //getting list from Database
                                recordList(realm);
                            }

                        } else {
                            Toast.makeText(this, "Select Record From List", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.btnDelete:
                    if (realm != null) {
                        if (book != null) {
                            //book object is selected from recycler view click
                            strName = etName.getText().toString();
                            strMobile = etMobile.getText().toString();
                            if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strMobile)) {
                                book.setName(strName);
                                book.setMobile(strMobile);
                                //Realm function call here
                                bookModel.deleteRecord(realm, book);
                                //getting list from Database
                                recordList(realm);
                            }

                        } else {
                            Toast.makeText(this, "Select Record From List", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public void recordList(Realm realm) {
        try {
            bookArrayList = new ArrayList<>();
            etName.setText("");
            etMobile.setText("");
            if (bookModel.viewList(realm) != null) {
                if (bookModel.viewList(realm).size() > 0) {
                    displayView(false);
                    for (Book book : bookModel.viewList(realm)) {
                        bookArrayList.add(new Book(book.get_Id(), book.getName(), book.getMobile()));
                    }
                    dataListAdapter = new DataListAdapter(this, bookArrayList);
                    rvList.setAdapter(dataListAdapter);
                } else {
                    displayView(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayView(boolean b) {
        if (b) {
            rvList.setVisibility(View.GONE);
        } else {
            rvList.setVisibility(View.VISIBLE);
        }
    }
}
