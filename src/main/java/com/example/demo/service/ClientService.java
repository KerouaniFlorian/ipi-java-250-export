package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
		clientDTO.setId(client.getId());
		clientDTO.setNom(client.getNom());
		clientDTO.setPrenom(client.getPrenom());
		clientDTO.setAge(client.getAge());
		return clientDTO;
	}
	
	public ClientDTO findById(Long id) {
		
		return clientRepository.findById(id).map(this::toDTO).orElseThrow(() -> new IllegalArgumentException("Client inconnu " + id));
		
	}
}
