package com.project.sneaker.entity.dotgiamgia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotGiamGiaReponse {

    private Long id;

    private String tenDot;

    private double giaTri;

    private String loaiGiamGia;

//    private double giaTriGiamToiDa;

    private Boolean trangThai;


    private Date thoiGianBatDau;

    private Date thoiGianKetThuc;


    private Date ngayTao;


    private String tenNguoiTao;


//    private Date ngay_chinh_sua;

//    private Long id_nguoi_chinh_sua;
}
