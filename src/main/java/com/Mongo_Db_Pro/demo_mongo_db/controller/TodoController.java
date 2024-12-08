package com.Mongo_Db_Pro.demo_mongo_db.controller;

import com.Mongo_Db_Pro.demo_mongo_db.exception.TodoCollectionException;
import com.Mongo_Db_Pro.demo_mongo_db.model.TodoDTO;
import com.Mongo_Db_Pro.demo_mongo_db.repository.TodoRepository;
import com.Mongo_Db_Pro.demo_mongo_db.service.TodoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List <TodoDTO> todos = todoService.getAllTodos();
            return new ResponseEntity<>(todos, todos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") String id) {
       try{
           return new ResponseEntity<>(todoService.getTodoById(id),HttpStatus.OK);
       }catch (TodoCollectionException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try{
            todoService.createTodo(todoDTO);
            return  new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK );
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable("id") String id ,@RequestBody TodoDTO todoDTO){
        try {
                todoService.updateTodoById(id,todoDTO);
                return new ResponseEntity<>("Updated Todo with id:" +id+" Successfully !",HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") String id){
       try {
           todoService.deleteTodoById(id);
           return new ResponseEntity<>("Deleted Todo id:" +id+ " Successfully",HttpStatus.OK);
       } catch (TodoCollectionException e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);

       }
    }


}
