package com.collage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.Entity.Assignment;
import com.collage.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
}
