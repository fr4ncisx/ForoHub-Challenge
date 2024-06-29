package com.challenge.forohub.forohub.domain.models.topic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.challenge.forohub.forohub.domain.models.topic.dto.TopicDTO;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicValidationDTO;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;


public interface ITopicService {

    ResponseEntity<?> createTopic(TopicValidationDTO validationDTO, User user);
    public Page<TopicDTO> findAllTopics(Pageable pg);
    ResponseEntity<?> findTopicById(Long id);
    ResponseEntity<?> updateTopicById(Long id, TopicValidationDTO validationDTO, User user);
}
