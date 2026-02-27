package repository.custom;

import model.entity.Customer;
import repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
