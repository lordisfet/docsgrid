package com.example.docsgrid.repository;

import com.example.docsgrid.entity.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends IEntity> extends JpaRepository<T, Long> {
}
