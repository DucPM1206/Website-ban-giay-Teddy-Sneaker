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

    @GetMapping("/phantrang")
    public List<DotGiamGiaReponse> trang(Pageable pageable) {
        return dotGiamGiaRepo.phantrang(pageable).getContent();
    }

    @GetMapping("/form-add")
    public String viewAdd(@ModelAttribute("dgg") DotGiamGia dotGiamGia) {
        return "dotgiamgia/create";
    }

//    @PostMapping("/add")
//    public String add(@RequestBody DotGiamGiaRequets requets) {
////        DotGiamGia dotGiamGia = new DotGiamGia();
////        dotGiamGia.setTenDot(requets.getTenDot());
////        dotGiamGia.setGiaTri(requets.getGiaTri());
////        dotGiamGia.setLoaiGiamGia(requets.getLoaiGiamGia());
////        dotGiamGia.setGiaTriGiamToiDa(requets.getGiaTriGiamToiDa());
////        dotGiamGia.setTrangThai(requets.getTrangThai());
////        dotGiamGia.setThoiGianBatDau(requets.getThoiGianBatDau());
////        dotGiamGia.setThoiGianKetThuc(requets.getThoiGianKetThuc());
////        dotGiamGia.setNgayTao(requets.getNgayTao());
////        dotGiamGia.setTaiKhoan(requets.getTaiKhoan());
////        dotGiamGia.setNgay_chinh_sua(requets.getNgay_chinh_sua());
//////        dotGiamGia.setId_nguoi_chinh_sua(requets.getId_nguoi_chinh_sua());
////        DotGiamGia dgg = dotGiamGiaRepo.save(dotGiamGia);
////        if (dgg == null) {
////            return "them ko thanh cong";
////        }
////        return "them thanh cong";
//
//    }


    //    @PostMapping("/add")
//    public ResponseEntity<Object> createPromotion(@RequestBody DotGiamGia dotGiamGia) {
//
//        return ResponseEntity.ok(dggService.addDotGiamGia(dotGiamGia));
//    }
    @PostMapping("/add")
    public String addPromotion(@ModelAttribute DotGiamGia dotGiamGia) {
        DotGiamGia savedPromotion = dotGiamGiaRepo.save(dotGiamGia);
        return (savedPromotion != null) ? "redirect:/dotgiamgia/list" : "dotgiamgia/create";
    }


    @PutMapping("/update")
    public String update(@RequestBody DotGiamGiaRequets requets) {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setId(requets.getId());
        dotGiamGia.setTenDot(requets.getTenDot());
        dotGiamGia.setGiaTri(requets.getGiaTri());
        dotGiamGia.setLoaiGiamGia(requets.getLoaiGiamGia());
        dotGiamGia.setGiaTriGiamToiDa(requets.getGiaTriGiamToiDa());
        dotGiamGia.setTrangThai(requets.getTrangThai());
        dotGiamGia.setThoiGianBatDau(requets.getThoiGianBatDau());
        dotGiamGia.setThoiGianKetThuc(requets.getThoiGianKetThuc());
        dotGiamGia.setNgayTao(requets.getNgayTao());
        dotGiamGia.setTaiKhoan(requets.getTaiKhoan());
        dotGiamGia.setNgay_chinh_sua(requets.getNgay_chinh_sua());
//        dotGiamGia.setId_nguoi_chinh_sua(requets.getId_nguoi_chinh_sua());
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

    //xóa theo id
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        dotGiamGiaRepo.deleteById(id);
        return "xoa thanh cong";
    }

    // xoa theo tên đợt
    @DeleteMapping("/deleteByTenDot/{tenDot}")
    public String deleteByTenDot(@PathVariable String tenDot) {
        try {
            dotGiamGiaRepo.deleteByTenDot(tenDot);
            return "Xóa thành công đợt có tên: " + tenDot;
        } catch (Exception e) {
            return "Có lỗi xảy ra khi xóa đợt: " + e.getMessage();
        }
    }

    // tìm kiếm theo id
//    @GetMapping("/search/byId")
//    public DotGiamGiaReponse searchById(@RequestParam long id) {
//        return dotGiamGiaRepo.findById(id);
//    }
    // tìm kiếm theo ten
    @GetMapping("/search/byName")
    public List<DotGiamGiaReponse> searchByName(@RequestParam String tenDot) {
        return dotGiamGiaRepo.findByTenDot(tenDot);

    }

    // lọc ten , loại giảm giá , trang thái
    @GetMapping("/loc")
    public List<DotGiamGiaReponse> filter(
            @RequestParam(required = false) String tenDot,
            @RequestParam(required = false) String loaiGiamGia,
            @RequestParam(required = false) Boolean trangThai) {

        return dotGiamGiaRepo.filter(tenDot, loaiGiamGia, trangThai);
    }


}