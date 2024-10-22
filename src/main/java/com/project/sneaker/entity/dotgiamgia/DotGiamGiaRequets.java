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


    private String ten_dot ;


    private Double gia_tri ;


    private String loai_giam_gia ;


    private Double gia_tri_giam_toi_da ;


    private Boolean trang_thai ;


    private Date thoi_gian_bat_dau ;


    private Date thoi_gian_ket_thuc ;


    private Date  ngay_tao ;

}
