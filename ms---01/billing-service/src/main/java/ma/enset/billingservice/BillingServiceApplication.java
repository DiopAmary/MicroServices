package ma.enset.billingservice;
import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
    @Autowired
    BillRepository billRepository;
    @Autowired
    ProductItemRepository productItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository){
        return arg -> {
            Bill bill = new Bill(new Long(1));
            billRepository.save(bill);
            productItemRepository.save(new ProductItem(1200, 3, new Long(1), bill));
            productItemRepository.save(new ProductItem(2500, 7, new Long(2), bill));
        };
    }

}
