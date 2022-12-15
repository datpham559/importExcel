package importExcel.repository;

import importExcel.entity.DmMerchandise;
import importExcel.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DmMerchandiseRepository extends JpaRepository<DmMerchandise,Long> {

    @Modifying
    @Query(value = "delete from dm_merchandise where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from dm_merchandise where keyUUID = :keyUUID",nativeQuery = true)
    List<DmMerchandise> getDmMerchandiseByKeyUUID(String keyUUID);
}
