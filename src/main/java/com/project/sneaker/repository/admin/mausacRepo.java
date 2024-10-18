package com.project.sneaker.repository.admin;

import com.project.sneaker.entity.admin.mau_sac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mausacRepo extends JpaRepository<mau_sac, Long> {
}
