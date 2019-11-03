package com.example.tranminhhieu_16046291_sqlite;

public class Lop {
    private int id;
    private String name;

    public Lop() {
    }

    public Lop(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
