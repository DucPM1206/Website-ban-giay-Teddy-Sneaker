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
@Table(name = "kich_co")
public class kich_co {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String kichco;
}
