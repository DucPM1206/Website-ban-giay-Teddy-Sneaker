package com.project.sneaker.repository.admin;

import com.project.sneaker.entity.admin.thuong_hieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface thuonghieuRepo extends JpaRepository<thuong_hieu, Long> {
}
