package com.example.deepak.localto_doapp;

import java.util.List;

public class Wrapper
{
    List<TaskDTO> list;

    public List<TaskDTO> getList() {
        return list;
    }

    public void setList(List<TaskDTO> list) {
        this.list = list;
    }

    public Wrapper(List<TaskDTO> list) {
        this.list = list;



    }


    @Override
    public String toString() {
        return "Wrapper{" +
                "list=" + list +
                '}';
    }
}
