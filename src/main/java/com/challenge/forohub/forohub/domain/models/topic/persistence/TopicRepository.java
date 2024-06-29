package com.challenge.forohub.forohub.domain.models.topic.persistence;

import com.challenge.forohub.forohub.domain.models.enums.Status;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
  Optional<Topic> findByTitle(String title);

  Page<Topic> findByStatus(Pageable pg, Status status);
  void removeById(Long id);
}
