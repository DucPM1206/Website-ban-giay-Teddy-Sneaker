package com.project.sneaker.entity.sanpham;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "san_pham")
public class san_pham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ma_sanpham;
    private String ten_sanpham;
    private long id_thuonghieu;
    private long id_kieudang;
    private long id_chatlieu;
    private double gia_goc;
    private Double gia_khuyen_mai;
    private int so_luong;
    private String mo_ta;
    private boolean trang_thai;
    private Date ngay_tao;
    private long id_nguoitao;
    private Date ngay_chinh_sua;
    private Long id_nguoichinhsua;
}
