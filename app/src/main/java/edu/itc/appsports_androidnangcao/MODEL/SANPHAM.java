package edu.itc.appsports_androidnangcao.MODEL;

import java.io.Serializable;

public class SANPHAM implements Serializable {
    public String idsanpham, idtheloai, tensanpham, hinhsanpham, mota, dongia, ngaycapnhat, luotmua;

    public SANPHAM(String idsanpham, String idtheloai, String tensanpham, String hinhsanpham, String mota, String dongia, String ngaycapnhat, String luotmua) {
        this.idsanpham = idsanpham;
        this.idtheloai = idtheloai;
        this.tensanpham = tensanpham;
        this.hinhsanpham = hinhsanpham;
        this.mota = mota;
        this.dongia = dongia;
        this.ngaycapnhat = ngaycapnhat;
        this.luotmua = this.luotmua;
    }

    public SANPHAM() {
    }

}
