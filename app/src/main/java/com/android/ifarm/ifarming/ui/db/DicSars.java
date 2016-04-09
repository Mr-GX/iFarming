package com.android.ifarm.ifarming.ui.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "DicSarss")
public class DicSars extends Model {
    @Column(name = "DicFrom")
    public String dicFrom;
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

    public DicSars(String dicFrom, String dicType, String dicPz, String dicNum, long dicTime, String dicPic, long dicUid) {
        this.dicFrom = dicFrom;
        this.dicType = dicType;
        this.dicPz = dicPz;
        this.dicNum = dicNum;
        this.dicTime = dicTime;
        this.dicPic = dicPic;
        this.dicUid = dicUid;
    }
}
