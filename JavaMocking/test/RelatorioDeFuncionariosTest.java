import java.util.ArrayList;
import static org.junit.Assert.*;
import questao3.RelatorioDeFuncionarios;
import org.junit.Test;
import static org.mockito.Mockito.*;
import questao3.Funcionario;
import questao3.FuncionarioDAO;
import questao3.ReceitaFederal;

/**
 *
 * @author Marcelo Paglione
 */
public class RelatorioDeFuncionariosTest {

    public RelatorioDeFuncionariosTest() {
    }
    
    public ArrayList<Funcionario> getFuncionariosRandom(){
        return getFuncionariosRandom(null);
    }
    
    public ArrayList<Funcionario> getFuncionariosRandom(String cpf){
        ArrayList<Funcionario> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Funcionario f = new Funcionario();
            f.setCpf(cpf);
            list.add(f);
        }
        return list;
    }

    //Existem 2 funcionários na categoria “tecnico” que não estão com o CPF bloqueado.    
    @Test
    public void categoriaTecnico() {
        FuncionarioDAO funcDao = mock(FuncionarioDAO.class);
        ArrayList<Funcionario> funcionarios = getFuncionariosRandom("tecnico");
        when(funcDao.getFuncionariosBy("tecnico")).thenReturn(funcionarios);                
        
        RelatorioDeFuncionarios rdf = new RelatorioDeFuncionarios(funcDao);
        
        ReceitaFederal rf = mock(ReceitaFederal.class);
        when(rf.isCPFBloqueado(any())).thenReturn(false);
        
        rdf.setRf(rf);
        
        int quantidadeBloqueados = rdf.getFuncComCPFBloqueado("tecnico");
        
        assertEquals(0, quantidadeBloqueados);        
    }
    
    //Existe 1 funcionário na categoria “analista” que está com o CPF bloqueado.
    @Test
    public void categoriaAnalista() {
        FuncionarioDAO funcDao = mock(FuncionarioDAO.class);
        ArrayList<Funcionario> funcionarios = getFuncionariosRandom();
        funcionarios.get(0).setCpf("047225509-66");
        when(funcDao.getFuncionariosBy("analista")).thenReturn(funcionarios);                
        
        RelatorioDeFuncionarios rdf = new RelatorioDeFuncionarios(funcDao);
        
        ReceitaFederal rf = mock(ReceitaFederal.class);
        when(rf.isCPFBloqueado("047225509-66")).thenReturn(true).thenReturn(false);
        
        rdf.setRf(rf);
        
        int quantidadeBloqueados = rdf.getFuncComCPFBloqueado("analista");
        
        assertEquals(1, quantidadeBloqueados);        
    }
    //Existem 4 funcionários na categoria “gerente” com os CPFs: (123456789-00, 111222333-44,654321987-23, 098876654-99), 
    //sendo que os CPFs 111222333-44 e 098876654-99 estão bloqueados.
    @Test
    public void categoriaGerente() {
        FuncionarioDAO funcDao = mock(FuncionarioDAO.class);
        ArrayList<Funcionario> funcionarios = getFuncionariosRandom();
        funcionarios.get(0).setCpf("123456789-00");
        funcionarios.get(1).setCpf("111222333-44");
        funcionarios.get(2).setCpf("654321987-23");
        funcionarios.get(3).setCpf("098876654-99");
        when(funcDao.getFuncionariosBy("gerente")).thenReturn(funcionarios);                
        
        RelatorioDeFuncionarios rdf = new RelatorioDeFuncionarios(funcDao);
        
        ReceitaFederal rf = mock(ReceitaFederal.class);
        when(rf.isCPFBloqueado("111222333-44")).thenReturn(true);
        when(rf.isCPFBloqueado("098876654-99")).thenReturn(true).thenReturn(false);
        
        rdf.setRf(rf);
        
        int quantidadeBloqueados = rdf.getFuncComCPFBloqueado("gerente");
        
        assertEquals(2, quantidadeBloqueados);        
    }
}
