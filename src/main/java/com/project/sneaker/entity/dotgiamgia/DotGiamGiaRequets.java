package com.project.sneaker.entity.dotgiamgia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotGiamGiaRequets {
    private Long id;

    private String tenDot;

    private double giaTri;

    private String loaiGiamGia;

//    private double giaTriGiamToiDa;

    private Boolean trangThai;


    private Date thoiGianBatDau;

    private Date thoiGianKetThuc;


    private Date ngayTao;


    private TaiKhoan taiKhoan;
}
