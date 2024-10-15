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

    @Column(name = "ten_dot")
    private String tenDot;

    @Column(name = "gia_tri")
    private double giaTri;

    @Column(name = "loai_giam_gia")
    private String loaiGiamGia;

//    @Column(name = "gia_tri_giam_toi_da")
//    private double giaTriGiamToiDa;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "thoi_gian_bat_dau")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thoiGianBatDau;

    @Column(name = "thoi_gian_ket_thuc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thoiGianKetThuc;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  ngayTao;

    @ManyToOne
    @JoinColumn(name = "id_nguoitao",referencedColumnName = "id")
    private TaiKhoan taiKhoan;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date ngay_chinh_sua;

//    private Long id_nguoi_chinh_sua;




}
