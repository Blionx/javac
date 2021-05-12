package com.mercadolibre.pens_luis_bootcamp_final.services;

//import com.google.common.reflect.TypeToken;
//import com.google.protobuf.Api;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.NewPartDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.PartDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.PartResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.exceptions.ApiException;
import com.mercadolibre.pens_luis_bootcamp_final.models.Part;
import com.mercadolibre.pens_luis_bootcamp_final.models.PartRecord;
import com.mercadolibre.pens_luis_bootcamp_final.models.Provider;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.PartRecordRepository;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.PartRepository;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.ProviderRepository;
import com.mercadolibre.pens_luis_bootcamp_final.util.DateMapper;
import com.mercadolibre.pens_luis_bootcamp_final.util.PartMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class PartsServiceImpl implements PartsService{

    private PartRepository repoParts;
    private PartRecordRepository repoPartRecords;
    private PartMapper mapper;
    private ProviderRepository repoProvider;

    public PartsServiceImpl(PartRepository repoParts, PartRecordRepository repoPartRecords,
                            PartMapper mapper, ProviderRepository repoProvider){
        this.repoParts = repoParts;
        this.repoPartRecords = repoPartRecords;
        this.mapper = mapper;
        this.repoProvider = repoProvider;
    }

    // receives controller input and returns the dto response object back to controller
    @Transactional(readOnly=true)
    @Override
    public PartResponseDto getParts(String queryType, String date, String order) throws Exception {
        LocalDate dateLocal = null;
        Integer orderInt = null;
        if (!queryType.equals("C")){
            dateLocal = DateMapper.mappearFecha(date);
            orderInt = validateOrder(order);
        }
        else{
            if (!date.isEmpty() || !order.isEmpty()){
                throw new ApiException(HttpStatus.BAD_REQUEST.name(), "No filters allow with queryType C", HttpStatus.BAD_REQUEST.value());
            }
        }
        List<PartDto> listParts = queryParts(queryType, dateLocal, orderInt);

        if(listParts != null && listParts.isEmpty())
        {
            throw new ApiException(HttpStatus.NOT_FOUND.name(),"404 Not Found", HttpStatus.NOT_FOUND.value());
        }

        return new PartResponseDto(listParts);
    }

    public void updateStock(Part part, Integer newStock){
        Integer currentStock = part.getStock().getQuantity();
        part.getStock().setQuantity(currentStock + newStock);
        repoParts.save(part);
    }

    public Integer updateStock(String partCode, Integer quantity){
        if (quantity<0){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "Negative quantity", HttpStatus.BAD_REQUEST.value());
        }
        Part part = repoParts.findPartByPartCode(partCode).orElse(null);
        if (part == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "No such part exists", HttpStatus.BAD_REQUEST.value());
        }
        Integer currentStock = part.getStock().getQuantity();
        Integer newStock = currentStock + quantity;
        part.getStock().setQuantity(newStock);
        repoParts.save(part);
        return newStock;
    }

    public Provider validateProvider(Long providerId){
        Provider provider = repoProvider.findProviderById(providerId).orElse(null);
        if (provider == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "No such provider exists", HttpStatus.BAD_REQUEST.value());
        }
        else return provider;
    }

    @Transactional
    @Override
    public NewPartDto createPart(NewPartDto newPart) {
        String partCode = newPart.getPartCode();
        Part part = repoParts.findPartByPartCode(partCode).orElse(null);
        // updates an existing part
        if (part != null){
            Integer newStock = newPart.getStock();
            updateStock(part, newStock);
        }
        // creates and saves the new part
        else{
            Long providerId = newPart.getProviderId();
            Provider provider =  validateProvider(providerId);
            part = mapper.reverseMap(newPart, provider);
            repoParts.save(part);
        }
        return newPart;
    }

    // get the parts according the query type
    public List<PartDto> queryParts(String query, LocalDate date, Integer order) throws Exception {
        List<PartDto> listParts = null;
        switch (query){
            case "C":
                listParts = getAllParts();
                break;
            case "P":
                listParts = getAllPartsModify(date, order);
                break;
            case "V":
                listParts = getAllPartsPriceMod(date,order);
                break;
        }
        return listParts;
    }
    // validates order input
    public Integer validateOrder(String order){
        Integer orderInt = null;
        try {
            orderInt = Integer.parseInt(order);
        }
        catch (Exception ex){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "invalid order format", HttpStatus.BAD_REQUEST.value());
        }
        if (orderInt < 1 || orderInt > 3){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "order must be between 1 or 3", HttpStatus.BAD_REQUEST.value());
        }
        return orderInt;
    }

    // gets all parts without filtering
    public List<PartDto> getAllParts(){
        var result =  this.repoParts.findAll();
        ArrayList<PartDto> parts = new ArrayList<>();
        return mapper.mapList(result, false);
    }

    // gets every part which price was modified after the date input
    public List<PartDto> getAllPartsPriceMod(LocalDate date, Integer order) throws Exception {
        List<PartRecord> result = this.repoPartRecords.findByLastModificationAfter(date);
        if (order > 0){
            orderPartsRecords(order, result);
        }
        List<Part> parts = getRelatedParts(result);
        return mapper.mapList(parts, true);
    }

    // gets the parts related to the part records
    public List<Part> getRelatedParts(List<PartRecord> records) {
        List<Part> parts = new ArrayList<>();
        for(PartRecord p: records){
            parts.add(p.getPart());
        }
        return parts;
    }

    // gets every part modified after the date input
    public List<PartDto> getAllPartsModify(LocalDate date, Integer order) throws Exception {
        List<Part> result = this.repoParts.findByLastModificationAfter(date);
        if(order > 0){
            orderParts(order, result);
        }
        return mapper.mapList(result, false);
    }

    // order a list of Parts
    public void orderParts(Integer order, List<Part> parts) throws Exception {
        switch (order){
            case 1:
                parts.sort(Comparator.comparing(Part::getDescription));
                break;
            case 2:
                parts.sort(Comparator.comparing(Part::getDescription).reversed());
                break;
            case 3:
                parts.sort(Comparator.comparing(Part::getLastModification));
                break;
        }
    }

    // order a list of PartsRecords
    public void orderPartsRecords(Integer order, List<PartRecord> parts) throws Exception {
        switch (order){
            case 1:
                parts.sort((p1, p2) -> p1.getPart().getDescription().compareTo(p2.getPart().getDescription()));
                break;
            case 2:
                parts.sort((p1, p2) -> p2.getPart().getDescription().compareTo(p1.getPart().getDescription()));
                break;
            case 3:
                parts.sort(Comparator.comparing(PartRecord::getLastModification));
        }
    }
}
