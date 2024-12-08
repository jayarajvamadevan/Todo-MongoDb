package com.Mongo_Db_Pro.demo_mongo_db.service;

import com.Mongo_Db_Pro.demo_mongo_db.exception.TodoCollectionException;
import com.Mongo_Db_Pro.demo_mongo_db.model.TodoDTO;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface TodoService {

   public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException;
   public List<TodoDTO> getAllTodos();
   public TodoDTO getTodoById(String id) throws TodoCollectionException;
   public void updateTodoById(String id,TodoDTO todoDTO) throws TodoCollectionException;
   public void deleteTodoById(String id) throws TodoCollectionException;
}
