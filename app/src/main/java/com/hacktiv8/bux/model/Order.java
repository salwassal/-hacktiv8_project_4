package com.hacktiv8.bux.model;

public class Order {
    String bookNo;
    String platno;
    String status;
    String transaksi;
    String total;
    String idTrip;
    String seatNo;
    String toTgl;
    boolean rated;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getPlatno() {
        return platno;
    }

    public void setPlatno(String platno) {
        this.platno = platno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
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

    public String getToTgl() {
        return toTgl;
    }

    public void setToTgl(String toTgl) {
        this.toTgl = toTgl;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }


}
