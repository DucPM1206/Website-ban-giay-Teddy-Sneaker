package com.project.sneaker.repository.admin;

import com.project.sneaker.entity.admin.kieu_dang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kieudangRepo extends JpaRepository<kieu_dang, Long> {
}
