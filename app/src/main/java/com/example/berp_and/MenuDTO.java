package com.example.berp_and;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {

    private String parent_menu;
    private ArrayList<String> child_menu;

    public MenuDTO(String parent_menu, ArrayList<String> child_menu) {
        this.parent_menu = parent_menu;
        this.child_menu = child_menu;
    }

    public String getParent_menu() {
        return parent_menu;
    }

    public void setParent_menu(String parent_menu) {
        this.parent_menu = parent_menu;
    }

    public ArrayList<String> getChild_menu() {
        return child_menu;
    }

    public void setChild_menu(ArrayList<String> child_menu) {
        this.child_menu = child_menu;
    }
}
