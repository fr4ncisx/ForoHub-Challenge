package com.challenge.forohub.forohub.domain.models.topic.service;

import com.challenge.forohub.forohub.domain.models.enums.Status;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicDTO;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicValidationDTO;
import com.challenge.forohub.forohub.domain.models.topic.persistence.Topic;
import com.challenge.forohub.forohub.domain.models.topic.persistence.TopicRepository;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;
import com.challenge.forohub.forohub.infra.errors.DuplicatedEntryError;
import com.challenge.forohub.forohub.infra.errors.TopicNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements ITopicService {

  private TopicRepository repository;

  public TopicServiceImpl(TopicRepository repository) {
    this.repository = repository;
  }

  @Override
  public ResponseEntity<?> createTopic(
    TopicValidationDTO validationDTO,
    User userFound
  ) {
    Topic topic = new Topic(validationDTO, userFound);
    if (!repository.findByTitle(topic.getTitle()).isPresent()) {
      repository.save(topic);
      return new ResponseEntity<>(new TopicDTO(topic), HttpStatus.CREATED);
    } else {
      throw new DuplicatedEntryError(
        "No se pueden crear topicos con títulos iguales"
      );
    }
  }

  @Override
  public Page<TopicDTO> findAllTopics(Pageable pg) {
    var activeTopics = repository.findByStatus(pg, Status.ACTIVO);
    if (!activeTopics.isEmpty()) {
      var response = activeTopics
      .map(list -> new TopicDTO(list));
      return response;
    } else {
      throw new TopicNotFoundException("No se encontraron topicos");
    }
  }

  @Override
  public ResponseEntity<?> findTopicById(Long id) {
    var topicFound = repository.findById(id);
    if(topicFound.isPresent()){
      return ResponseEntity.ok(new TopicDTO(topicFound.get()));
    } else {
      throw new TopicNotFoundException("El topico no fue encontrado");
    }
  }

  @Override
  public ResponseEntity<?> updateTopicById(Long id, TopicValidationDTO validationDTO, User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateTopicById'");
  }
}