package com.collage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.Entity.Writer;

public interface WriterRepository extends JpaRepository<Writer, String> {

}
