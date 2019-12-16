package co.yabx.admin.portal.app.kyc.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.Question;

/**
 * 
 * @author Asad.ali
 *
 */
@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {
	List<Question> findByIdIn(Collection<Integer> ids, Pageable pageable);

	@Query("Select q from Question q where q.id=?1")
	Question findById(Integer id);

}
