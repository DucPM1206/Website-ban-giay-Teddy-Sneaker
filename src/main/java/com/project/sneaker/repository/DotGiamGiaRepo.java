package com.project.sneaker.repository;

import com.project.sneaker.entity.dotgiamgia.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DotGiamGiaRepo extends JpaRepository<DotGiamGia,Long> {
}
