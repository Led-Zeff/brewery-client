package courses.microservices.breweryclient.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class BeerDto {
  private UUID id;
  private String name;
  private String style;
  private Long upc;
}