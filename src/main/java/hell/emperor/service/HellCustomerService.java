package hell.emperor.service;

import hell.emperor.entity.HellCustomer;
import hell.emperor.repository.HellCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HellCustomerService
{

    @Autowired
    private HellCustomerRepository hellCustomerRepository;
    @Autowired
    private EntityManager entityManager;

    // Save or update a customer
    public HellCustomer createOrUpdateCustomer(String phone, Map<String, String> columnUpdates) throws ChangeSetPersister.NotFoundException {
        HellCustomer updatedCustomer;

        // Check if the customer exists based on phone
        Optional<HellCustomer> optionalCustomer = hellCustomerRepository.findByPhone(phone);

        if (optionalCustomer.isPresent()) {
            // If customer exists, update the existing customer
            updatedCustomer = optionalCustomer.get();
        } else {
            // If customer does not exist, create a new customer
            updatedCustomer = new HellCustomer();
            updatedCustomer.setPhone(phone);
        }

        // Update customer attributes
        for (Map.Entry<String, String> entry : columnUpdates.entrySet()) {
            String columnName = entry.getKey();
            String columnValue = entry.getValue();

            switch (columnName) {
                case "name":
                    updatedCustomer.setName(columnValue);
                    break;
                case "mail":
                    updatedCustomer.setMail(columnValue);
                    break;
                case "password":
                    updatedCustomer.setPassword(columnValue);
                    break;
                case "bookingType":
                    updatedCustomer.setBookingType(columnValue);
                    break;
                case "bookingDate":
                    updatedCustomer.setBookingDate(columnValue);
                    break;
                case "teamCount":
                    updatedCustomer.setTeamCount(Integer.parseInt(columnValue));
                    break;
                case "otp":
                    updatedCustomer.setOtp(columnValue);
                    break;
                case "six":
                    updatedCustomer.setSix(Boolean.parseBoolean(columnValue));
                    break;
                case "seven":
                    updatedCustomer.setSeven(Boolean.parseBoolean(columnValue));
                    break;
                case "eight":
                    updatedCustomer.setEight(Boolean.parseBoolean(columnValue));
                    break;
                case "nine":
                    updatedCustomer.setNine(Boolean.parseBoolean(columnValue));
                    break;
                default:
                    System.out.println("Invalid column name: " + columnName);
                    break;
            }
        }

        // Save the updated or new customer
        HellCustomer savedCustomer = hellCustomerRepository.save(updatedCustomer);
        return savedCustomer;
    }



    // Retrieve all customers
    public List<HellCustomer> getAllCustomers() {
        return hellCustomerRepository.findAll();
    }

    // Retrieve a single customer by ID
    public HellCustomer getCustomerById(String phone) {
        Optional<HellCustomer> optionalCustomer = hellCustomerRepository.findById(Long.valueOf(phone));
        return optionalCustomer.orElse(null);
    }

    // Delete a customer by ID
    public void deleteCustomerById(String phone) {
        hellCustomerRepository.deleteById(Long.valueOf(phone));
    }
    public List<HellCustomer> findCustomersByName(String name) {
        return hellCustomerRepository.findByName(name);
    }
}
