package it.contrader.converter;

import it.contrader.dto.UrineTestDTO;
import it.contrader.model.UrineTest;

import java.util.ArrayList;
import java.util.List;

public class UrineTestConverter {

    public UrineTestDTO toUrineDTO(UrineTest urineTest){
        return (urineTest !=null) ? new UrineTestDTO(urineTest.getId(), urineTest.getColor(), urineTest.getPh(), urineTest.getProtein(), urineTest.getHemoglobin(), urineTest.getIdAdmin(), urineTest.getIdUser(), urineTest.getChecked()) : null;
    }

    public UrineTest toEntity(UrineTestDTO urineTestDTO){
        return (urineTestDTO != null) ? new UrineTest(urineTestDTO.getId(), urineTestDTO.getColor(), urineTestDTO.getPh(), urineTestDTO.getProtein(), urineTestDTO.getHemoglobin(), urineTestDTO.getIdAdmin(), urineTestDTO.getIdUser(), urineTestDTO.getChecked()) : null;
    }

public List<UrineTestDTO> toUrineDTOList(List<UrineTest> urineTestList){
        List<UrineTestDTO> urineTestDTOList = new ArrayList<>();
        if(urineTestList != null) {
            for (UrineTest urine : urineTestList) {
                urineTestDTOList.add(toUrineDTO(urine));
            }
            return urineTestDTOList;
        }else return null;

        }
}

