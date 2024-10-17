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

    private Double giaTri;

    private String loaiGiamGia;

    private Double giaTriGiamToiDa;

    private Boolean trangThai;


    private Date thoiGianBatDau;

    private Date thoiGianKetThuc;


    private Date ngayTao;


    private TaiKhoan taiKhoan;


    private Date ngay_chinh_sua;
//
//    private Long id_nguoi_chinh_sua;
}
