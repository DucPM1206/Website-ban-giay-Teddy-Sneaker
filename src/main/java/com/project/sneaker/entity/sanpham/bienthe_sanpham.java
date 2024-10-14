package com.project.sneaker.entity.sanpham;

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
    private long id;
    private long id_sanpham;
    private Long id_hinhanh;
    private long id_mausac;
    private long id_kichco;
    private int so_luong;
}
