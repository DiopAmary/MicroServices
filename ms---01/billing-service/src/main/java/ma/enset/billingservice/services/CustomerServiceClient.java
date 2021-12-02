package ma.enset.billingservice.services;

import ma.enset.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerServiceClient{
    @GetMapping("/customers/{id}?fullCustomer")
    Customer findCustomerById(@PathVariable("id") Long id);
}
