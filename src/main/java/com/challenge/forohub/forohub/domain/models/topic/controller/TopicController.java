package com.challenge.forohub.forohub.domain.models.topic.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.challenge.forohub.forohub.domain.models.topic.dto.TopicDTO;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicValidationDTO;
import com.challenge.forohub.forohub.domain.models.topic.service.ITopicService;
import com.challenge.forohub.forohub.domain.models.user.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private ITopicService topicService;
    private IUserService userService;


    public TopicController(ITopicService topicService, IUserService userService) {
      this.topicService = topicService;
      this.userService = userService;
    }

    @PostMapping("/new/{id}")
    @Operation(summary = "Crear topico", description = "Crea un nuevo topico obteniendo la id del usuario, retorna los datos ingresados,el usuario y el correo")
    @Transactional(readOnly = false)
    public ResponseEntity<?> newTopic(@Valid @RequestBody TopicValidationDTO validationDTO, @PathVariable Long id){
      var userFound = userService.findUserToCreateTopic(id);
      return topicService.createTopic(validationDTO, userFound);
    }
       
    @GetMapping("/find")  
    @Operation(summary = "Busca todos los topicos", description = "Retorna todo los topicos en un formato Page, limitado a 10, en orden ascendente y solo muestra los tópicos activos")
    @Transactional(readOnly = true)  
    public Page<TopicDTO> findAllTopics(@PageableDefault(size = 10, sort = {"title"}, direction = Direction.ASC) Pageable pg) {
        return topicService.findAllTopics(pg);
    } 
    @GetMapping("/{id}")
    @Operation(summary = "Busca solo el topico por id", description = "Devuelve el topico que coincida con el id ingresado")
    @Transactional(readOnly = true) 
    public ResponseEntity<?> findById(@PathVariable Long id){
      return topicService.findTopicById(id);
    }
    @PutMapping("/edit/{id}/user/{user_id}")
    @Transactional
    public ResponseEntity<?> updateTopic(@Valid @RequestBody TopicValidationDTO dto, @PathVariable Long id, @PathVariable Long user_id){
      return topicService.updateTopicById(id, dto, user_id);
    }
    @DeleteMapping("/delete/{topic_id}")
    @Transactional
    public ResponseEntity<?> deleteTopic(@PathVariable Long topic_id){
      return topicService.deleteTopicById(topic_id);
    }
}
