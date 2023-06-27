package app.netlify.iambarroso.escolaapi.exceptions;

public class NotFoundException extends BusinessException{

    public static final String PROFESSOR_NOT_FOUND = "Professor(a) não encontrado(a)";
    public static final String ESTUDANTE_NOT_FOUND = "Estudante não encontrado(a)";
    public static final String DISCIPLINA_NOT_FOUND = "Disciplina não encontrada";
    public static final String AULA_NOT_FOUND = "Aula não encontrada";
    public static final String TURMA_NOT_FOUND = "Turma não encontrada";
    public static final String FREQUENCIA_NOT_FOUND = "Frequência não encontrada";
    public NotFoundException(String message) {
        super(message);
    }
}
