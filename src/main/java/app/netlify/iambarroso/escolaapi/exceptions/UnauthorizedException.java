package app.netlify.iambarroso.escolaapi.exceptions;

public class UnauthorizedException extends BusinessException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
