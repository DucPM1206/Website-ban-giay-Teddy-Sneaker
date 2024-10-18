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
@Table(name = "chat_lieu")
public class chat_lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten_chatlieu;
    private Date ngay_tao;
    private Long id_nguoitao;
    private Date ngay_chinh_sua;
    private Long id_nguoichinhsua;
}
