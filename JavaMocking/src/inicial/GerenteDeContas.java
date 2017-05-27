/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicial;

/**
 *
 * @author utfpr
 */
public interface GerenteDeContas {
    public long recuperarSaldo(Conta conta);
    
    public long efetuarSaque(Conta conta, long valor);
    
    public Conta acharConta(Cliente cliente);
}
