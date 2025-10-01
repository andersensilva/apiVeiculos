package com.wswork.apiVeiculos.repository;

import com.wswork.apiVeiculos.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}

