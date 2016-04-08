package com.android.ifarm.ifarming.ui.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "DicFarms")
public class DicFarm extends Model {
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

    public DicFarm() {
        super();
    }

    public DicFarm(String dicAvatar, String dicName, String dicAddress, String dicContact, String dicMobile, long dicCode) {
        this.dicAvatar = dicAvatar;
        this.dicName = dicName;
        this.dicAddress = dicAddress;
        this.dicContact = dicContact;
        this.dicMobile = dicMobile;
        this.dicCode = dicCode;
    }
}
