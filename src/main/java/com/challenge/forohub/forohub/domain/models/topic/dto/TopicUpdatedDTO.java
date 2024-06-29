package com.challenge.forohub.forohub.domain.models.topic.dto;

import lombok.Data;

@Data
public class TopicUpdatedDTO {
    private String title, message, course;

    public TopicUpdatedDTO(TopicValidationDTO validationDTO) {
        if (!validationDTO.title().isBlank()) {
            this.title = validationDTO.title();
        } else {
            this.title = "No se ha modificado el título";
        }
        if (!validationDTO.message().isBlank()) {
            this.message = validationDTO.message();
        } else {
            this.message = "No se ha modificado el mensaje";
        }
        if (!validationDTO.course().isBlank()) {
            this.course = validationDTO.course();
        } else {
            this.course = "No se ha modificado el curso";
        }
    }

}
