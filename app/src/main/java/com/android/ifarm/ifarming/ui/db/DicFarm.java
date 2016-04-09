package com.android.ifarm.ifarming.ui.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "DicFarms")
public class DicFarm extends Model implements Serializable{
    @Column(name = "DicAvatar")
    public String dicAvatar;
    @Column(name = "DicName")
    public String dicName;
    @Column(name = "DicAddress")
    public String dicAddress;
    @Column(name = "DicContact")
    public String dicContact;
    @Column(name = "DicMobile")
    public String dicMobile;
    @Column(name = "DicCode")
    public long dicCode;
    @Column(name = "DicUid")
    public long dicUid;

    public DicFarm() {
        super();
    }

    public DicFarm(String dicAvatar, String dicName, String dicAddress, String dicContact, String dicMobile, long dicCode, long dicUid) {
        this.dicAvatar = dicAvatar;
        this.dicName = dicName;
        this.dicAddress = dicAddress;
        this.dicContact = dicContact;
        this.dicMobile = dicMobile;
        this.dicCode = dicCode;
        this.dicUid = dicUid;
    }
}
