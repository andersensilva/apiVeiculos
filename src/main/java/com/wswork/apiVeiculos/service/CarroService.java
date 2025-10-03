package com.wswork.apiVeiculos.service;

import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.entity.Marca;
import com.wswork.apiVeiculos.entity.Modelo;
import com.wswork.apiVeiculos.repository.CarroRepository;
import com.wswork.apiVeiculos.repository.MarcaRepository;
import com.wswork.apiVeiculos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    ModeloRepository modeloRepository;

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Optional<Carro> findById(Long id) {
        return carroRepository.findById(id);
    }

    public Carro save(Carro carro) {
        Modelo modeloExistente = modeloRepository.findById(carro.getModelo().getId())
                .orElseThrow(() -> new RuntimeException("Modelo não encontrada"));
        carro.setModelo(modeloExistente);
        carro.setTimestamp_cadastro(System.currentTimeMillis());
        return carroRepository.save(carro);
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}

