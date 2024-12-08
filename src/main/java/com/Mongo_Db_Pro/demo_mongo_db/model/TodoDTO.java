package com.Mongo_Db_Pro.demo_mongo_db.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "BoolField cannot be null")
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
}
