package org.example.superapiv1.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.superapiv1.DTO.FranchiseDTO;
import org.example.superapiv1.entities.Franchise;
import org.example.superapiv1.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;


    @Transactional(readOnly = true)
    public List<FranchiseDTO> findAll() {
        List<Franchise> franchises = franchiseRepository.findAll();
        return franchises.stream().map(FranchiseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public FranchiseDTO findById(Long id) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franquia"));
        return new FranchiseDTO(franchise);
    }

    @Transactional
    public FranchiseDTO create(FranchiseDTO franchiseDTO) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseDTO.getName());
        franchise = franchiseRepository.save(franchise);
        return new FranchiseDTO(franchise);
    }

    @Transactional
    public FranchiseDTO update(Long id, FranchiseDTO franchiseDTO) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franquia"));
        franchise.setName(franchiseDTO.getName());
        franchise = franchiseRepository.save(franchise);
        return new FranchiseDTO(franchise);
    }

    @Transactional
    public void delete(Long id) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franquia"));
        franchiseRepository.delete(franchise);
    }
}
