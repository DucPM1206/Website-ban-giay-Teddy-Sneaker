package com.project.sneaker.entity.dotgiamgia;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "dot_giam_gia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
