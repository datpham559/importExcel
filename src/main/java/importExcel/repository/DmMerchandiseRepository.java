package importExcel.repository;

import importExcel.entity.DmMerchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DmMerchandiseRepository extends JpaRepository<DmMerchandise,Long> {

    @Modifying
    @Query(value = "delete from dm_merchandise where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);
}
