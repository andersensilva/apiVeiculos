package com.wswork.apiVeiculos.repository;

import com.wswork.apiVeiculos.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}

