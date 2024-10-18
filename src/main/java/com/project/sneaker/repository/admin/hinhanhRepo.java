package com.project.sneaker.repository.admin;

import com.project.sneaker.entity.admin.hinh_anh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hinhanhRepo extends JpaRepository<hinh_anh, Long> {
}
