package co.yabx.admin.portal.app.kyc.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.yabx.admin.portal.app.kyc.entities.Answer;

/**
 * 
 * @author Asad.ali
 *
 */
@Transactional(readOnly = true)
@Repository("answerRepository")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	List<Answer> findByIdIn(Collection<Long> ids, Pageable pageable);

	Answer findById(Integer id);

}
