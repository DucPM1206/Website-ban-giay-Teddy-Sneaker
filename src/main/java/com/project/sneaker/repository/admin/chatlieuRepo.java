package com.project.sneaker.repository.admin;

import com.project.sneaker.entity.admin.chat_lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chatlieuRepo extends JpaRepository<chat_lieu, Long> {
}
