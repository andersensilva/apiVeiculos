package com.wswork.apiVeiculos.service;

import com.wswork.apiVeiculos.entity.Marca;
import com.wswork.apiVeiculos.entity.Modelo;
import com.wswork.apiVeiculos.repository.MarcaRepository;
import com.wswork.apiVeiculos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    ModeloRepository modeloRepository;

    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> findById(Long id) {
        return modeloRepository.findById(id);
    }

    public Modelo save(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public void delete(Long id) {
        modeloRepository.deleteById(id);
    }
}
