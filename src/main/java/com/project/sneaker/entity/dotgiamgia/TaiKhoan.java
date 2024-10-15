package com.project.sneaker.entity.dotgiamgia;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tai_khoan ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ho_ten;

}
