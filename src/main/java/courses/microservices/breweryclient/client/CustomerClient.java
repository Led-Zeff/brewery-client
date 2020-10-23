package courses.microservices.breweryclient.client;

import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import courses.microservices.breweryclient.model.CustomerDto;
import lombok.Setter;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {
  
  public static final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

  @Setter
  private String apiHost;

  private final RestTemplate restTemplate;

  public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.build();
  }

  public CustomerDto getCustomerById(UUID id) {
    return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + id.toString(), CustomerDto.class);
  }

  public CustomerDto createCustomer(CustomerDto customerDto) {
    return restTemplate.postForObject(apiHost + CUSTOMER_PATH_V1, customerDto, CustomerDto.class);
  }

  public void updateCustomer(UUID id, CustomerDto dto) {
    restTemplate.put(apiHost + CUSTOMER_PATH_V1 + id.toString(), dto);
  }

  public void deleteCustomer(UUID id) {
    restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + id.toString());
  }

}
