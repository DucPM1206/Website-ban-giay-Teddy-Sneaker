package com.project.sneaker.service;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.repository.DotGiamGiaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DotGiamGiaService {
    @Autowired
    private DotGiamGiaRepo dotGiamGiaRepo;

    //them dot giam gia

    public DotGiamGia addThuonghieu(DotGiamGia dotGiamGia) {
        dotGiamGia.setNgay_tao(new Date(System.currentTimeMillis()));
        dotGiamGiaRepo.save(dotGiamGia);
        return dotGiamGia;
    }

    public void updateDgg(DotGiamGia dotGiamGia, Long id){
        Optional<DotGiamGia> th = dotGiamGiaRepo.findById(id);
        DotGiamGia rs = th.get();
        rs.setTen_dot(dotGiamGia.getTen_dot());
        rs.setGia_tri(dotGiamGia.getGia_tri());
        rs.setLoai_giam_gia(dotGiamGia.getLoai_giam_gia());
        rs.setGia_tri_giam_toi_da(dotGiamGia.getGia_tri_giam_toi_da());
        rs.setTrang_thai(dotGiamGia.getTrang_thai());
        rs.setThoi_gian_bat_dau(dotGiamGia.getThoi_gian_bat_dau());
        rs.setThoi_gian_ket_thuc(new Date(System.currentTimeMillis()));
        try {
            dotGiamGiaRepo.save(rs);
        } catch (Exception e){
            throw new IllegalArgumentException("Lỗi chỉnh sửa");
        }
    }

    public void deleteDgg(Long id) {
        Optional<DotGiamGia> th = dotGiamGiaRepo.findById(id);
        try {
            dotGiamGiaRepo.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Lỗi xóa");
        }
    }

}
