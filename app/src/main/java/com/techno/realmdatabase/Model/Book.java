package com.techno.realmdatabase.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Arbaz.
 * Date: 23/4/18
 * Time: 12:49 PM
 */
public class Book extends RealmObject {

    @PrimaryKey
    private int _Id;

    private String name;

    private String mobile;

    public Book() {
    }

    public Book(int _Id, String name, String mobile) {
        this._Id = _Id;
        this.name = name;
        this.mobile = mobile;
    }

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
