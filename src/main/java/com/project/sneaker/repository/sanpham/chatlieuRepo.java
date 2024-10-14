package com.project.sneaker.repository.sanpham;

import com.project.sneaker.entity.sanpham.chat_lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chatlieuRepo extends JpaRepository<chat_lieu, Long> {
}
