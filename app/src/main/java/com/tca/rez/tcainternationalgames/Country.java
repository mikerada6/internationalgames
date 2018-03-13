package com.tca.rez.tcainternationalgames;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rez on 3/12/2018.
 */

public class Country {
    private String name;
    private String teacher;
    private String student1;
    private String student2;
    private double score;
    private double spiritScore;
    private Bitmap flag;

    public Country(String name, String teacher, String student1, String student2, double score, double spiritScore, String flagURI) {
        this.name = name;
        this.teacher = teacher;
        this.student1 = student1;
        this.student2 = student2;
        this.score = score;
        this.spiritScore = spiritScore;
        try {
            this.flag = BitmapFactory.decodeStream( (InputStream) new URL( flagURI ).getContent() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStudent1() {
        return student1;
    }

    public void setStudent1(String student1) {
        this.student1 = student1;
    }

    public String getStudent2() {
        return student2;
    }

    public void setStudent2(String student2) {
        this.student2 = student2;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getSpiritScore() {
        return spiritScore;
    }

    public void setSpiritScore(double spiritScore) {
        this.spiritScore = spiritScore;
    }

    public Bitmap getFlag() {
        return flag;
    }

    public void setFlag(Bitmap flag) {
        this.flag = flag;
    }

    public void setFlad(String flagURI) {
        try {
            this.flag = BitmapFactory.decodeStream( (InputStream) new URL( flagURI ).getContent() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
