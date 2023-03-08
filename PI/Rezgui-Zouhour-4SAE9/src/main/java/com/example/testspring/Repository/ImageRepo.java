package com.example.testspring.Repository;

import com.example.testspring.Entities.Category;
import com.example.testspring.Entities.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<ImageModel,Long> {
}
