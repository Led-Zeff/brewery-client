package courses.microservices.breweryclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import courses.microservices.breweryclient.client.BreweryClient;
import courses.microservices.breweryclient.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BreweryClientTests {
  
  @Autowired BreweryClient breweryClient;

  @Test
  void getBeerById() {
    BeerDto dto = breweryClient.getBeerById(UUID.randomUUID());
    assertNotNull(dto);
  }

  @Test
  void saveBeer() {
    BeerDto dto = BeerDto.builder().name("").style("IPA").upc(454534543L).build();
    URI uri = breweryClient.saveBeer(dto);
    assertNotNull(uri);
    log.info(uri.toString());
  }

  @Test
  void updateBeer() {
    BeerDto dto = BeerDto.builder().name("Minerva").style("IPA").upc(3242343432L).build();
    breweryClient.updateBeer(UUID.randomUUID(), dto);
  }

  @Test
  void deleteBeer() {
    breweryClient.deleteBeer(UUID.randomUUID());
  }

}