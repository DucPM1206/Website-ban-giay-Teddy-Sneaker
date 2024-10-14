package com.project.sneaker.repository.sanpham;

import com.project.sneaker.entity.sanpham.kieu_dang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kieudangRepo extends JpaRepository<kieu_dang, Long> {
}
