package com.project.sneaker.controller.admin;

import com.project.sneaker.entity.admin.*;
import com.project.sneaker.service.admin.DanhmucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class DanhmucController {

    @Autowired
    private DanhmucService dmService;

    @GetMapping("/danhmuc/chatlieu")
    public String listChatlieu(Model model) {
        model.addAttribute("listChatlieu", dmService.listChatlieu());
        return "admin/danhmuc/listchatlieu";
    }

    @GetMapping("/danhmuc/kichco")
    public String listKichco(Model model) {
        model.addAttribute("listKichco", dmService.listKichco());
        return "admin/danhmuc/listkichco";
    }

    @GetMapping("/danhmuc/kieudang")
    public String listKieudang(Model model) {
        model.addAttribute("listKieudang", dmService.listKieudang());
        return "admin/danhmuc/listkieudang";
    }

    @GetMapping("/danhmuc/mausac")
    public String listMausac(Model model) {
        model.addAttribute("listMausac", dmService.listMausac());
        return "admin/danhmuc/listmausac";
    }

    @GetMapping("/danhmuc/thuonghieu")
    public String listThuonghieu(Model model) {
        model.addAttribute("listThuonghieu", dmService.listThuonghieu());
        return "admin/danhmuc/listthuonghieu";
    }

    @PostMapping("/danhmuc/thuonghieu")
    public ResponseEntity<Object> addThuonghieu(@RequestBody thuong_hieu thuong_hieu) {
        dmService.addThuonghieu(thuong_hieu);
        return ResponseEntity.ok("Thêm thành công");
    }

    @PutMapping("/danhmuc/thuonghieu/{id}")
    public ResponseEntity<Object> updateThuonghieu(@RequestBody thuong_hieu thuong_hieu, @PathVariable Long id) {
        dmService.updateThuonghieu(thuong_hieu, id);
        return ResponseEntity.ok("Sửa nhãn hiệu thành công!");
    }

    @DeleteMapping("/danhmuc/thuonghieu/{id}")
    public ResponseEntity<Object> deleteThuonghieu(@PathVariable long id) {
        dmService.deleteThuonghieu(id);
        return ResponseEntity.ok("Xóa nhãn hiệu thành công!");
    }

    @GetMapping("/danhmuc/thuonghieu/{id}")
    public ResponseEntity<Object> getThuonghieuById(@PathVariable long id){
        thuong_hieu thuong_hieu = dmService.getThuonghieuById(id);
        return ResponseEntity.ok(thuong_hieu);
    }
}
