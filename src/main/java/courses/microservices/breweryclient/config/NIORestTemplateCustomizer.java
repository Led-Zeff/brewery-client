package courses.microservices.breweryclient.config;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

  public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
    final DefaultConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(IOReactorConfig.custom()
              .setConnectTimeout(3000)
              .setIoThreadCount(4)
              .setSoTimeout(3000)
              .build());

    final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor);
    connectionManager.setDefaultMaxPerRoute(100);
    connectionManager.setMaxTotal(1000);

    CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
              .setConnectionManager(connectionManager)
              .build();

    return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
  }

  @Override
  public void customize(RestTemplate restTemplate) {
    try {
      restTemplate.setRequestFactory(clientHttpRequestFactory());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

}