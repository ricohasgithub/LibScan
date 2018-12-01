package com.example.ricoz.libscan;

import java.util.*;

public class BookList {

    public ArrayList<Book> list;

    public BookList () {
        list = new ArrayList<Book>();
    }

    public void addBook (Book b) {
        if (b == null) {
            throw new IllegalArgumentException();
        }
        if (!containsBook(b)) {
            list.add(b);
        }
    }

    public boolean containsBook (Book find) {
        for (Book b : list) {
            if (b.equals(find)) {
                return true;
            }
        }
        return false;
    }

    public void removeBook (Book b) {
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).equals(b)) {
                list.remove(i);
                break;
            }
        }
    }

    public int getSize () {
        return list.size();
    }

    public String getBookTitle (int index) {
        return list.get(index).toString();
    }

}