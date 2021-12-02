package ma.enset.billingservice.controllers;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import ma.enset.billingservice.services.CustomerServiceClient;
import ma.enset.billingservice.services.InventoryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BillRestController{
    @Autowired
    private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;

    @Autowired
    CustomerServiceClient customerServiceClient;
    @Autowired
    InventoryServiceClient inventoryServiceClient;

    @GetMapping("/bill/full/{id}")
    Bill getBill(@PathVariable("id") Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerId()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(inventoryServiceClient.findProductById(pi.getProductId()));
        });
        return bill;
    }

}
