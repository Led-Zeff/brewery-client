package courses.microservices.breweryclient.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import courses.microservices.breweryclient.model.BeerDto;
import lombok.Setter;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

  public static final String BEER_PATH_V1 = "/api/v2/beer/";

  @Setter
  private String apiHost;

  private final RestTemplate restTemplate;

  public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.build();
  }

  public BeerDto getBeerById(UUID id) {
    return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDto.class);
  }

  public URI saveBeer(BeerDto beerDto) {
    return restTemplate.postForLocation(apiHost + BEER_PATH_V1, beerDto);
  }

  public void updateBeer(UUID id, BeerDto beerDto) {
    restTemplate.put(apiHost + BEER_PATH_V1 + id.toString(), beerDto);
  }

  public void deleteBeer(UUID id) {
    restTemplate.delete(apiHost + BEER_PATH_V1 + id.toString());
  }

}