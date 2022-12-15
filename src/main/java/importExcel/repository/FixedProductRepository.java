package importExcel.repository;

import importExcel.entity.Customer;
import importExcel.entity.FixedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FixedProductRepository extends JpaRepository<FixedProduct, Long> {
    @Modifying
    @Query(value = "delete from fixed_product where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from fixed_product where keyUUID = :keyUUID",nativeQuery = true)
    List<FixedProduct> getFixedProducts(String keyUUID);
}