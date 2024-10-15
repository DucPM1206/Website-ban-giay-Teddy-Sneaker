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

    private String ten_dot;

    private double gia_tri;

    private String loai_giam_gia;

//    private double gia_tri_giam_toi_da;

    private Boolean trang_thai;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thoi_gian_bat_dau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thoi_gian_ket_thuc;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngay_tao;

    @ManyToOne
    @JoinColumn(name = "id_nguoitao",referencedColumnName = "id")
    private TaiKhoan taiKhoan;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngay_chinh_sua;

//    private Long id_nguoi_chinh_sua;




}
