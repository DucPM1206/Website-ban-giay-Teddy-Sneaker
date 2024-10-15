package com.project.sneaker.repository;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DotGiamGiaRepo extends JpaRepository<DotGiamGia, Long> {

    @Query("""
            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
            (dgg.id,
            dgg.tenDot,
            dgg.giaTri,
            dgg.loaiGiamGia,         
                 
            dgg.trangThai,
            dgg.thoiGianBatDau,
            dgg.thoiGianKetThuc,
            dgg.ngayTao,
            dgg.taiKhoan.ho_ten               
            )
            from  DotGiamGia dgg
            """)
    List<DotGiamGiaReponse> getAll();

    @Query("""
            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
            (dgg.id,
            dgg.tenDot,
            dgg.giaTri,
            dgg.loaiGiamGia,         
                 
            dgg.trangThai,
            dgg.thoiGianBatDau,
            dgg.thoiGianKetThuc,
            dgg.ngayTao,
            dgg.taiKhoan.ho_ten               
            )
            from  DotGiamGia dgg
            """)
    Page<DotGiamGiaReponse> phantrang(Pageable pageable);
}
