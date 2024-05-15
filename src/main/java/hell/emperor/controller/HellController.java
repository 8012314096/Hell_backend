package hell.emperor.controller;

import hell.emperor.entity.HellCustomer;
import hell.emperor.service.HellCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class HellController {

    @Autowired
    private HellCustomerService hellCustomerService;

    @PostMapping("/createOrUpdateCustomer/{phone}")
    public ResponseEntity<HellCustomer> createOrUpdateCustomer(@PathVariable("phone") String phone,
                                                               @RequestBody Map<String, String> columnUpdates) {
        try {
            HellCustomer customer = hellCustomerService.createOrUpdateCustomer(phone, columnUpdates);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (ChangeSetPersister.NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all customers
    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<HellCustomer>> getAllCustomers() {

        List<HellCustomer> customers = hellCustomerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Retrieve a single customer by ID
    @GetMapping("/{phone}")
    public ResponseEntity<HellCustomer> getCustomerById(@PathVariable("phone") String phone) {

        HellCustomer customer = hellCustomerService.getCustomerById(phone);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Delete a customer by ID
    @DeleteMapping("/{phone}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("phone") String phone) {

        HellCustomer existingCustomer = hellCustomerService.getCustomerById(phone);
        if (existingCustomer != null) {
            hellCustomerService.deleteCustomerById(phone);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve customers by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<HellCustomer>> getCustomersByName(@PathVariable String name) {

        List<HellCustomer> customers = hellCustomerService.findCustomersByName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
