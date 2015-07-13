package com.androidxmlparsing.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidxmlparsing.R;
import com.androidxmlparsing.adapter.util.ApplicationBitmapManager;
import com.androidxmlparsing.model.SongsList;


public class CustomListViewAdapter extends BaseAdapter {
    Activity context;
    ArrayList<SongsList> employees;
 
    public CustomListViewAdapter(Activity context, 
    		ArrayList<SongsList> employees) {
        this.context = context;
        this.employees = employees;
    }
 


	// private View Holder class
    private class ViewHolder {
        TextView title;
        TextView artist;
        TextView duration;
        ImageView thumb_image;
        ProgressBar spinner;
    }
 
    public int getCount() {
        return employees.size();
    }
 
    public Object getItem(int position) {
        return employees.get(position);
    }
 
    public long getItemId(int position) {
        return employees.indexOf(getItem(position));
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.title); // title
            holder.artist = (TextView)convertView.findViewById(R.id.artist); // artist name
            holder.duration = (TextView)convertView.findViewById(R.id.duration); // duration
            holder.thumb_image=(ImageView)convertView.findViewById(R.id.list_image); // thumb image
            holder.spinner = (ProgressBar) convertView.findViewById(R.id.spinner);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        SongsList employee = (SongsList) getItem(position);
 
//        holder.txtTitle.setText(employee.getId() + ": " + employee.getName());
//        holder.txtDepartment.setText(employee.getDepartment() + ": "
//                + employee.getType());
//        holder.txtEmail.setText("Email: " + employee.getEmail());
        
        // Setting all values in listview
        holder.title.setText(employee.getKEY_TITLE());
        holder.artist.setText(employee.getKEY_ARTIST());
        holder.duration.setText(employee.getKEY_DURATION());
       
		ApplicationBitmapManager.INSTANCE.loadBitmap(
				employee.getKEY_THUMB_URL(), holder.thumb_image,
		holder.spinner);
        return convertView;
    }
}