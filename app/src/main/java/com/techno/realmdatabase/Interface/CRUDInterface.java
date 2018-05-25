package com.techno.realmdatabase.Interface;

import com.techno.realmdatabase.Model.Book;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Arbaz.
 * Date: 23/4/18
 * Time: 1:49 PM
 */
public interface CRUDInterface {
    boolean addRecord(Realm realm, Book book);
    RealmResults<Book> viewList(Realm realm);
    void updateRecord(Realm realm,Book book);
    void deleteRecord(Realm realm,Book book);
}
