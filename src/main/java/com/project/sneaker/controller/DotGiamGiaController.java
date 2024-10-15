package com.project.sneaker.controller;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse;
import com.project.sneaker.entity.dotgiamgia.TaiKhoan;
import com.project.sneaker.repository.DotGiamGiaRepo;
import com.project.sneaker.repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DotGiamGiaController {
    @Autowired
    DotGiamGiaRepo dotGiamGiaRepo;

    @Autowired
    TaiKhoanRepo taiKhoanRepo;

    @GetMapping("/list")
    public List<DotGiamGiaReponse> hienThi(){
        return dotGiamGiaRepo.getAll();
    }

    @GetMapping("/phantrang")
   public List<DotGiamGiaReponse> trang(Pageable pageable){
        return dotGiamGiaRepo.phantrang(pageable).getContent();
    }
}
