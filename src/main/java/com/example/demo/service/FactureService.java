package com.example.demo.service;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.FactureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ClientMapper clientMapper;

	private FactureDTO toDTO(Facture f) {
        FactureDTO factureDTO = new FactureDTO();
        factureDTO.setId(f.getId());
        factureDTO.setClient(clientMapper.map(f.getClient()));
        factureDTO.setLigneFactures(f.getLigneFactures().stream().map(this::mapLigneFacture).collect(toList()));
        return factureDTO;
    }

    private LigneFactureDTO mapLigneFacture(LigneFacture ligneFacture) {
    	LigneFactureDTO ligneFactureDTO = new LigneFactureDTO();
        ligneFactureDTO.setDesignation(ligneFacture.getArticle().getLibelle());
        ligneFactureDTO.setQuantite(ligneFacture.getQuantite());
        ligneFactureDTO.setPrixUnitaire(ligneFacture.getArticle().getPrix());
        return ligneFactureDTO;
    }
	
    public List<FactureDTO> findAllFactures() {
		List<Facture> FacturefindAll = factureRepository.findAll();
        return FacturefindAll.stream().map(this::toDTO).collect(toList());
    }

    public FactureDTO findById(Long id) {
        return factureRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() ->
                        new IllegalArgumentException("Facture inconnu " + id)
                );
    }

    public List<FactureDTO> findAllByIdClient(Long id) {
        return factureRepository.findAllByClientId(id).stream().map(this::toDTO).collect(toList());
    }
	
}
