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

    //them
    public DotGiamGia addDotGiamGia(DotGiamGia dotGiamGia) {
        dotGiamGia.setNgayTao(new Date(System.currentTimeMillis()));
        dotGiamGiaRepo.save(dotGiamGia);
        return dotGiamGia;
    }
}
