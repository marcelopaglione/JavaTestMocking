import org.junit.Test;
import static org.junit.Assert.*;
import questao2.Classificador;
import questao2.Pessoa;

/**
 *
 * @author Marcelo Paglione
 */
public class ClassificadorTest {

    String nome = "Nome";
    String valorEsperado;

    // (i) um valor de idade inválido
    @Test(expected = IllegalArgumentException.class)
    public void valorIdadeInvalido() {

        Pessoa p = new Pessoa(nome, -1);

        Classificador classificador = new Classificador();

        classificador.definirFaixaEtaria(p);
    }

    //(ii) uma pessoa que é criança; 
    @Test
    public void valorIdadeCrianca() {
        //Classificador classificadorMock = mock(Classificador.class);
        Pessoa p = new Pessoa(nome, 10);

        Classificador classificador = new Classificador();

        String faixaEtaria = classificador.definirFaixaEtaria(p);

        valorEsperado = nome + " eh crianca";

        assertEquals(valorEsperado, faixaEtaria);
    }

    //(iii) uma pessoa que é adolescente; 
    @Test
    public void valorIdadeAdolescente() {
        //Classificador classificadorMock = mock(Classificador.class);
        Pessoa p = new Pessoa(nome, 17);

        Classificador classificador = new Classificador();

        String faixaEtaria = classificador.definirFaixaEtaria(p);

        valorEsperado = nome + " eh adolescente";

        assertEquals(valorEsperado, faixaEtaria);
    }

    //(iv) uma pessoa que é adulta; 
    @Test
    public void valorIdadeAdulto() {
        //Classificador classificadorMock = mock(Classificador.class);
        Pessoa p = new Pessoa(nome, 59);

        Classificador classificador = new Classificador();

        String faixaEtaria = classificador.definirFaixaEtaria(p);

        valorEsperado = nome + " eh adulto";

        assertEquals(valorEsperado, faixaEtaria);
    }

    //(ii) uma pessoa que é idosa. 
    @Test
    public void valorIdadeIdoso() {
        //Classificador classificadorMock = mock(Classificador.class);
        Pessoa p = new Pessoa(nome, 60);

        Classificador classificador = new Classificador();

        String faixaEtaria = classificador.definirFaixaEtaria(p);

        valorEsperado = nome + " eh idoso";

        assertEquals(valorEsperado, faixaEtaria);
    }

}
