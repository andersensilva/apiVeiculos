package com.wswork.apiVeiculos.repository;

import com.wswork.apiVeiculos.entity.Carro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    long countByModeloId(Long modeloId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Carro c WHERE c.modelo.id = :modeloId")
    void deleteByModeloId(Long modeloId);
}

