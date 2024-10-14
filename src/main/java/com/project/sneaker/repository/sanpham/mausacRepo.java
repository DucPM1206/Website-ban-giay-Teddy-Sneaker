package com.project.sneaker.repository.sanpham;

import com.project.sneaker.entity.sanpham.mau_sac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mausacRepo extends JpaRepository<mau_sac, Long> {
}
