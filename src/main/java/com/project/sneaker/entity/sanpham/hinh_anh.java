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
@Table(name = "hinh_anh")
public class hinh_anh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hinh_anh;
}
