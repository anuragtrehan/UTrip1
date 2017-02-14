package anurag.com.utrip1.Activity.Model;

import java.io.Serializable;

/**
 * Created by AnuragTrehan on 12/24/2016.
 */

public class SendData implements Serializable
{
    int must_see,adventure,art,entertainment,shopping,restuarent,nature,religious,park,no_days;

    public int getAdventure() {
        return adventure;
    }

    public void setAdventure(int adventure) {
        this.adventure = adventure;
    }

    public int getArt() {
        return art;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public int getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }

    public int getMust_see() {
        return must_see;
    }

    public void setMust_see(int must_see) {
        this.must_see = must_see;
    }

    public int getNature() {
        return nature;
    }

    public void setNature(int nature) {
        this.nature = nature;
    }

    public int getNo_days() {
        return no_days;
    }

    public void setNo_days(int no_days) {
        this.no_days = no_days;
    }

    public int getPark() {
        return park;
    }

    public void setPark(int park) {
        this.park = park;
    }

    public int getReligious() {
        return religious;
    }

    public void setReligious(int religious) {
        this.religious = religious;
    }

    public int getRestuarent() {
        return restuarent;
    }

    public void setRestuarent(int restuarent) {
        this.restuarent = restuarent;
    }

    public int getShopping() {
        return shopping;
    }

    public void setShopping(int shopping) {
        this.shopping = shopping;
    }
}
