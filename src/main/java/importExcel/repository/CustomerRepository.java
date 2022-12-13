package importExcel.repository;

import importExcel.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Modifying
    @Query(value = "delete from customers where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);
}
