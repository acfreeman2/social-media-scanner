package com.acfreeman.socialmediascanner;

/**
 * Created by jianziyu on 2017/9/19.
 */

public class Phones {
    private int id;
    private String number;
    private String type;
    public Phones()
    {
    }
    public Phones(int id,String number,String type)
    {
        this.id=id;
        this.number=number;
        this.type=type;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String number) {
        this.number = number;
    }

    public void setAddress(String type) {
        this.type = type;
    }
    public int getId() {
        return id;
    }
    public String getAddress() {
        return type;
    }
    public String getName() {
        return number;
    }
}
