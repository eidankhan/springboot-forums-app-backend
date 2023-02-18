package com.sprinboot.jwt.app.repository;

import com.sprinboot.jwt.app.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
