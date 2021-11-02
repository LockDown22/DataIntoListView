package com.example.bai76;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<RSSItem> rssItems = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<RSSItem> rssItems, Context context) {
        this.rssItems = rssItems;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rssItems.size();
    }

    @Override
    public Object getItem(int i) {
        return rssItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = inflater.inflate(R.layout.activity_dong,viewGroup,false);
        }
        ImageView hinh = view.findViewById(R.id.imgHinh);
        TextView tieude = view.findViewById(R.id.tvTitle);
        TextView mota = view.findViewById(R.id.tvMota);

        tieude.setText(rssItems.get(i).getTitle());
        mota.setText(rssItems.get(i).getDescription());

        String urlHinh = "https://s.vnecdn.net/vnexpress/i/v20/logos/vne_logo_rss.png";
        Picasso.with(context).load(urlHinh).resize(100,100).centerCrop().into(hinh);

        return view;
    }
}
