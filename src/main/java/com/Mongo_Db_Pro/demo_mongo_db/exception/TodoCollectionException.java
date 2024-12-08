package com.Mongo_Db_Pro.demo_mongo_db.exception;

public class TodoCollectionException  extends Exception{

    public TodoCollectionException(String message){
        super(message);
    }
    public static String NotFoundException(String id){
        return ("Todo with id:" +id+ " not found");
    }
    public static String TodoAlreadyExists(){

        return "Todo with given name already exists";
    }
}
