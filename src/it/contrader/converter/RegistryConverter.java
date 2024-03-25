package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;

/**
 * The UserConverter class provides utility methods to convert between User and UserDTO objects.
 * This conversion is useful when the application needs to convert domain-specific objects (User)
 * to data transfer objects (UserDTO) and vice versa.
 */
public class RegistryConverter {

    /**
     * Converts a Registry object to a RegistryDTO object.
     *
     * @param registry the Registry object to be converted.
     * @return a RegistryDTO object that represents the given User object. Returns null if the input Registry object is null.
     */
    public RegistryDTO toDTO(Registry registry) {
        return registry != null ? new RegistryDTO(registry.getId(), registry.getName(), registry.getSurname(), registry.getBirthDate(),registry.getEmail(),registry.getNationality(),registry.getCity(),registry.getAddress(),registry.getCf(),registry.getIdUser()) : null;
    }

    /**
     * Converts a RegistryDTO object to a User object.
     *
     * @param registryDTO the RegistryDTO object to be converted.
     * @return a Registry object that represents the given RegistryDTO object. Returns null if the input RegistryDTO object is null.
     */
    public Registry toEntity(RegistryDTO registryDTO) {
        return registryDTO!= null ? new Registry(registryDTO.getId(), registryDTO.getName(), registryDTO.getSurname(), registryDTO.getBirthDate(),registryDTO.getEmail(),registryDTO.getNationality(),registryDTO.getCity(),registryDTO.getAddress(),registryDTO.getCf(),registryDTO.getIdUser()) : null;
    }

    /**
     * Converts a list of User objects to a list of UserDTO objects.
     *
     * @param registryList the list of User objects to be converted.
     * @return a list of UserDTO objects that represents the given list of Registry objects. Returns null if the input list is null.
     */
    public List<RegistryDTO> toDTOList(List<Registry> registryList) {
        List<RegistryDTO> registryDTOList = new ArrayList<>();
        if(registryList != null) {
            for (Registry registry : registryList) {
                registryDTOList.add(toDTO(registry));
            }
            return registryDTOList;
        } else return null;
    }


}