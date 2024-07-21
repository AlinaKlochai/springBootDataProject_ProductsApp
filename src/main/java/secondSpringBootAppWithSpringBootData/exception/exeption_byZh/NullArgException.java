package secondSpringBootAppWithSpringBootData.exception.exeption_byZh;

public class NullArgException extends RuntimeException{
    public NullArgException(String field){
        super("{\"fieldName\" : \""+field+"\", \"message\" : \"must not be null\"}");
    }
}
