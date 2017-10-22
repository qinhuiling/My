package com.bwie.asus.shopping;

import java.util.List;

/**
 * Created by ASUS on 2017/10/19.
 */

public class EventBean {

    private boolean is_all;
    private List<Bean> list;

    public EventBean(boolean is_all, List<Bean> list) {
        this.is_all = is_all;
        this.list = list;
    }

    public boolean is_all() {
        return is_all;
    }

    public void setIs_all(boolean is_all) {
        this.is_all = is_all;
    }

    public List<Bean> getList() {
        return list;
    }

    public void setList(List<Bean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "EventBus{" +
                "is_all=" + is_all +
                ", list=" + list +
                '}';
    }

}
