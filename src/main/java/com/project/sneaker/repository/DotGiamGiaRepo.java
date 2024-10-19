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

//    @Query("""
//            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
//            (dgg.id,
//            dgg.tenDot,
//            dgg.giaTri,
//            dgg.loaiGiamGia,
//            dgg.giaTriGiamToiDa,
//            dgg.trangThai,
//            dgg.thoiGianBatDau,
//            dgg.thoiGianKetThuc,
//            dgg.ngayTao,
//            dgg.taiKhoan.ho_ten,
//            dgg.ngay_chinh_sua,
//            dgg.id_nguoi_chinh_sua
//
//            )
//            from  DotGiamGia dgg
//            """)
//    List<DotGiamGiaReponse> getAll();


    @Query("""
            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
            (dgg.id,
            dgg.tenDot,
            dgg.giaTri,
            dgg.loaiGiamGia,         
            dgg.giaTriGiamToiDa,
            dgg.trangThai,
            dgg.thoiGianBatDau,
            dgg.thoiGianKetThuc,
            dgg.ngayTao,
            dgg.taiKhoan.ho_ten,
            dgg.ngay_chinh_sua,
            dgg.id_nguoichinhsua 
                            
            )
            from  DotGiamGia dgg
            """)
    Page<DotGiamGiaReponse> phantrang(Pageable pageable);



//    @Query("""
//            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
//            (dgg.id,
//            dgg.tenDot,
//            dgg.giaTri,
//            dgg.loaiGiamGia,
//             dgg.giaTriGiamToiDa,
//            dgg.trangThai,
//            dgg.thoiGianBatDau,
//            dgg.thoiGianKetThuc,
//            dgg.ngayTao,
//            dgg.taiKhoan.ho_ten,
//            dgg.ngay_chinh_sua ,
//            dgg.id_nguoi_chinh_sua
//
//            )
//            from DotGiamGia dgg
//            where dgg.id = :id
//            """)
//    DotGiamGiaReponse findById1(@Param("id") long id);

    //      Tìm theo tenDot
    @Query("""
            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
            (dgg.id,
            dgg.tenDot,
            dgg.giaTri,
            dgg.loaiGiamGia,      dgg.giaTriGiamToiDa,
            dgg.trangThai,
            dgg.thoiGianBatDau,
            dgg.thoiGianKetThuc,
            dgg.ngayTao,
            dgg.taiKhoan.ho_ten,
            dgg.ngay_chinh_sua,
            dgg.id_nguoichinhsua 
       
            )
            from DotGiamGia dgg
            where dgg.tenDot like %:tenDot%
            """)
    List<DotGiamGiaReponse> findByTenDot(@Param("tenDot") String tenDot);

    // Lọc
    @Query("""
            select new com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse
            (dgg.id,
            dgg.tenDot,
            dgg.giaTri,
            dgg.loaiGiamGia,    
            dgg.giaTriGiamToiDa,
            dgg.trangThai,
            dgg.thoiGianBatDau,
            dgg.thoiGianKetThuc,
            dgg.ngayTao,
            dgg.taiKhoan.ho_ten,
            dgg.ngay_chinh_sua ,
            dgg.id_nguoichinhsua 
           
            )
            from DotGiamGia dgg
            where (:tenDot is null or dgg.tenDot like %:tenDot%)
            and (:loaiGiamGia is null or dgg.loaiGiamGia = :loaiGiamGia)
            and (:trangThai is null or dgg.trangThai = :trangThai)
            """)
    List<DotGiamGiaReponse> filter(@Param("tenDot") String tenDot,
                                   @Param("loaiGiamGia") String loaiGiamGia,
                                   @Param("trangThai") Boolean trangThai);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM DotGiamGia d WHERE d.tenDot = :tenDot")
    void deleteByTenDot(@Param("tenDot") String tenDot);


}