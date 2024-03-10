package edu.itc.appsports_androidnangcao.MODEL;

import java.io.Serializable;

public class THELOAISANPHAM implements Serializable {
    public String idtheloai, idchude, tenthheloai, hinhtheloai;

    public THELOAISANPHAM(String idtheloai, String idchude, String tenthheloai, String hinhtheloai) {
        this.idtheloai = idtheloai;
        this.idchude = idchude;
        this.tenthheloai = tenthheloai;
        this.hinhtheloai = hinhtheloai;
    }

    public THELOAISANPHAM() {
    }
}
