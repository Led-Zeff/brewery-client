package courses.microservices.breweryclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import courses.microservices.breweryclient.client.CustomerClient;
import courses.microservices.breweryclient.model.CustomerDto;

@SpringBootTest
public class CustomerClientTest {
  
  @Autowired CustomerClient customerClient;

  @Test
  void getCustomerById() {
    CustomerDto dto = customerClient.getCustomerById(UUID.randomUUID());
    assertNotNull(dto);
  }

  @Test
  void createCustomer() {
    CustomerDto dto = CustomerDto.builder().name("Buss").build();
    customerClient.createCustomer(dto);
    assertNotNull(dto);
  }

  @Test
  void updateCustomer() {
    CustomerDto dto = CustomerDto.builder().build();
    customerClient.updateCustomer(UUID.randomUUID(), dto);
  }

  @Test
  void deleteCustomer() {
    customerClient.deleteCustomer(UUID.randomUUID());
  }

}