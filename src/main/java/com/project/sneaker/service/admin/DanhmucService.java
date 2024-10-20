package com.project.sneaker.service.admin;

import com.project.sneaker.entity.admin.*;
import com.project.sneaker.errorcontrol.InternalServerException;
import com.project.sneaker.errorcontrol.NotFoundException;
import com.project.sneaker.repository.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DanhmucService {
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

    //Ra danh sách
    public List<chat_lieu> listChatlieu() {
        return clRepo.findAll();
    }

    public List<kich_co> listKichco() {
        return kcRepo.findAll();
    }

    public List<kieu_dang> listKieudang() {
        return kdRepo.findAll();
    }

    public List<mau_sac> listMausac() {
        return msRepo.findAll();
    }

    public List<thuong_hieu> listThuonghieu() {
        return thRepo.findAll();
    }

    //Thêm danh mục
    public chat_lieu addChatlieu(chat_lieu chatlieu) {
        chatlieu.setNgay_tao(new Date(System.currentTimeMillis()));
        clRepo.save(chatlieu);
        return chatlieu;
    }

    public kich_co addKichco(kich_co kichco) {
        kichco.setNgay_tao(new Date(System.currentTimeMillis()));
        kcRepo.save(kichco);
        return kichco;
    }

    public kieu_dang addKieudang(kieu_dang kieudang) {
        kieudang.setNgay_tao(new Date(System.currentTimeMillis()));
        kdRepo.save(kieudang);
        return kieudang;
    }

    public mau_sac addMausac(mau_sac mausac) {
        mausac.setNgay_tao(new Date(System.currentTimeMillis()));
        msRepo.save(mausac);
        return mausac;
    }

    public thuong_hieu addThuonghieu(thuong_hieu thuonghieu) {
        thuonghieu.setNgay_tao(new Date(System.currentTimeMillis()));
        thRepo.save(thuonghieu);
        return thuonghieu;
    }

    //Cập nhật danh mục
    public void updateChatlieu(chat_lieu chatlieu, Long id){
        Optional<chat_lieu> cl = clRepo.findById(id);
        chat_lieu rs = cl.get();
        rs.setTen_chatlieu(chatlieu.getTen_chatlieu());
        rs.setNgay_chinh_sua(new Date(System.currentTimeMillis()));
        try{
            clRepo.save(rs);
        } catch (Exception e){
            throw new InternalServerException("Lỗi chỉnh sửa");
        }
    }

    public void updateKichco(kich_co kichco, Long id){
        Optional<kich_co> kc = kcRepo.findById(id);
        kich_co rs = kc.get();
        rs.setKichco(kichco.getKichco());
        rs.setNgay_chinh_sua(new Date(System.currentTimeMillis()));
        try {
            kcRepo.save(rs);
        } catch (Exception e){
            throw new InternalServerException("Lỗi chỉnh sửa");
        }
    }

    public void updateKieudang(kieu_dang kieudang, Long id){
        Optional<kieu_dang> kd = kdRepo.findById(id);
        kieu_dang rs = kd.get();
        rs.setTen_kieudang(kieudang.getTen_kieudang());
        rs.setNgay_chinh_sua(new Date(System.currentTimeMillis()));
        try {
            kdRepo.save(rs);
        } catch (Exception e){
            throw new InternalServerException("Lỗi chỉnh sửa");
        }
    }

    public void updateMausac(mau_sac mausac, Long id){
        Optional<mau_sac> ms = msRepo.findById(id);
        mau_sac rs = ms.get();
        rs.setTen_mausac(mausac.getTen_mausac());
        rs.setNgay_chinh_sua(new Date(System.currentTimeMillis()));
        try {
            msRepo.save(rs);
        } catch (Exception e){
            throw new InternalServerException("Lỗi chỉnh sửa");
        }
    }

    public void updateThuonghieu(thuong_hieu thuonghieu, Long id){
        Optional<thuong_hieu> th = thRepo.findById(id);
        thuong_hieu rs = th.get();
        rs.setTen_thuonghieu(thuonghieu.getTen_thuonghieu());
        rs.setTrang_thai(thuonghieu.getTrang_thai());
        rs.setNgay_chinh_sua(new Date(System.currentTimeMillis()));
        try {
            thRepo.save(rs);
        } catch (Exception e){
            throw new InternalServerException("Lỗi chỉnh sửa");
        }
    }

    //Xóa danh mục
    public void deleteChatlieu(Long id) {
        Optional<chat_lieu> cl = clRepo.findById(id);
        try {
            clRepo.deleteById(id);
        } catch (Exception e){
            throw new InternalServerException("Lỗi xóa");
        }
    }

    public void deleteKichco(Long id) {
        Optional<kich_co> kc = kcRepo.findById(id);
        try {
            kcRepo.deleteById(id);
        } catch (Exception e){
            throw new InternalServerException("Lỗi xóa");
        }
    }

    public void deleteKieudang(Long id) {
        Optional<kieu_dang> kd = kdRepo.findById(id);
        try {
            kdRepo.deleteById(id);
        } catch (Exception e){
            throw new InternalServerException("Lỗi xóa");
        }
    }

    public void deleteMausac(Long id) {
        Optional<mau_sac> ms = msRepo.findById(id);
        try {
            msRepo.deleteById(id);
        } catch (Exception e){
            throw new InternalServerException("Lỗi xóa");
        }
    }

    public void deleteThuonghieu(Long id) {
        Optional<thuong_hieu> th = thRepo.findById(id);
        try {
            thRepo.deleteById(id);
        } catch (Exception e){
            throw new InternalServerException("Lỗi xóa");
        }
    }

    //Tìm kiếm theo id
    public chat_lieu getChatlieuById(Long id) {
        Optional<chat_lieu> cl = clRepo.findById(id);
        if (cl.isEmpty()) {
            throw new NotFoundException("Dữ liệu không tồn tại");
        }
        return cl.get();
    }

    public kich_co getKichcoById(Long id) {
        Optional<kich_co> kc = kcRepo.findById(id);
        if (kc.isEmpty()) {
            throw new NotFoundException("Dữ liệu không tồn tại");
        }
        return kc.get();
    }

    public kieu_dang getKieudangById(Long id) {
        Optional<kieu_dang> kd = kdRepo.findById(id);
        if (kd.isEmpty()) {
            throw new NotFoundException("Dữ liệu không tồn tại");
        }
        return kd.get();
    }

    public mau_sac getMausacById(Long id) {
        Optional<mau_sac> ms = msRepo.findById(id);
        if (ms.isEmpty()) {
            throw new NotFoundException("Dữ liệu không tồn tại");
        }
        return ms.get();
    }

    public thuong_hieu getThuonghieuById(Long id) {
        Optional<thuong_hieu> th = thRepo.findById(id);
        if (th.isEmpty()) {
            throw new NotFoundException("Dữ liệu không tồn tại");
        }
        return th.get();
    }
}
