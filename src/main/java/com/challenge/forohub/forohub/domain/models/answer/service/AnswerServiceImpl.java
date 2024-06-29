package com.challenge.forohub.forohub.domain.models.answer.service;

import com.challenge.forohub.forohub.domain.models.answer.dto.AnswerDTO;
import com.challenge.forohub.forohub.domain.models.answer.persistence.Answer;
import com.challenge.forohub.forohub.domain.models.answer.persistence.AnswerRepository;
import com.challenge.forohub.forohub.domain.models.topic.persistence.TopicRepository;
import com.challenge.forohub.forohub.domain.models.user.persistence.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements IAnswerService {

  private AnswerRepository answerRepository;
  private TopicRepository topicRepository;
  private UserRepository userRepository;

  public AnswerServiceImpl(
    AnswerRepository answerRepository,
    TopicRepository topicRepository,
    UserRepository userRepository
  ) {
    this.answerRepository = answerRepository;
    this.topicRepository = topicRepository;
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<?> answerTopic(
    @Valid AnswerDTO dto,
    Long user_id,
    Long topic_id
  ) {
    Answer answer = new Answer(dto, user_id, topic_id);
    var userExists = userRepository.findById(user_id);
    var topicExists = topicRepository.findById(topic_id);
    if (topicExists.isPresent() && userExists.isPresent()){
      answerRepository.save(answer);
      // hacer un count select en response usando el topic_id 
      return new ResponseEntity<>(new AnswerDTO(answer.getMessage()),
      HttpStatus.CREATED);
    } else if(!topicExists.isPresent() && !userExists.isPresent()){
      throw new RuntimeException("No existe topico y usuario");
    } else if(!topicExists.isPresent()){
      throw new RuntimeException("No existe el topico");
    } else {
      throw new RuntimeException("No existe el usuario");
    }    
  }
}
