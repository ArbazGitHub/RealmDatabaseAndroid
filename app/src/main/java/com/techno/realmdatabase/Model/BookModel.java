package com.techno.realmdatabase.Model;

import android.util.Log;
import android.widget.Toast;

import com.techno.realmdatabase.Global.Constant;
import com.techno.realmdatabase.Interface.CRUDInterface;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Arbaz.
 * Date: 23/4/18
 * Time: 1:51 PM
 */
public class BookModel implements CRUDInterface {

    @Override
    public boolean addRecord(Realm realm, Book book) {
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(book);//copyToRealmOrUpdate
            realm.commitTransaction();
            Log.e(Constant.APP_LOG, "Data Inserted");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RealmResults<Book> viewList(Realm realm) {
        Log.e(Constant.APP_LOG, "Inserted List" + realm.where(Book.class).findAll().size());
        return realm.where(Book.class).findAll();
    }

    @Override
    public void updateRecord(Realm realm, Book book) {
        try {
            realm.beginTransaction();
            RealmResults<Book> realmResults = realm.where(Book.class).equalTo("_Id", book.get_Id()).findAll();
            for (Book obj : realmResults) {
                //obj.set_Id(book.get_Id());//Its not change coz is primary key
                obj.setName(book.getName());
                obj.setMobile(book.getMobile());
            }
            realm.commitTransaction();
            Log.e(Constant.APP_LOG, "Record Updated" + realm.where(Book.class).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRecord(Realm realm, Book book) {
        try {
            realm.beginTransaction();
            RealmResults<Book> realmResults = realm.where(Book.class).equalTo("_Id", book.get_Id()).findAll();
            for (Book obj : realmResults) {
               obj.deleteFromRealm();
            }
            realm.commitTransaction();
            Log.e(Constant.APP_LOG, "Record Deleted" + realm.where(Book.class).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
