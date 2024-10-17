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
@Controller
@RestController
public class DotGiamGiaController {
    @Autowired
    DotGiamGiaRepo dotGiamGiaRepo;

    @Autowired
    TaiKhoanRepo taiKhoanRepo;




    @GetMapping("/list")
    public List<DotGiamGia> hienThi() {
        return dotGiamGiaRepo.findAll();
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
