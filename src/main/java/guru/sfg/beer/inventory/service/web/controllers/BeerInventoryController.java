package guru.sfg.beer.inventory.service.web.controllers;

import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-05-31.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @GetMapping("/api/v1/beer/{beerId}/inventory")
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId){
        log.debug("Finding Inventory for beerId:" + beerId);

        List<BeerInventoryDto> beers = beerInventoryRepository.findAllByBeerId(beerId)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
        System.out.println("list of beers inv:"+beerInventoryRepository.findAllByBeerId(beerId));
        return beers;
    }

    /*@GetMapping("/api/v1/beer/{upc}/inventory")
    List<BeerInventoryDto> listBeersByUpc(@PathVariable String upc){
        log.debug("Finding Inventory for beerId:" + upc);

        List<BeerInventoryDto> beers = beerInventoryRepository.findAllByUpc(upc)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
        System.out.println("list of beers inv:"+beerInventoryRepository.findAllByUpc(upc));
        return beers;
    }*/

    @GetMapping("/api/v1/beer/inventory")
    List<BeerInventoryDto> listAll(){
        List<BeerInventoryDto> beers = beerInventoryRepository.findAll()
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
        System.out.println(beers);
        return beers;
    }
}
