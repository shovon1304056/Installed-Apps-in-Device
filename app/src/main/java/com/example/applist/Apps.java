package com.example.applist;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Apps extends ArrayAdapter<ApplicationInfo> {


    private List<ApplicationInfo> appList = null;
    private Context context;
    private PackageManager packageManager;

    public Apps(Context context, int resource, List<ApplicationInfo> objects) {
        super(context, resource, objects);

        this.context = context;
        this.appList = objects;
        packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return ((null != appList) ? appList.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != appList) ? appList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_items, null);
        }

        ApplicationInfo data = appList.get(position);

        if (null != data) {
            TextView appName = view.findViewById(R.id.app_name);
           // TextView appPackage = view.findViewById(R.id.app_package);
            ImageView icon = view.findViewById(R.id.app_icon);

            appName.setText(data.loadLabel(packageManager));
            //appPackage.setText(data.packageName);
            icon.setImageDrawable(data.loadIcon(packageManager));
        }

        return view;
    }
}
