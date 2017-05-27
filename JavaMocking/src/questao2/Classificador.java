/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questao2;

/**
 *
 * @author Marcelo Paglione
 */
public class Classificador {

    /**
     * Metodo retorna em qual faixa etaria esta a pessoa; lança RuntimeException
     * quando a idade eh invalida
     */
    public String definirFaixaEtaria(Pessoa p)
            throws RuntimeException {
        if (p.getIdade() < 0 || p.getIdade() >= 110) {
            throw new IllegalArgumentException("idade invalida");
        }

        int idade = p.getIdade();
        String tipo = "";
        if (idade <= 11) {
            tipo = "crianca";
        } else if (idade <= 18) {
            tipo = "adolescente";
        } else if (idade <= 59) {
            tipo = "adulto";
        } else {
            tipo = "idoso";
        }

        return p.getNome() + " eh " + tipo;
    }
}
