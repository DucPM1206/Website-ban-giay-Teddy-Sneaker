package com.project.sneaker.controller.admin;

import com.project.sneaker.entity.sanpham.*;
import com.project.sneaker.repository.sanpham.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
public class DanhmucController {

    @Autowired
    private chatlieuRepo clRepo;

    @Autowired
    private kichcoRepo kcRepo;

    @Autowired
    private kieudangRepo kdRepo;

    @Autowired
    private mausacRepo msRepo;

    @Autowired
    private thuonghieuRepo thRepo;

    //Test json bằng postman
    @GetMapping("/danhmuc/chatlieu")
    public List<chat_lieu> chatlieu(Model model) {
        return clRepo.findAll();
    }

    @GetMapping("/danhmuc/kichco")
    public List<kich_co> kichco(Model model) {
        return kcRepo.findAll();
    }

    @GetMapping("/danhmuc/kieudang")
    public List<kieu_dang> kieudang(Model model) {
        return kdRepo.findAll();
    }

    @GetMapping("/danhmuc/mausac")
    public List<mau_sac> mausac(Model model) {
        return msRepo.findAll();
    }

    @GetMapping("/danhmuc/thuonghieu")
    public List<thuong_hieu> thuonghieu(Model model) {
        return thRepo.findAll();
    }
}
