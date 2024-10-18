package com.project.sneaker.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bienthe_sanpham")
public class bienthe_sanpham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_sanpham")
    private san_pham id_sanpham;
    @ManyToOne
    @JoinColumn(name = "id_hinhanh")
    private hinh_anh id_hinhanh;
    @ManyToOne
    @JoinColumn(name = "id_mausac")
    private mau_sac id_mausac;
    @ManyToOne
    @JoinColumn(name = "id_kichco")
    private kich_co id_kichco;
    private Integer so_luong;
}
