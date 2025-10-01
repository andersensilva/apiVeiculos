package com.wswork.apiVeiculos.service;

import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.repository.CarroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
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
        carro.setTimestamp_cadastro(System.currentTimeMillis());
        return carroRepository.save(carro);
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}

