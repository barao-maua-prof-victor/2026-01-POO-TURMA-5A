public class Main {

    public static void main(String[] args){

        Endereco enderecoProfessor = new Endereco(
                "Rua do professor",
                "123",
                "11111111"
        );
        Professor eu = new Professor(
                "Victor", "123", 25, enderecoProfessor, "hoje"
        );

        Endereco enderecoProfessor2 = new Endereco(
                "Rua do professor",
                "123",
                "11111111"
        );
        Professor eu2 = new Professor(
                "Ciclano",
                "321",
                34,
                enderecoProfessor2,
                "ontem"
        );

        Endereco enderecoAluno = new Endereco("Rua do aluno", "123", "2222222");
        Aluno fulano = new Aluno(
                "Fulano", "987", 22, enderecoAluno,"214332141"
        );

        eu2.darAula("Aula de classe abstrata!!!!");

        eu.darAula("A herança é o 3° pilar da OO");
        fulano.fazerPergunta("O que é herança mesmo");
        eu.responderPergunta("É o 3° pilar da OO");

//        System.out.println(eu);
//        System.out.println(fulano);
        eu.gritar();
        fulano.gritar();

    }

}

abstract class Pessoa{
    private String nome;
    private String cpf;
    private int idade;
    private Endereco endereco;

    public Pessoa(String nome, String cpf, int idade, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.endereco = endereco;
    }

    public abstract void falar(String fala);

    public void gritar(){
        this.falar(this.getNome() + ": Estou gritando com você...");
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", endereco=" + endereco +
                '}';
    }
}

class Professor extends Pessoa{
    private String dataContratacao;

    public Professor(String nome, String cpf, int idade, Endereco endereco, String dataContratacao) {
        super(nome, cpf, idade, endereco);
        this.dataContratacao = dataContratacao;
    }

    @Override
    public void falar(String fala) {
        System.out.println("[PROFESSOR]: " + this.getNome() + ": " + fala);
    }

    public void darAula(String conteudo){
        this.falar(conteudo);
    }

    public void responderPergunta(String resposta){
        this.falar(resposta);
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "dataContratacao='" + dataContratacao + '\'' +
                ", " + super.toString() +
                '}';
    }
}

class Aluno extends Pessoa{

    private String RA;

    public Aluno(String nome, String cpf, int idade, Endereco endereco, String RA) {
        super(nome, cpf, idade, endereco);
        this.RA = RA;
    }

    @Override
    public void falar(String fala) {
        System.out.println("[ALUNO]: " + this.getNome() + ": " + fala);
    }

    public void fazerPergunta(String pergunta){
        if(!pergunta.endsWith("?")){
            this.fazerPergunta(pergunta + "?");
        }else{
            this.falar(pergunta);
        }
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "RA='" + RA + '\'' +
                ", " + super.toString() +
                '}';
    }
}

class Endereco {
    private String endereco;
    private String numero;
    private String cep;

    public Endereco(String endereco, String numero, String cep) {
        this.endereco = endereco;
        this.numero = numero;
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "endereco='" + endereco + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}



