package com.wswork.apiVeiculos.service;

import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.entity.Marca;
import com.wswork.apiVeiculos.repository.CarroRepository;
import com.wswork.apiVeiculos.repository.MarcaRepository;
import com.wswork.apiVeiculos.repository.ModeloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    ModeloRepository modeloRepository;
    @Autowired
    CarroRepository carroRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public long countModelosVinculados(Long marcaId) {
        return modeloRepository.countByMarcaId(marcaId);
    }

    public Optional<Marca> findById(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Transactional
    public void delete(Long id, boolean cascade) {
        try {
            long modelosCount = modeloRepository.countByMarcaId(id);
            if (modelosCount > 0) {
                if (cascade) {
                    // Deleta todos os carros vinculados a modelos dessa marca
                    List<Long> modeloIds = modeloRepository.findIdsByMarcaId(id);
                    modeloIds.forEach(modeloId -> carroRepository.deleteByModeloId(modeloId));
                    // Deleta os modelos
                    modeloRepository.deleteByMarcaId(id);
                } else {
                    throw new DataIntegrityViolationException(
                            "Existem modelos vinculados a essa marca. Use cascade=true para excluir tudo.");
                }
            }

            marcaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
