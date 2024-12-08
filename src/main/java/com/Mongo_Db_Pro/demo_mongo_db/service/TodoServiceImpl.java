package com.Mongo_Db_Pro.demo_mongo_db.service;

import com.Mongo_Db_Pro.demo_mongo_db.exception.TodoCollectionException;
import com.Mongo_Db_Pro.demo_mongo_db.model.TodoDTO;
import com.Mongo_Db_Pro.demo_mongo_db.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException{
        Optional <TodoDTO>todoOptional =todoRepository.findByTodo(todoDTO.getUsername());
        if(todoOptional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }else {
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }
    }

    @Override
    public List<TodoDTO> getAllTodos(){
        List<TodoDTO> todoDTOS = todoRepository.findAll();
        if(!todoDTOS.isEmpty()){
            return todoDTOS;
        }else {
            return new ArrayList<TodoDTO>();
        }
    }

    @Override
    public TodoDTO getTodoById(String id) throws TodoCollectionException {
    Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(id);
     if(! optionalTodoDTO.isPresent()){
         throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
     }else{
         return optionalTodoDTO.get();
     }
    }

    @Override
    public void updateTodoById(String id, TodoDTO todoDTO) throws TodoCollectionException {

        Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(id);
        Optional<TodoDTO> optionalTodoDTOWithSameName =todoRepository.findByTodo(todoDTO.getUsername());

        if(optionalTodoDTO.isPresent()){
            if(optionalTodoDTOWithSameName.isPresent() && !optionalTodoDTOWithSameName.get().getId().equals(id)){
                throw new TodoCollectionException((TodoCollectionException.TodoAlreadyExists()));
            }

            TodoDTO todoDTOUpdate = optionalTodoDTO.get();
            todoDTOUpdate.setUsername(todoDTO.getUsername() != null ? todoDTO.getUsername():todoDTOUpdate.getUsername());
            todoDTOUpdate.setDescription(todoDTO.getDescription() != null ? todoDTO.getDescription():todoDTOUpdate.getDescription());
            todoDTOUpdate.setCompleted(todoDTO.getCompleted() != null ? todoDTO.getCompleted():todoDTOUpdate.getCompleted());
            todoDTOUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTOUpdate);
        }else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException {
        Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(id);
        if(! optionalTodoDTO.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else{
            todoRepository.deleteById(id);
        }
    }

}
