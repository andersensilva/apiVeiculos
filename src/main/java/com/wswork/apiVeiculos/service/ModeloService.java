package com.wswork.apiVeiculos.service;

import com.wswork.apiVeiculos.entity.Marca;
import com.wswork.apiVeiculos.entity.Modelo;
import com.wswork.apiVeiculos.repository.CarroRepository;
import com.wswork.apiVeiculos.repository.MarcaRepository;
import com.wswork.apiVeiculos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    ModeloRepository modeloRepository;

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    CarroRepository carroRepository;

    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> findById(Long id) {
        return modeloRepository.findById(id);
    }

    public long countCarrosVinculados(Long modeloId) {
        return carroRepository.countByModeloId(modeloId);
    }
    public Modelo save(Modelo modelo) {
        Marca marcaExistente = marcaRepository.findById(modelo.getMarca().getId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        modelo.setMarca(marcaExistente);
        return modeloRepository.save(modelo);
    }

    public void delete(Long id, boolean cascade) {
        try {
            if (cascade) {
                carroRepository.deleteByModeloId(id); // exclui todos os carros vinculados
            }
            modeloRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "Não é possível excluir o modelo, existem carros vinculados.", e
            );
        }
    }
}
