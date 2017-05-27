import inicial.Cliente;
import inicial.Conta;
import inicial.GerenteDeContas;
import inicial.SemFundosException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author marcelopaglione
 */
public class ClienteTest {
    
    public ClienteTest() {
    }

    @Test
    public void testeClienteSemSaldo() {
        GerenteDeContas gerenteMock = mock(GerenteDeContas.class);
        Conta contateste = new Conta();
        when(gerenteMock.acharConta(any())).thenReturn(contateste);
        when(gerenteMock.recuperarSaldo(contateste)).thenReturn(70l);
        
        Cliente cliente = new Cliente();        
        cliente.setGerenteDeContas(gerenteMock);
        try {
            cliente.efetuarSaque(100);
            fail();
        } catch (SemFundosException ex) {
            
        }
        
        verify(gerenteMock,times(1)).acharConta(any());
        verify(gerenteMock, times(1)).recuperarSaldo(contateste);
    }
    
    @Test
    public void testeClienteComSaldo() {
        GerenteDeContas gerenteMock = mock(GerenteDeContas.class);
        Conta contateste = new Conta();
        when(gerenteMock.acharConta(any())).thenReturn(contateste);
        when(gerenteMock.recuperarSaldo(contateste)).thenReturn(170l,70l);
        when(gerenteMock.efetuarSaque(contateste, 100)).thenReturn(70l);
        
        Cliente cliente = new Cliente();        
        cliente.setGerenteDeContas(gerenteMock);
        try {
            long novoSaldo = cliente.efetuarSaque(100);            
            assertEquals(70,novoSaldo);
        } catch (SemFundosException ex) {
            
        }
       
        verify(gerenteMock,times(1)).acharConta(any());
        verify(gerenteMock, times(2)).recuperarSaldo(contateste);
        verify(gerenteMock,times(1)).efetuarSaque(contateste, 100);
    }
}
