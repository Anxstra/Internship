package com.anxstra.services;

import com.anxstra.entities.Position;
import com.anxstra.exceptions.PositionNotFoundException;
import com.anxstra.repositories.PositionRepository;

import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.POSITION_NOT_FOUND;

public class PositionService implements Service<Integer, Position> {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position findById(Integer id) {
        return positionRepository.getById(id).orElseThrow(() ->
                new PositionNotFoundException(POSITION_NOT_FOUND.formatted(id)));
    }

    public List<Position> findAll() {
        return positionRepository.getAll();
    }

    public Position save(Position position) {
        positionRepository.save(position);
        return position;
    }

    public Position update(Position position) {
        positionRepository.update(position);
        return position;
    }

    public void delete(Position position) {
        positionRepository.deleteById(position.getId());
    }
}
