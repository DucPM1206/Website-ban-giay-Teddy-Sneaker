package com.project.sneaker.controller;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaReponse;
import com.project.sneaker.entity.dotgiamgia.DotGiamGiaRequets;
import com.project.sneaker.entity.dotgiamgia.TaiKhoan;
import com.project.sneaker.repository.DotGiamGiaRepo;
import com.project.sneaker.repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DotGiamGiaController {
    @Autowired
    DotGiamGiaRepo dotGiamGiaRepo;

    @Autowired
    TaiKhoanRepo taiKhoanRepo;

    @GetMapping("/list")
    public List<DotGiamGiaReponse> hienThi() {
        return dotGiamGiaRepo.getAll();
    }

    @GetMapping("/phantrang")
    public List<DotGiamGiaReponse> trang(Pageable pageable) {
        return dotGiamGiaRepo.phantrang(pageable).getContent();
    }

    @PostMapping("/add")
    public String add(@RequestBody DotGiamGiaRequets requets) {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setTenDot(requets.getTenDot());
        dotGiamGia.setGiaTri(requets.getGiaTri());
        dotGiamGia.setLoaiGiamGia(requets.getLoaiGiamGia());
        dotGiamGia.setTrangThai(requets.getTrangThai());
        dotGiamGia.setThoiGianBatDau(requets.getThoiGianBatDau());
        dotGiamGia.setThoiGianKetThuc(requets.getThoiGianKetThuc());
        dotGiamGia.setNgayTao(requets.getNgayTao());
        dotGiamGia.setTaiKhoan(requets.getTaiKhoan());
        DotGiamGia dgg = dotGiamGiaRepo.save(dotGiamGia);
        if (dgg == null) {
            return "them ko thanh cong";
        }
        return "them thanh cong";
    }

    @PutMapping("/update")
    public String update(@RequestBody DotGiamGiaRequets requets) {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setId(requets.getId());
        dotGiamGia.setTenDot(requets.getTenDot());
        dotGiamGia.setGiaTri(requets.getGiaTri());
        dotGiamGia.setLoaiGiamGia(requets.getLoaiGiamGia());
        dotGiamGia.setTrangThai(requets.getTrangThai());
        dotGiamGia.setThoiGianBatDau(requets.getThoiGianBatDau());
        dotGiamGia.setThoiGianKetThuc(requets.getThoiGianKetThuc());
        dotGiamGia.setNgayTao(requets.getNgayTao());
        dotGiamGia.setTaiKhoan(requets.getTaiKhoan());
        DotGiamGia dgg = dotGiamGiaRepo.save(dotGiamGia);
        if (dgg == null) {
            return "sua ko thanh cong";
        }
        return "sua thanh cong";
    }


    @GetMapping("/detail")
    public DotGiamGia detail(@RequestParam(value = "id", defaultValue = "1") long id) {
        return dotGiamGiaRepo.findById(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        dotGiamGiaRepo.deleteById(id);
        return "xoa thanh cong";
    }

    //    @DeleteMapping("/delete/tendot/{tenDot}")
//    public String deleteTenDot(@PathVariable(value = "tenDot") String tenDot) {
//        dotGiamGiaRepo.deleteByTenDot(tenDot);
//        return "Xóa thành công";
//    }
    @GetMapping("/search/byId")
    public DotGiamGiaReponse searchById(@RequestParam long id) {
        return dotGiamGiaRepo.findById1(id);
    }

    @GetMapping("/search/byName")
    public List<DotGiamGiaReponse> searchByName(@RequestParam String tenDot) {
      return dotGiamGiaRepo.findByTenDot(tenDot);

    }
}
