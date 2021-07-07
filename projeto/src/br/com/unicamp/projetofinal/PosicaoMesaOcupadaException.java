package br.com.unicamp.projetofinal;

public class PosicaoMesaOcupadaException extends Exception{
    public PosicaoMesaOcupadaException(String message) {
        super(message);
    }

    public PosicaoMesaOcupadaException() {
        super();
    }

    public PosicaoMesaOcupadaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PosicaoMesaOcupadaException(Throwable cause) {
        super(cause);
    }
}
