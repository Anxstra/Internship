package com.anxstra.services;

import com.anxstra.entities.Cabinet;
import com.anxstra.exceptions.CabinetNotFoundException;
import com.anxstra.repositories.CabinetRepository;

import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.CABINET_NOT_FOUND;

public class CabinetService implements Service<Integer, Cabinet> {

    private final CabinetRepository cabinetRepository;

    public CabinetService(CabinetRepository cabinetRepository) {
        this.cabinetRepository = cabinetRepository;
    }

    @Override
    public Cabinet findById(Integer id) {
        return cabinetRepository.getById(id).orElseThrow(() ->
                new CabinetNotFoundException(CABINET_NOT_FOUND.formatted(id)));
    }

    @Override
    public List<Cabinet> findAll() {
        return cabinetRepository.getAll();
    }

    @Override
    public Cabinet save(Cabinet cabinet) {
        cabinetRepository.save(cabinet);
        return cabinet;
    }

    @Override
    public Cabinet update(Cabinet cabinet) {
        cabinetRepository.update(cabinet);
        return cabinet;
    }

    @Override
    public void delete(Cabinet cabinet) {
        cabinetRepository.deleteById(cabinet.getId());
    }
}
