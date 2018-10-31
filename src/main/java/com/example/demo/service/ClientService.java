package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.dto.FactureDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
	@Autowired
	private FactureRepository factureRepository;

    public List<ClientDTO> findAllClients() {
		
		return clientRepository.findAll().stream().map(c-> clientMapper.map(c)).collect(toList());
		
    }
	
	private ClientDTO toDTO(Client client){
		ClientDTO clientDTO = new ClientDTO();
		ClientDTO.setId(client.getId());
		ClientDTO.setNom(client.getNom());
		ClientDTO.setPrenom(client.getPrenom());
		ClientDTO.setAge(client.getAge());
		return clientDTO;
	}
	
	public ClientDTO findById(Long id) {
		
		return clientRepository.findById(id).map(this::toDTO).orElseThrow(() -> new IllegalArgumentException("Client inconnu " + id));
		
	}
}
