package com.tca.rez.tcainternationalgames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rez on 3/6/2018.
 */

public class CountryAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] countries;
    String[] teachers;
    String[] students;
    String flag;
    String score;

    public CountryAdapter(Context c, String[] countries, String[] teachers, String[] students, String flagURI) {
        this.countries = countries;
        this.teachers = teachers;
        this.students = students;
        this.flag=flagURI;
        this.score = "none";
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.length;
    }

    @Override
    public Object getItem(int i) {
        return countries[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.listviewcountry, null);
        TextView tvTeacher = (TextView) v.findViewById(R.id.tvTeacher);
        TextView tvStudent = (TextView) v.findViewById(R.id.tvStudent);
        TextView tvCountry = (TextView) v.findViewById(R.id.tvCountry);
        TextView tvScore = (TextView) v.findViewById(R.id.tvScore);

        try {
            ImageView img = (ImageView) v.findViewById(R.id.imgFlag);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(flag).getContent());
            img.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String teacher = teachers[i];
        String student = students[i];
        String country = countries[i];


        tvTeacher.setText(teacher);
        tvScore.setText(student);
        tvCountry.setText(country);
        if(score.equals("none"))
        {
            tvScore.setText("0.00");
            tvScore.setTextColor(Color.GRAY);
        }
        else
        {
            tvScore.setText(score);
        }
        return v;
    }
}
