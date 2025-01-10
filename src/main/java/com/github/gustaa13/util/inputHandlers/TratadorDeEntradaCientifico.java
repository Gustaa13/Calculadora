package com.github.gustaa13.util.inputHandlers;

public class TratadorDeEntradaCientifico extends TratadorDeEntradaPadrao {

    private String operadores;
    //private String caracteresespeciais;
    private Integer contadorDeParentesesAbertos;
    private Integer contadorDeParentesesFechados;
    
    public TratadorDeEntradaCientifico(StringBuilder expressao){
        super(expressao, "+-x÷^√(", ",");

        operadores = "+-x÷^√(";
        //caracteresespeciais = ",(";
        contadorDeParentesesAbertos = 0;
        contadorDeParentesesFechados = 0;
    }

    protected TratadorDeEntradaCientifico(StringBuilder expressao, String operadores, String caracteresespeciais){
        super(expressao, operadores, caracteresespeciais);

        this.operadores = operadores;
        //this.caracteresespeciais = caracteresespeciais;
        contadorDeParentesesAbertos = 0;
        contadorDeParentesesFechados = 0;
    }

    protected Integer getContadorDeParentesesAbertos(){
        return contadorDeParentesesAbertos;
    }

    protected void setContadorDeParentesesAbertos(Integer contadorDeParentesesAbertos){
        this.contadorDeParentesesAbertos = contadorDeParentesesAbertos;
    }

    protected Integer getContadorDeParentesesFechados(){
        return contadorDeParentesesFechados;
    }

    protected void setContadorDeParentesesFechados(Integer contadorDeParentesesFechados){
        this.contadorDeParentesesFechados = contadorDeParentesesFechados;
    }

    @Override
    public StringBuilder getExpressaoFinal(){
        if(contadorDeParentesesAbertos > contadorDeParentesesFechados){
            if(getExpressao().charAt(getExpressao().length() - 1) == '(') getExpressao().append("0)");
            else for(int i = 0; i < contadorDeParentesesAbertos - contadorDeParentesesFechados; i++) getExpressao().append(")");         
        }
        return getExpressao();
    }

    @Override
    public void apagarExpressao(){
        contadorDeParentesesAbertos = 0;
        contadorDeParentesesFechados = 0;
        super.apagarExpressao();
    }

    @Override
    protected void tratadorNumerico(String caractere){
        if(getExpressao().length() > 0 && (getExpressao().charAt(getExpressao().length() - 1) == ')')) return;
        super.tratadorNumerico(caractere);
    }

    @Override
    public void apagarCaractereDaExpressao(){
        if(getExpressao().length() <= 0) return;
        if(getExpressao().charAt(getExpressao().length() - 1) == '('){
            contadorDeParentesesAbertos--;
            if(getExpressao().charAt(getExpressao().length() - 2) == 's'){
                getExpressao().delete(getExpressao().length() - 4, getExpressao().length() - 1);
            }
        } 
        super.apagarCaractereDaExpressao();
    }

    protected void tratadorDeParenteses(String caractere){
        if(getContadorDeParentesesAbertos() == 0 && getContadorDeAlgarismos() == 0){
            getExpressao().append("(");
            setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
        }else if(String.valueOf(getExpressao().charAt(getExpressao().length() - 1)).matches("[0-9]") && getContadorDeParentesesAbertos() > 0){
            getExpressao().append(")");
            setContadorDeParentesesFechados(getContadorDeParentesesFechados() + 1);
        }else if(operadores.contains(String.valueOf(getExpressao().charAt(getExpressao().length() - 1)))){
            getExpressao().append("(");
            setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
        }else if(getExpressao().charAt(getExpressao().length() - 1) == ')' && (getContadorDeParentesesAbertos() > getContadorDeParentesesFechados())){
            getExpressao().append(")");
            setContadorDeParentesesFechados(getContadorDeParentesesFechados() + 1);
        }
    }

    protected void tratadorDeValorAbsoluto(){
        if(getContadorDeParentesesAbertos() == 0 && getContadorDeAlgarismos() == 0){
            getExpressao().append("abs(");
            setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
        }else if(operadores.contains(String.valueOf(getExpressao().charAt(getExpressao().length() - 1)))){
            getExpressao().append("abs(");
            setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
        }else if(getExpressao().charAt(getExpressao().length() - 1) == '('){
            getExpressao().append("abs(");
            setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
        }
    }

    protected void tratadorDeMaisOuMenos(){
        if(getContadorDeAlgarismos() <= 0) return;
        if(posicaoDoUltimoNumero() - 2 >= 0 && getExpressao().substring(posicaoDoUltimoNumero() - 2, posicaoDoUltimoNumero()).equals("(-")){
            getExpressao().delete(posicaoDoUltimoNumero() - 2, posicaoDoUltimoNumero());
            getExpressao().deleteCharAt(getExpressao().length() - 1);
        }else{
            getExpressao().insert(posicaoDoUltimoNumero(), "(-");
            getExpressao().append(")");
        }
    }

    protected void tratadorDeExponenciacao(){
        if(getContadorDeAlgarismos() <= 0) return;
        getExpressao().append("^");
        setPermitirVirgula(true);
        setContadorDeAlgarismos(0);
    }

    protected void tratadorDeRadiciacao(){
        if(getContadorDeAlgarismos() == 0){
            getExpressao().append("√");
        }else{
            getExpressao().append("x√");
        }
    }

    @Override
    public void adicionarCaracterNaExpressao(String caractere){

        super.adicionarCaracterNaExpressao(caractere);

        if(getExpressao().length() >= 100) return;
        if(caractere.equals("( )")){
            tratadorDeParenteses(caractere);
        }else if(caractere.equals("|x|")){
            tratadorDeValorAbsoluto();
        }else if(caractere.equals("+/-")){
            tratadorDeMaisOuMenos();
        }else if(caractere.equals("xʸ")){
            tratadorDeExponenciacao();
        }else if(caractere.equals("√")){
           tratadorDeRadiciacao(); 
        }
    }
}
