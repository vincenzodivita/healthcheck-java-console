package it.contrader.service;

import java.util.List;


import it.contrader.converter.RegistryConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.RegistryDAO;
import it.contrader.dao.UserDAO;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.RegistryDTO;
import it.contrader.dto.UserDTO;

/**
 * Service class for User operations.
 * Uses a UserDAO for database operations
 * and a UserConverter for conversion between DTO and Entity objects.
 */
public class RegistryService {

    private final RegistryDAO registryDAO;
    private final RegistryConverter registryConverter;

    /**
     * Constructor to initialize the specific DAO and Converter.
     */
    public RegistryService() {
        this.registryDAO = new RegistryDAO();
        this.registryConverter = new RegistryConverter();
    }

    /**
     * Inserts a new registry into the database.
     * Converts the input DTO to an entity before passing to DAO.
     *
     * @param dto RegistryDTO object to insert.
     * @return boolean representing the operation result.
     */
    public boolean insert(RegistryDTO dto) {
        return registryDAO.insert(registryConverter.toEntity(dto));
    }
    public RegistryDTO read(int id) {
        return registryConverter.toDTO(registryDAO.read(id));
    }
    public boolean update(RegistryDTO dto) {
        return registryDAO.update(registryConverter.toEntity(dto));
    }



    public List<RegistryDTO> getAllPatient(int idAdmin) {
        return registryConverter.toDTOList(registryDAO.getAllPatient(idAdmin));
    }



}