package importExcel.repository;

import importExcel.entity.Accreditative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccreditativesRepository  extends JpaRepository<Accreditative,Long> {
}
