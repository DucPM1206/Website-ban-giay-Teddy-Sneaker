package com.project.sneaker.controller;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaRequets;
import com.project.sneaker.entity.dotgiamgia.TaiKhoan;
import com.project.sneaker.repository.DotGiamGiaRepo;
import com.project.sneaker.repository.TaiKhoanRepo;
import com.project.sneaker.service.DotGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DotGiamGiaController {
    @Autowired
    DotGiamGiaRepo dotGiamGiaRepo;

    @Autowired
    TaiKhoanRepo taiKhoanRepo;

    @Autowired
    DotGiamGiaService dggService;

    @GetMapping("/list")
    public String hienThi(Model model) {
        model.addAttribute("dgg", dotGiamGiaRepo.findAll());
        return "dotgiamgia/index";
    }


    @GetMapping("/form-add")
    public String viewAdd(@ModelAttribute("dgg") DotGiamGia dotGiamGia) {
        return "dotgiamgia/create";
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addDotGiamGia(@RequestBody DotGiamGia dotGiamGia) {
        dggService.addThuonghieu(dotGiamGia);
        return ResponseEntity.ok("Thêm thành công");
    }




}
