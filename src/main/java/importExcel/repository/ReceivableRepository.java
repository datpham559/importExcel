package importExcel.repository;

import importExcel.entity.Receivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReceivableRepository extends JpaRepository<Receivable, Long> {
    @Modifying
    @Query(value = "delete from receivable where keyuuid = :keyUUID", nativeQuery = true)
    public void deleteByKeyUUID(String keyUUID);
}