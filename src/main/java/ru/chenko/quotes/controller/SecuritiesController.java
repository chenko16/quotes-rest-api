package ru.chenko.quotes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.chenko.quotes.model.EnergyLevel;
import ru.chenko.quotes.model.Quote;
import ru.chenko.quotes.service.EnergyLevelService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
public class SecuritiesController {

    private static final Logger logger = LoggerFactory.getLogger(SecuritiesController.class);

    private EnergyLevelService energyLevelService;

    @Autowired
    public SecuritiesController(EnergyLevelService energyLevelService) {
        this.energyLevelService = energyLevelService;
    }

    @PostMapping(path = "/quote")
    public ResponseEntity postQuote(@RequestBody Quote quote) {
        logger.info("Gotten quote request with ISIN=" + quote.getIsin());
        energyLevelService.processQuote(quote);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "/elvl")
    @ResponseBody
    public EnergyLevel getEnergyLevel(@RequestParam(name = "ISIN") String isin) {
        logger.info("Gotten elvl request with ISIN=" + isin);
        EnergyLevel found = energyLevelService.findEnergyLevelByIsin(isin);
        if(found == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find elevl");
        }

        return found;
    }


    @GetMapping(path = "/elvl/list")
    @ResponseBody
    public List<EnergyLevel> getEnergyLevelList() {
        logger.info("Gotten elvl list request");
        return energyLevelService.getEnergyLevelList();
    }

}
