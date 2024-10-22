package com.project.sneaker.service;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.repository.DotGiamGiaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
}
