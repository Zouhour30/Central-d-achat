package com.example.testspring.Repository;

import com.example.testspring.Entities.Category;
import com.example.testspring.Entities.FileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileResponseRepository extends JpaRepository<FileResponse,Integer> {
}
