package com.project.sneaker.entity.admin;

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
    private Long id;
    private String ma_sanpham;
    private String ten_sanpham;
    @ManyToOne
    @JoinColumn(name = "id_thuonghieu")
    private thuong_hieu id_thuonghieu;
    @ManyToOne
    @JoinColumn(name = "id_kieudang")
    private kieu_dang id_kieudang;
    @ManyToOne
    @JoinColumn(name = "id_chatlieu")
    private chat_lieu id_chatlieu;
    private Double gia_goc;
    private Double gia_khuyen_mai;
    private Integer so_luong;
    private String mo_ta;
    private Boolean trang_thai;
    private Date ngay_tao;
    private Date ngay_chinh_sua;
}
