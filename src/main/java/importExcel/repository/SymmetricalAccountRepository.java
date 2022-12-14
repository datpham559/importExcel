package importExcel.repository;

import importExcel.entity.SymmetricalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SymmetricalAccountRepository extends JpaRepository<SymmetricalAccount,Long> {

    @Modifying
    @Query(value = "delete from symmetrical_account where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);
}
