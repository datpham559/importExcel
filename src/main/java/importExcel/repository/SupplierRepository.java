package importExcel.repository;

import importExcel.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Modifying
    @Query(value = "delete from supplies where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);
}
