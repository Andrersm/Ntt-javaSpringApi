package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.FranchiseDTO;
import org.example.superapiv1.entities.Franchise;
import org.example.superapiv1.exception.EntityDeletionException;
import org.example.superapiv1.exception.UnexpectedIdException;
import org.example.superapiv1.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public List<FranchiseDTO> findAll() {
        List<Franchise> listFranchise = franchiseRepository.findAll();
        return listFranchise.stream().map(FranchiseDTO::new).toList();
    }

    public FranchiseDTO findById(Long id) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franquia"));
        return new FranchiseDTO(franchise);
    }

    public FranchiseDTO save(FranchiseDTO franchiseDTO) {
        Franchise franchise;
        if (franchiseDTO.getId() != null) {
            franchise = franchiseRepository.findById(franchiseDTO.getId())
                    .orElseThrow(() -> new UnexpectedIdException("Franquia"));
        } else {
            franchise = new Franchise();
        }
        franchise.setName(franchiseDTO.getName());
        franchise = franchiseRepository.save(franchise);
        return new FranchiseDTO(franchise);
    }

    public void delete(Long id) {
        if (!franchiseRepository.existsById(id)) {
            throw new EntityDeletionException("Ator", id);
        }
        franchiseRepository.deleteById(id);
    }
}
