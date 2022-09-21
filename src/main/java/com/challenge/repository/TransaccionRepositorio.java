package com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Transaccion;

@Repository
public interface TransaccionRepositorio extends JpaRepository<Transaccion, Long> {
}