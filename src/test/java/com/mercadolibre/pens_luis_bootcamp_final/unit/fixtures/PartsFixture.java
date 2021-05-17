package com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures;

import com.mercadolibre.pens_luis_bootcamp_final.dto.NewPartDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.PartDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.PriceVarianceItemDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.PartResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.PriceHistoryDto;
import com.mercadolibre.pens_luis_bootcamp_final.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartsFixture {

    // creates new part dto object
    public static NewPartDto defaultNewPartDto2() throws Exception {
        NewPartDto newPartDto = new NewPartDto();
        newPartDto.setPartCode("82917292");
        newPartDto.setDescription("This is a default part");
        newPartDto.setNetWeight(800);
        newPartDto.setNormalPrice(1000.00);
        newPartDto.setStock(15);
        newPartDto.setLongDimension(180);
        newPartDto.setTalDimension(150);
        newPartDto.setUrgentPrice(1500.00);
        newPartDto.setLastModification("2015-07-12");
        newPartDto.setProviderId(1L);
        newPartDto.setDiscountTypeId(2L);
        newPartDto.setWidthDimension(120);
        return newPartDto;
    }

    // creates new part dto object
    public static NewPartDto defaultNewPartDto() throws Exception {
        NewPartDto newPartDto = new NewPartDto();
        newPartDto.setPartCode("0001");
        newPartDto.setDescription("PruebaNewPartDto");
        newPartDto.setNetWeight(12);
        newPartDto.setNormalPrice(1000.00);
        newPartDto.setStock(15);
        newPartDto.setLongDimension(11);
        newPartDto.setTalDimension(10);
        newPartDto.setUrgentPrice(1500.00);
        newPartDto.setLastModification("2020-05-15");
        newPartDto.setProviderId(1L);
        newPartDto.setDiscountTypeId(2L);
        newPartDto.setWidthDimension(12);
        return newPartDto;
    }

    // create a default provider
    public static Provider defaultProvider(){
        Provider prov = new Provider();
        prov.setId(1L);
        prov.setCountry("Argentina");
        prov.setPhone("1332244");
        prov.setName("PruebaProv");
        prov.setAddress("25 de Mayo 525");
        prov.setParts(defaultListPartModel());

        return prov;
    }

    // create a response dto
    public static PartResponseDto defaultPartResponseDto() {
        return new PartResponseDto(defaultListPartDto());
    }

    // create a single PartDto object
    public static PartDto defaultPartDto() {
        return new PartDto("82917292", "This is a default part", "Brian", "37", "A91", "1500.0", "1980.0", "800","180", "120", "150", "2015-07-12");
    }

    // create default objects for PartDTo dto (last Modification)
    public static List<PartDto> defaultListPartDto() {
        List<PartDto> list = new ArrayList<>();
        list.add(new PartDto("82917292", "This is a default part", "Brian", "37", "A91", "1500.0", "1980.0", "800","180", "120", "150", "2015-07-12"));
        list.add(new PartDto("16282920", "Another default part", "Tony", "10", "F01", "9000.0", "11300.0", "400","170", "110", "80", "2010-05-29"));
        list.add(new PartDto("40927202", "Here we go again", "Roger", "28", "D31", "3200.0", "4250.0", "700","120", "70", "130", "2021-02-04"));
        list.add(new PartDto("27394020", "Last default part", "John", "5", "J04", "4100.0", "5280.0", "300","230", "220", "100", "1999-11-17"));
        return list;
    }

    // create default objects for PartDTo dto (last Price Modification)
    public static List<PartDto> defaultListPartDtoPriceModification() {
        List<PartDto> list = new ArrayList<>();
        list.add(new PartDto("82917292", "This is a default part", "Brian", "37", "A91", "1500.0", "1980.0", "800","180", "120", "150", "2019-03-12"));
        list.add(new PartDto("16282920", "Another default part", "Tony", "10", "F01", "9000.0", "11300.0", "400","170", "110", "80", "2013-11-29"));
        list.add(new PartDto("40927202", "Here we go again", "Roger", "28", "D31", "3200.0", "4250.0", "700","120", "70", "130", "2021-04-01"));
        list.add(new PartDto("27394020", "Last default part", "John", "5", "J04", "4100.0", "5200.0", "300","230", "220", "100", "2002-01-17"));
        return list;
    }

    // create ordered lists for PartRecord models
    public static List<PartRecord> defaultOrderedListPartRecordPrice() {
        List<PartRecord> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(record4, record2, record1, record3);
    }

    public static List<PartRecord> defaultOrderedListPartRecordDescription() {
        List<PartRecord> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(record2, record3, record4, record1);
    }

    public static List<PartRecord> defaultOrderedListPartRecordDescriptionReverse() {
        List<PartRecord> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(record1, record4, record3, record2);
    }


    // create default objects for PartRecord model
    public static List<PartRecord> defaultListPartRecord() {
        List<PartRecord> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(record1, record2, record3, record4);
    }

    // create ordered lists for Part models

    public static List<Part> defaultOrderedListPartDescription() {
        List<Part> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(part2, part3, part4, part1);
    }

    public static List<Part> defaultOrderedListPartDescriptionReversed() {
        List<Part> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(part1, part4, part3, part2);
    }

    public static List<Part> defaultOrderedListPartLastModification() {
        List<Part> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(part4, part2, part1, part3);
    }

    // create default objects for Part model
    public static List<Part> defaultListPartModel() {
        List<Part> list = new ArrayList<>();
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part1 = defaultPart1();
        Part part2 = defaultPart2();
        Part part3 = defaultPart3();
        Part part4 = defaultPart4();
        record1.setPart(part1);
        record2.setPart(part2);
        record3.setPart(part3);
        record4.setPart(part4);
        return Arrays.asList(part1, part2, part3, part4);
    }

    // create default records, one by one...
    public static PartRecord defaultPartRecord1() {
        PartRecord record = new PartRecord();
        record.setId(1L);
        record.setPart(null);
        DiscountType discount = new DiscountType();
        discount.setDescription("A91");
        record.setDiscountType(discount);
        record.setNormalPrice(1500.0);
        record.setUrgentPrice(1980.0);
        record.setLastModification(LocalDate.of(2019, 01, 01));
        return record;
    }
    public static PartRecord defaultPartRecord2() {
        PartRecord record = new PartRecord();
        record.setId(2L);
        record.setPart(null);
        DiscountType discount = new DiscountType();
        discount.setDescription("F01");
        record.setDiscountType(discount);
        record.setNormalPrice(9000.0);
        record.setUrgentPrice(11300.0);
        record.setLastModification(LocalDate.of(2019, 02, 01));
        return record;
    }
    public static PartRecord defaultPartRecord3() {
        PartRecord record = new PartRecord();
        record.setId(3L);
        record.setPart(null);
        DiscountType discount = new DiscountType();
        discount.setDescription("D31");
        record.setDiscountType(discount);
        record.setNormalPrice(3200.0);
        record.setUrgentPrice(4250.0);
        record.setLastModification(LocalDate.of(2019, 03, 01));
        return record;
    }
    public static PartRecord defaultPartRecord4() {
        PartRecord record = new PartRecord();
        record.setId(4L);
        record.setPart(null);
        DiscountType discount = new DiscountType();
        discount.setDescription("J04");
        record.setDiscountType(discount);
        record.setNormalPrice(4100.0);
        record.setUrgentPrice(5280.0);
        record.setLastModification(LocalDate.of(2019, 03, 17));
        return record;
    }
    public static PartRecord defaultPartRecord5() {
        PartRecord record = new PartRecord();
        record.setId(1L);
        record.setPart(defaultPart5());
        DiscountType discount = new DiscountType();
        discount.setDescription("A91");
        record.setDiscountType(discount);
        record.setNormalPrice(1500.0);
        record.setUrgentPrice(1980.0);
        record.setLastModification(LocalDate.of(2019, 03, 12));
        return record;
    }

    // create default parts, one by one...
    public static Part defaultPart1() {
        Part part = new Part();
        part.setId(1L);
        part.setPartCode("82917292");
        part.setDescription("This is a default part");
        Provider provider = new Provider();
        provider.setName("Brian");
        part.setProvider(provider);
        Stock stock = new Stock();
        stock.setQuantity(37);
        part.setStock(stock);
        part.setNetWeight(800);
        part.setLongDimension(180);
        part.setWidthDimenion(120);
        part.setTalDimension(150);
        part.setLastModification(LocalDate.of(2015, 07, 12));
        part.setPartRecords(Arrays.asList(defaultPartRecord1()));
        return part;
    }
    public static Part defaultPart2() {
        Part part = new Part();
        part.setId(2L);
        part.setPartCode("16282920");
        part.setDescription("Another default part");
        Provider provider = new Provider();
        provider.setName("Tony");
        part.setProvider(provider);
        Stock stock = new Stock();
        stock.setQuantity(10);
        part.setStock(stock);
        part.setNetWeight(400);
        part.setLongDimension(170);
        part.setWidthDimenion(110);
        part.setTalDimension(80);
        part.setLastModification(LocalDate.of(2010, 05, 29));
        part.setPartRecords(Arrays.asList(defaultPartRecord2()));
        return part;
    }
    public static Part defaultPart3() {
        Part part = new Part();
        part.setId(3L);
        part.setPartCode("40927202");
        part.setDescription("Here we go again");
        Provider provider = new Provider();
        provider.setName("Roger");
        part.setProvider(provider);
        Stock stock = new Stock();
        stock.setQuantity(28);
        part.setStock(stock);
        part.setNetWeight(700);
        part.setLongDimension(120);
        part.setWidthDimenion(70);
        part.setTalDimension(130);
        part.setLastModification(LocalDate.of(2021, 02, 04));
        part.setPartRecords(Arrays.asList(defaultPartRecord3()));
        return part;
    }
    public static Part defaultPart4() {
        Part part = new Part();
        part.setId(4L);
        part.setPartCode("27394020");
        part.setDescription("Last default part");
        Provider provider = new Provider();
        provider.setName("John");
        part.setProvider(provider);
        Stock stock = new Stock();
        stock.setQuantity(5);
        part.setStock(stock);
        part.setNetWeight(300);
        part.setLongDimension(230);
        part.setWidthDimenion(220);
        part.setTalDimension(100);
        part.setLastModification(LocalDate.of(1999, 11, 17));
        part.setPartRecords(Arrays.asList(defaultPartRecord4()));
        return part;
    }

    // create default parts, one by one...
    public static Part defaultPart5() {
        Part part = new Part();
        part.setPartCode("82917292");
        part.setDescription("This is a default part");
        part.setProvider(defaultProvider());
        Stock stock = new Stock();
        stock.setPart(defaultPart6());
        stock.setQuantity(15);
        part.setStock(null);
        part.setNetWeight(800);
        part.setLongDimension(180);
        part.setWidthDimenion(120);
        part.setTalDimension(150);
        part.setLastModification(LocalDate.now());
        part.setPartRecords(null);
        return part;
    }
    // create default parts, one by one...
    public static Part defaultPart6() {
        Part part = new Part();
        part.setPartCode("82917292");
        part.setDescription("This is a default part");
        part.setProvider(defaultProvider());
        Stock stock = new Stock();
        stock.setQuantity(15);
        part.setStock(stock);
        part.setNetWeight(800);
        part.setLongDimension(180);
        part.setWidthDimenion(120);
        part.setTalDimension(150);
        part.setLastModification(LocalDate.now());
        part.setPartRecords(Arrays.asList(defaultPartRecord1()));
        return part;
    }

    public static Part defaultpartwithrecords()
    {
        PartRecord record1 = defaultPartRecord1();
        PartRecord record2 = defaultPartRecord2();
        PartRecord record3 = defaultPartRecord3();
        PartRecord record4 = defaultPartRecord4();
        Part part = new Part();
        part.setPartCode("88991122");
        part.setDescription("This is a default part");
        part.setProvider(defaultProvider());
        Stock stock = new Stock();
        stock.setQuantity(15);
        part.setStock(stock);
        part.setNetWeight(800);
        part.setLongDimension(180);
        part.setWidthDimenion(120);
        part.setTalDimension(150);
        part.setLastModification(LocalDate.now());

        List<PartRecord> recordList = new ArrayList<>();
        record1.setPart(part);
        record2.setPart(part);
        record3.setPart(part);
        record4.setPart(part);
        recordList.add(record1);
        recordList.add(record2);
        recordList.add(record3);
        recordList.add(record4);

        part.setPartRecords(recordList);
        return part;

    }

    public static PriceHistoryDto getPriceHistory()
    {

        PriceHistoryDto history = new PriceHistoryDto();
        Part expectedPart = defaultpartwithrecords();
        List<PartRecord> recordsList = expectedPart.getPartRecords();

        history.setStartingNormalPrice(recordsList.get(0).getNormalPrice());
        history.setStartingUrgentPrice(recordsList.get(0).getUrgentPrice());
        history.setEndingUrgentPrice(recordsList.get(recordsList.size()-1).getUrgentPrice());
        history.setEndingNormalPrice(recordsList.get(recordsList.size()-1).getNormalPrice());
        history.setNormalPriceVariance(getVariance(history.getStartingNormalPrice(), history.getEndingNormalPrice()));
        history.setUrgentPriceVariance(getVariance(history.getStartingUrgentPrice(), history.getEndingUrgentPrice()));

        List<PriceVarianceItemDto> varianceItemDtos = new ArrayList<>();
        varianceItemDtos.add(defaultPriceVariance());
        varianceItemDtos.add(defaultPriceVariance2());
        varianceItemDtos.add(defaultPriceVariance3());
        varianceItemDtos.add(defaultPriceVariance4());
        history.setPriceChangeVarianceList(varianceItemDtos);
        return history;
    }

    public static double getVariance(double startprice, double endprice)
    {
        double test = ((endprice *100) / startprice );
        return test- 100;
    }

    public static PriceVarianceItemDto defaultPriceVariance()
    {
        PartRecord price = defaultPartRecord1();
        double normalPriceVarition = 0;
        double urgentPriceVariation = 0;
        PriceVarianceItemDto item = new PriceVarianceItemDto();
        item.setDate(price.getLastModification().toString());
        item.setNormalPrice(price.getNormalPrice());
        item.setUrgentPrice(price.getUrgentPrice());
        item.setNormalPriceVariation(normalPriceVarition);
        item.setUrgentPriceVariation(urgentPriceVariation);
        return item;
    }

    public static PriceVarianceItemDto defaultPriceVariance2()
    {
        PartRecord price = defaultPartRecord2();
        PriceVarianceItemDto lastPriceChange = defaultPriceVariance();
        double  normalPriceVarition = getVariance(lastPriceChange.getNormalPrice(), price.getNormalPrice());
        double urgentPriceVariation = getVariance(lastPriceChange.getUrgentPrice(), price.getUrgentPrice());
        PriceVarianceItemDto item = new PriceVarianceItemDto();
        item.setDate(price.getLastModification().toString());
        item.setNormalPrice(price.getNormalPrice());
        item.setUrgentPrice(price.getUrgentPrice());
        item.setNormalPriceVariation(normalPriceVarition);
        item.setUrgentPriceVariation(urgentPriceVariation);
        return item;
    }
    public static PriceVarianceItemDto defaultPriceVariance3()
    {
        PartRecord price = defaultPartRecord3();
        PriceVarianceItemDto lastPriceChange = defaultPriceVariance2();
        double  normalPriceVarition = getVariance(lastPriceChange.getNormalPrice(), price.getNormalPrice());
        double urgentPriceVariation = getVariance(lastPriceChange.getUrgentPrice(), price.getUrgentPrice());
        PriceVarianceItemDto item = new PriceVarianceItemDto();
        item.setDate(price.getLastModification().toString());
        item.setNormalPrice(price.getNormalPrice());
        item.setUrgentPrice(price.getUrgentPrice());
        item.setNormalPriceVariation(normalPriceVarition);
        item.setUrgentPriceVariation(urgentPriceVariation);
        return item;
    }
    public static PriceVarianceItemDto defaultPriceVariance4()
    {
        PartRecord price = defaultPartRecord4();
        PriceVarianceItemDto lastPriceChange = defaultPriceVariance3();
        double  normalPriceVarition = getVariance(lastPriceChange.getNormalPrice(), price.getNormalPrice());
        double urgentPriceVariation = getVariance(lastPriceChange.getUrgentPrice(), price.getUrgentPrice());
        PriceVarianceItemDto item = new PriceVarianceItemDto();
        item.setDate(price.getLastModification().toString());
        item.setNormalPrice(price.getNormalPrice());
        item.setUrgentPrice(price.getUrgentPrice());
        item.setNormalPriceVariation(normalPriceVarition);
        item.setUrgentPriceVariation(urgentPriceVariation);
        return item;
    }
}
