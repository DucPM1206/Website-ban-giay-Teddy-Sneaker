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
        model.addAttribute("list", dotGiamGiaRepo.findAll());
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
//        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String formUpdate(@PathVariable Long id, Model m) {
        DotGiamGia dotGiamGia= dggService.getById(id);
        m.addAttribute("dgg",dotGiamGia);
//        m.addAttribute("list",dotGiamGiaRepo.findAll());
        return "dotgiamgia/update";
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateDgg(@RequestBody DotGiamGia dotGiamGia, @PathVariable Long id) {
        dggService.updateDgg(dotGiamGia, id);
        return ResponseEntity.ok("Sửa dot giam gia thành công!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteThuonghieu(@PathVariable long id) {
        dggService.deleteDgg(id);
        return ResponseEntity.ok("Xóa nhãn hiệu thành công!");
    }

}
