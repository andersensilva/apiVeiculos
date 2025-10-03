package com.wswork.apiVeiculos.repository;

import com.wswork.apiVeiculos.entity.Modelo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    long countByMarcaId(Long marcaId);
    @Query("SELECT m.id FROM Modelo m WHERE m.marca.id = :marcaId")
    List<Long> findIdsByMarcaId(Long marcaId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Modelo m WHERE m.marca.id = :marcaId")
    void deleteByMarcaId(Long marcaId);
}

