package main.gestionhotel.ClassesPersistants;

import java.sql.Date;

public class Reservation {
    private int id_res;
    private int num_res;
    private int num_pers;
    private int num_chbr;
    private Date date_arv;
    private Date date_sort;
    private float total_rsv;

    public Reservation(int id_res, int num_res, int num_pers, int num_chbr, Date date_arv, Date date_sort, float total_rsv) {
        this.id_res = id_res;
        this.num_res = num_res;
        this.num_pers = num_pers;
        this.num_chbr = num_chbr;
        this.date_arv = date_arv;
        this.date_sort = date_sort;
        this.total_rsv = total_rsv;
    }

    public Reservation() {

    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getNum_res() {
        return num_res;
    }

    public void setNum_res(int num_res) {
        this.num_res = num_res;
    }

    public int getNum_pers() {
        return num_pers;
    }

    public void setNum_pers(int num_pers) {
        this.num_pers = num_pers;
    }

    public int getNum_chbr() {
        return num_chbr;
    }

    public void setNum_chbr(int num_chbr) {
        this.num_chbr = num_chbr;
    }

    public Date getDate_arv() {
        return date_arv;
    }

    public void setDate_arv(Date date_arv) {
        this.date_arv = date_arv;
    }

    public Date getDate_sort() {
        return date_sort;
    }

    public void setDate_sort(Date date_sort) {
        this.date_sort = date_sort;
    }

    public float getTotal_rsv() {
        return total_rsv;
    }

    public void setTotal_rsv(float total_rsv) {
        this.total_rsv = total_rsv;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_res=" + id_res +
                ", num_res=" + num_res +
                ", num_pers=" + num_pers +
                ", num_chbr=" + num_chbr +
                ", date_arv=" + date_arv +
                ", date_sort=" + date_sort +
                ", total_rsv=" + total_rsv +
                '}';
    }
}
