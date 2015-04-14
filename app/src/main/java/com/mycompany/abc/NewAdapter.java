package com.mycompany.abc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class NewAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem;
    public ArrayList<HashMap<String, String>> tempChild;
    public ArrayList<Object> ChildItem = new ArrayList<>();
    public Activity activity;
    private final Context context;

    public NewAdapter(Context context,ArrayList<String> grList, ArrayList<Object> childItem) {
        this.context = context;
        groupItem = grList;
        this.ChildItem = childItem;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.child_drawer_layout, parent, false);
        }

        TextView itemName = (TextView) v.findViewById(R.id.text);
        ImageView itemImage = (ImageView) v.findViewById(R.id.icon);

        tempChild = (ArrayList<HashMap<String, String>>) ChildItem.get(groupPosition);
        itemName.setText(tempChild.get(childPosition).get("text"));
        itemImage.setImageResource(Integer.parseInt(tempChild.get(childPosition).get("icon")));

        return v;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) ChildItem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.parent_drawer_layout, parent, false);
        }

        TextView itemName = (TextView) v.findViewById(R.id.category);

        itemName.setText(groupItem.get(groupPosition));

        return v;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
