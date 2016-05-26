package com.ypf.fastsearch.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Record implements Serializable {
    public String company;
    public String number;
    public boolean isFinished;
    public List<Detial> detials;
}
