package importExcel.repository;

import importExcel.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Modifying
    @Query(value = "delete from stock where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from stock where keyuuid = :keyUUID",nativeQuery = true)
    List<Stock> getStocksByKeyUUID(String keyUUID);
}