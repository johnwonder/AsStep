package com.example.john.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;

    public NewsAdapter(Context context,int textViewResourceId,List<News> objects) {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }else{

            view = convertView;
        }
        TextView newsTitleText = (TextView)view.findViewById(R.id.news_title);
        newsTitleText.setText(news.getTitle());
        return  view;
    }



}
