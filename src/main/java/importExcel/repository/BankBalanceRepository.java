package importExcel.repository;

import importExcel.entity.BankBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BankBalanceRepository extends JpaRepository<BankBalance,Long> {

    @Modifying
    @Query(value = "delete from bank_balance where keyuuid = :keyUUID",nativeQuery = true)
    void deleteByKeyUUID(String keyUUID);
}
