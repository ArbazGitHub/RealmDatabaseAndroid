package com.techno.realmdatabase;

import android.app.Application;

import com.techno.realmdatabase.Global.Constant;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Arbaz.
 * Date: 23/4/18
 * Time: 12:39 PM
 */
public class RealmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Constant.DB_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        //for multiple configuration
        /*RealmConfiguration myConfig = new RealmConfiguration.Builder(this)
                .name("myrealm.realm")
                .schemaVersion(2)
                .setModules(new MyCustomSchema())
                .build();

        RealmConfiguration otherConfig = new RealmConfiguration.Builder(this)
                .name("otherrealm.realm")
                .schemaVersion(5)
                .setModules(new MyOtherSchema())
                .build();

        Realm myRealm = Realm.getInstance(myConfig);
        Realm otherRealm = Realm.getInstance(otherConfig);*/
    }
}
