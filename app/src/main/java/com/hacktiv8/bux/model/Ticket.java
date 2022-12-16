package com.hacktiv8.bux.model;

public class Ticket {
    String bookNo, idTrip, seatNo, platno , toTgl, total, transaksi, status ;
    Boolean rated;


    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public Boolean getRated() {
        return rated;
    }

    public void setRated(Boolean rated) {
        this.rated = rated;
    }


    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getPlatno() {
        return platno;
    }

    public void setPlatno(String platno) {
        this.platno = platno;
    }

    public String getToTgl() {
        return toTgl;
    }

    public void setToTgl(String toTgl) {
        this.toTgl = toTgl;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
