package com.bekamdo.service;

import com.bekamdo.domain.Beer;
import com.bekamdo.mappers.BeerMapper;
import com.bekamdo.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
public class BeerServiceImplTest {
    @Autowired
    BeerService beerService;
    @Autowired
    BeerMapper beerMapper;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDTO(getTestBeer());
    }

    @Test
    void saveBeer()  {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Mono<BeerDTO> savedMono = beerService.saveBeer(Mono.just(beerDTO));

        savedMono.subscribe(savedDto -> {
            System.out.println(savedDto.getId());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);


    }

//    @Test
//    void findFirstByBeerNameTest() {
//        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
//        BeerDTO beerDTO = getSavedBeerDto();
//        Mono<BeerDTO> foundDto = beerService.findFirstByBeerName(beerDTO.getBeerName());
//        foundDto.subscribe(dto -> {
//            System.out.println(dto.toString());
//            atomicBoolean.set(true);
//        });
//
//        await().untilTrue(atomicBoolean);
//
//    }


//    @Test
//    void testFindByBeerStyle() {
//        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
//        BeerDTO beerDTO1 = getSavedBeer();
//        beerService.findByBeerStyle(beerDTO1.getBeerStyle())
//                .subscribe(dto -> {
//                    System.out.println(dto.toString());
//                    atomicBoolean.set(true);
//                });
//
//        await().untilTrue(atomicBoolean);
//    }

    public static Beer getTestBeer(){
        return  Beer.builder()
                .beerName("Space dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("123213")
                .build();
    }
}