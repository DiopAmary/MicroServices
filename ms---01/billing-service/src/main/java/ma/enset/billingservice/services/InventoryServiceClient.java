package ma.enset.billingservice.services;

import ma.enset.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryServiceClient{
    @GetMapping("/products/{id}?fullCustomer")
    Product findProductById(@PathVariable("id") Long id);

    @GetMapping("/products?fullCustomer")
    PagedModel<Product> findAll();
}
