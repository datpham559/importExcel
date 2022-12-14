package importExcel.repository;

import importExcel.entity.DepositCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DepositCollectionRepository extends JpaRepository<DepositCollection,Long> {
}
