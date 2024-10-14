package com.project.sneaker.repository.sanpham;

import com.project.sneaker.entity.sanpham.bienthe_sanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bienthesanphamRepo extends JpaRepository<bienthe_sanpham, Long> {
}
