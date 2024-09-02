package com.techtraveller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtraveller.Entity.BookGuide;

public interface BookGuideRepository extends JpaRepository<BookGuide, String> {

}
