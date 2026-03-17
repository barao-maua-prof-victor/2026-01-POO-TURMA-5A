public class Main {
    public static void main(String[] args){

        Endereco enderecoProfessor = new Endereco(
                "Rua do professor",
                "234213",
                "34534645674"
        );
        Professor eu = new Professor(
                "Victor",
                "123",
                30,
                enderecoProfessor,
                "hoje"
        );

        Endereco enderecoAluno = new Endereco(
                "Rua do aluno",
                "987",
                "656576567"
        );
        Aluno fulano = new Aluno(
                "Fulano da Silva",
                "987",
                22,
                enderecoAluno,
                "111111111"
        );

        eu.falar("Boa noite, Turma A!!!!");
        fulano.falar("Olá, professor!!!!!");

        eu.darAula("Herança é o 3° pilar da OO!!!");

        fulano.fazerPergunta("O que é mesmo Herança?");

        eu.responderPergunta("É o 3° pilar da OO!!!!");

        System.out.println(eu);
        System.out.println(fulano);


    }
}

class Pessoa{
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

    public void falar(String fala){
        System.out.println(this.getNome() + ": " + fala);
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public void responderPergunta(String resposta){
        this.falar(resposta);
    }

    public void darAula(String conteudo){
        this.falar(conteudo);
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

    public void fazerPergunta(String pergunta){
        if(!pergunta.endsWith("?")){
            this.falar(pergunta + "?");
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

class Endereco{
    private String nomeDaRua;
    private String numero;
    private String cep;

    public Endereco(String nomeDaRua, String numero, String cep) {
        this.nomeDaRua = nomeDaRua;
        this.numero = numero;
        this.cep = cep;
    }

    public String getNomeDaRua() {
        return nomeDaRua;
    }

    public void setNomeDaRua(String nomeDaRua) {
        this.nomeDaRua = nomeDaRua;
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
                "nomeDaRua='" + nomeDaRua + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}




