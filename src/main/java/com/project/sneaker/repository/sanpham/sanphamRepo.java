package com.project.sneaker.repository.sanpham;

import com.project.sneaker.entity.sanpham.san_pham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sanphamRepo extends JpaRepository<san_pham, Long> {
}
