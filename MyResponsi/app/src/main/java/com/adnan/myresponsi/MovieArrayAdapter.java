package com.adnan.myresponsi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MovieArrayAdapter extends ArrayAdapter {

    private List<DetailFilm> detailFilmList;
    private int resource;
    private Context context;

    public MovieArrayAdapter(Context context, int resource, List<DetailFilm> detailFilm) {
        super(context, resource, detailFilm);
        this.context = context;
        this.detailFilmList = detailFilm;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailFilm details = detailFilmList.get(position);

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView judulFilm = (TextView) view.findViewById(R.id.tv_title);
        TextView sinopFilm = (TextView) view.findViewById(R.id.tv_sinopsis);
        ImageView image = (ImageView) view.findViewById(R.id.img1);

        judulFilm.setText(details.getOriginal_title());
        sinopFilm.setText(details.getOverview());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+details.getPoster_path()).into(image);

        return view;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return detailFilmList.get(position);
    }
}
