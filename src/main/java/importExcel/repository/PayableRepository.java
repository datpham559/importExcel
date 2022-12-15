package importExcel.repository;

import importExcel.entity.Payable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PayableRepository extends JpaRepository<Payable, Long> {
    @Modifying
    @Query(value = "delete from payable where keyuuid = :keyUUID", nativeQuery = true)
    public void deleteByKeyUUID(String keyUUID);

    @Query(value = "select * from payable where keyuuid = :keyUUID", nativeQuery = true)
    public List<Payable> getPayablesByKeyUUID(String keyUUID);
}