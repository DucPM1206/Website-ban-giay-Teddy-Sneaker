package com.project.sneaker.repository;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM dot_giam_gia d WHERE ten_dot = :tenDot",nativeQuery = true)
//    void deleteByTenDot(String tenDot);

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
            dgg.taiKhoan.ho_ten)
            from DotGiamGia dgg
            where dgg.id = :id
            """)
    DotGiamGiaReponse findById1(@Param("id") long id);

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
            dgg.taiKhoan.ho_ten)
            from DotGiamGia dgg
            where dgg.tenDot like %:tenDot%
            """)
    List<DotGiamGiaReponse> findByTenDot(@Param("tenDot") String tenDot);
}
