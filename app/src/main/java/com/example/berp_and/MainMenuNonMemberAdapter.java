package com.example.berp_and;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuNonMemberAdapter extends BaseExpandableListAdapter {

    LayoutInflater inflater;
    HashMap<String, ArrayList<String>> menu_list_none;
    ArrayList<String> parent_menu_none;

    int cnt = 0;

    public MainMenuNonMemberAdapter(LayoutInflater inflater, HashMap<String, ArrayList<String>> menu_list, ArrayList<String> parent_menu) {
        this.inflater = inflater;
        this.menu_list_none = menu_list;
        this.parent_menu_none = parent_menu;

    }
    @Override
    public int getGroupCount() {
        return menu_list_none.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return menu_list_none.get(parent_menu_none.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.menu_parent, parent, false);
        TextView tv_parent = convertView.findViewById(R.id.tv_parent);
        tv_parent.setText(parent_menu_none.get(groupPosition));



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.menu_child, parent, false);
        TextView tv_child = convertView.findViewById(R.id.tv_child);
        ArrayList<String> child = menu_list_none.get(parent_menu_none.get(groupPosition));
        tv_child.setText(child.get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
