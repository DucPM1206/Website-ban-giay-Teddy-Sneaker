package com.project.sneaker.repository.sanpham;

import com.project.sneaker.entity.sanpham.hinh_anh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hinhanhRepo extends JpaRepository<hinh_anh, Long> {
}
