package com.project.sneaker.repository;

import com.project.sneaker.entity.dotgiamgia.TaiKhoan;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepo extends JpaRepository<TaiKhoan,Long> {
}
