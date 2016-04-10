package com.android.ifarm.ifarming.ui.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "DicAnims")
public class DicAnim extends Model {
    @Column(name = "DicFrom")
    public String dicFrom;
    @Column(name = "DicCode")
    public long dicCode;
    @Column(name = "DicType")
    public String dicType;
    @Column(name = "DicPz")
    public String dicPz;
    @Column(name = "DicCount")
    public String dicCount;
    @Column(name = "DicUid")
    public long dicUid;

    public DicAnim() {
        super();
    }

    public DicAnim(String dicFrom, long dicCode, String dicType, String dicPz, String dicCount, long dicUid) {
        this.dicFrom = dicFrom;
        this.dicCode = dicCode;
        this.dicType = dicType;
        this.dicPz = dicPz;
        this.dicCount = dicCount;
        this.dicUid = dicUid;
    }
}
