package com.android.ifarm.ifarming.ui.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "DicSarss")
public class DicSars extends Model implements Serializable {
    @Column(name = "DicFrom")
    public String dicFrom;
    @Column(name = "DicCode")
    public long dicCode;
    @Column(name = "DicType")
    public String dicType;
    @Column(name = "DicPz")
    public String dicPz;
    @Column(name = "DicNum")
    public String dicNum;
    @Column(name = "DicTime")
    public long dicTime;
    @Column(name = "DicPic")
    public String dicPic;
    @Column(name = "DicUid")
    public long dicUid;

    public DicSars() {
        super();
    }

    public DicSars(String dicFrom, long dicCode, String dicType, String dicPz, String dicNum, long dicTime, String dicPic, long dicUid) {
        this.dicFrom = dicFrom;
        this.dicCode = dicCode;
        this.dicType = dicType;
        this.dicPz = dicPz;
        this.dicNum = dicNum;
        this.dicTime = dicTime;
        this.dicPic = dicPic;
        this.dicUid = dicUid;
    }
}
