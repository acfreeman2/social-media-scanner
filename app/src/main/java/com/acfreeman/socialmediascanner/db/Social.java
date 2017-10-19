package com.acfreeman.socialmediascanner.db;

/**
 * Created by jianziyu on 2017/9/19.
 */

public class Social {
    private long id;
    private String type;
    private String username;
    public Social()
    {
    }
    public Social(long id,String type,String username)
    {
        this.id=id;
        this.type=type;
        this.username=username;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getType() {
        return type;
    }
}
