package com.android.ifarm.ifarming.ui.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "DicUsers")
public class DicUser extends Model {
    @Column(name = "DicName")
    public String dicName;
    @Column(name = "DicEmail")
    public String dicEmail;
    @Column(name = "DicMobile")
    public String dicMobile;
    @Column(name = "DicPwd")
    public String dicPwd;
    @Column(name = "DicUid")
    public long dicUid;

    public DicUser() {
        super();
    }

    public DicUser(String dicName, String dicEmail, String dicMobile, String dicPwd, long dicUid) {
        this.dicName = dicName;
        this.dicEmail = dicEmail;
        this.dicMobile = dicMobile;
        this.dicPwd = dicPwd;
        this.dicUid = dicUid;
    }
}
