package importExcel.repository;

import importExcel.entity.PaySlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PayslipRepository extends JpaRepository<PaySlip,Long> {
}
