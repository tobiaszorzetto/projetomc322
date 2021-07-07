package br.com.unicamp.projetofinal;

public class ManaInsuficienteException extends Exception{
    public ManaInsuficienteException(String message) {
        super(message);
    }

    public ManaInsuficienteException() {
        super();
    }

    public ManaInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManaInsuficienteException(Throwable cause) {
        super(cause);
    }
}
