package tech.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandableListAdapter extends BaseAdapter {

    TextView t1;
    ArrayList<String> nm= new ArrayList<String>();

    LayoutInflater in;
    Context context;

    public ExpandableListAdapter(Context applicationContext, ArrayList<String> nm){

        this.nm=nm;
        context=applicationContext;
        in=LayoutInflater.from(applicationContext);

    }


    @Override
    public int getCount() {
        return nm.size();
    }

    @Override
    public Object getItem(int i) {
        return nm.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        in=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view=in.inflate(R.layout.list_header,null);
        t1=(TextView)view.findViewById(R.id.textView_header_name);
        t1.setText(nm.get(i));
        return view;
    }
}
