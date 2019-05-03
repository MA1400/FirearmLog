package com.example.firearmlog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class FirearmAdapter extends ArrayAdapter<Firearm> {
    private List<Firearm> firearmList;                // The list of firearms to display
    private Context context;                          // The original activity that displays this
    private int layoutResource;                       // the layout to use

    /**
     *   Basic constructor
     * @param context - The activity calling this
     * @param resource  The layout to use to display
     * @param firearmList  The list of fish to display
     */
    public FirearmAdapter(Context context, int resource, List<Firearm> firearmList) {
        super(context, resource, firearmList);
        this.context = context;
        this.layoutResource = resource;
        this.firearmList = firearmList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the firearms we are displaying
        Firearm firearm = firearmList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.firearm_row_layout, null);

        TextView tvSpecies=(TextView)view.findViewById(R.id.textViewTypeOfFirearm);
        TextView tvWeight=(TextView)view.findViewById(R.id.textViewCaliber);
        TextView tvDate=(TextView)view.findViewById(R.id.textViewRoundCount);
        tvSpecies.setText(firearm.getTypeOfFirearm());
        tvWeight.setText(firearm.getCaliber());
        tvDate.setText(firearm.getRoundCount());

        return(view);
    }}
