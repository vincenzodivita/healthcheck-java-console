package it.contrader.service;

import it.contrader.converter.UrineTestConverter;
import it.contrader.dao.UrineTestDAO;
import it.contrader.dto.UrineTestDTO;

import java.util.List;

public class UrineTestService {

    private final UrineTestDAO urineTestDAO;

    private  final UrineTestConverter urineTestConverter;

    public UrineTestService() {
        this.urineTestDAO = new UrineTestDAO();
        this.urineTestConverter = new UrineTestConverter();
    }

    public List<UrineTestDTO> getAll() {
        return urineTestConverter.toUrineDTOList(urineTestDAO.getAll());
    }

    public List<UrineTestDTO> getAll(int id) {
        return urineTestConverter.toUrineDTOList(urineTestDAO.getAll(id));
    }
    public List<UrineTestDTO> getAllUser(int id) { return urineTestConverter.toUrineDTOList(urineTestDAO.getAllUser(id)); }
    public List<UrineTestDTO> getAllChecked(int id){
        return urineTestConverter.toUrineDTOList(urineTestDAO.getAllChecked(id));
    }

    public boolean validation(UrineTestDTO dto) {
        return urineTestDAO.validation(urineTestConverter.toEntity(dto));
    }

    public boolean insert(UrineTestDTO dto) {
        return urineTestDAO.insert(urineTestConverter.toEntity(dto));
    }

    public UrineTestDTO read(int id) {return urineTestConverter.toUrineDTO(urineTestDAO.read(id));}

    public UrineTestDTO readIdAdmin(int id, int idAdmin) {
        return urineTestConverter.toUrineDTO(urineTestDAO.readIdAdmin(id, idAdmin));
    }

    public UrineTestDTO read_user(int UrineTestId, int UrineTestIdUser) { return urineTestConverter.toUrineDTO(urineTestDAO.read_user(UrineTestId, UrineTestIdUser)); }

    public boolean update(UrineTestDTO dto) {
        return urineTestDAO.update(urineTestConverter.toEntity(dto));
    }

    public boolean delete(int id) {
        return urineTestDAO.delete(id);
    }
}
