package com.project.sneaker.repository.admin;

import com.project.sneaker.entity.admin.kich_co;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kichcoRepo extends JpaRepository<kich_co, Long> {
}
