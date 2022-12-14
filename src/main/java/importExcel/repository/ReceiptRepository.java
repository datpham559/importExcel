package importExcel.repository;

import importExcel.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
}
