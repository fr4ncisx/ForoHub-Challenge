package com.challenge.forohub.forohub.domain.models.topic.service;

import com.challenge.forohub.forohub.domain.models.answer.persistence.AnswerRepository;
import com.challenge.forohub.forohub.domain.models.enums.Status;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicDTO;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicUpdatedDTO;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicValidationDTO;
import com.challenge.forohub.forohub.domain.models.topic.persistence.Topic;
import com.challenge.forohub.forohub.domain.models.topic.persistence.TopicRepository;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;
import com.challenge.forohub.forohub.domain.models.user.persistence.UserRepository;
import com.challenge.forohub.forohub.infra.errors.DuplicatedEntryError;
import com.challenge.forohub.forohub.infra.errors.TopicNotFoundException;
import com.challenge.forohub.forohub.infra.errors.UserOwnershipViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements ITopicService {

  private TopicRepository topicRepository;
  private UserRepository userRepository;
  private AnswerRepository answerRepository;


  public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository,
      AnswerRepository answerRepository) {
    this.topicRepository = topicRepository;
    this.userRepository = userRepository;
    this.answerRepository = answerRepository;
  }

  @Override
  public ResponseEntity<?> createTopic(
    TopicValidationDTO validationDTO,
    User userFound
  ) {
    Topic topic = new Topic(validationDTO, userFound);
    if (!topicRepository.findByTitle(topic.getTitle()).isPresent()) {
      topicRepository.save(topic);
      return new ResponseEntity<>(new TopicDTO(topic), HttpStatus.CREATED);
    } else {
      throw new DuplicatedEntryError(
        "No se pueden crear topicos con títulos iguales"
      );
    }
  }

  @Override
  public Page<TopicDTO> findAllTopics(Pageable pg) {
    var activeTopics = topicRepository.findByStatus(pg, Status.ACTIVO);
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
    var topicFound = topicRepository.findById(id);
    if(topicFound.isPresent()){
      return ResponseEntity.ok(new TopicDTO(topicFound.get()));
    } else {
      throw new TopicNotFoundException("El topico no fue encontrado");
    }
  }

  @Override
  public ResponseEntity<?> updateTopicById(Long id, TopicValidationDTO validationDTO, Long user_id) {
    var findTopic = topicRepository.findById(id);
    var findUser = userRepository.findById(user_id);
    if(findTopic.isPresent() && findUser.isPresent()){  
      if(findTopic.get().getUser().getId() == findUser.get().getId()){ 
      findTopic.get().updateTopic(validationDTO);
      return ResponseEntity.ok(new TopicUpdatedDTO(validationDTO));
      } else {
        throw new UserOwnershipViolationException("El usuario no tiene los permisos para modificar el tópico");
      }   
    } else {
      throw new TopicNotFoundException("Usuario o topico inexistente"); 
    }
  }

  @Override
  public ResponseEntity<?> deleteTopicById(Long topic_id) {
    var topicFound = topicRepository.findById(topic_id); 
    if(topicFound.isPresent()){    
      answerRepository.removeByTopicId(topic_id);
      topicRepository.removeById(topic_id);
      return ResponseEntity.ok().build();
    }  
    throw new TopicNotFoundException("El topico no existe");
  }
}