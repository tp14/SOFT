import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class BibliotecaTest {
  private Repositorio repo = new Repositorio();
  private Biblioteca lib = new Biblioteca(repo);
 
  
  Usuario usuario1 = new Usuario("1", "Draco Malfoy");
	Usuario usuario2 = new Usuario("2", "Hermione Granger");
	Usuario usuario3 = new Usuario("3", "Alvo Dumbledore");
  Usuario usuario4 = new Usuario("4", "Ron Weasley");
  Usuario usuario5 = new Usuario("5", "Severus Snape");

  Livro book1 = new Livro("1", "Harry POtter");
	Livro book2 = new Livro("2", "Percy Jackson");
	Livro book3 = new Livro("3", "As Crônicas de Nárnia");
	Livro book4 = new Livro("4", "O segredo");
	Livro book5 = new Livro("5", "Inteligência emocional");
	Livro book6 = new Livro("6", "Em busca do sentido");

 //---------------------------------------------------------------------------------------
  @Test
  public void q1() throws Exception { //efetue o empréstimo de 3 livros a 1 usuário

  lib.adicionarLivroAcervo(book1);
  lib.adicionarLivroAcervo(book3);
  lib.adicionarLivroAcervo(book5);

  lib.emprestarLivro(book1, usuario1);
  lib.emprestarLivro(book3, usuario1);
  lib.emprestarLivro(book5, usuario1);

  List<Livro> livrosEmprestados = lib.livrosEmprestadosUsuario(usuario1);

  assertEquals(3, livrosEmprestados.size());
}
//---------------------------------------------------------------------------------------
  @Test
  public void q2() throws Exception { 
    //verifique se um dado usuário, que não possui empréstimos, não tem de fato livros associados a ele.
  
    List<Livro> livrosEmprestados = lib.livrosEmprestadosUsuario(usuario2);

    assertEquals(0, livrosEmprestados.size());
  }


//---------------------------------------------------------------------------------------
  @Test
  public void q3() throws Exception {//removerLivro

    lib.adicionarLivroAcervo(book6);
    lib.adicionarLivroAcervo(book4);
    lib.adicionarLivroAcervo(book2);
    lib.emprestarLivro(book6, usuario3);
    lib.emprestarLivro(book4, usuario3);
    lib.emprestarLivro(book2, usuario3);
    lib.receberLivroEmprestado(book6);
    lib.receberLivroEmprestado(book4);

    List<Livro> livrosEmprestados = lib.livrosEmprestadosUsuario(usuario3);
    
    assertFalse("Não consta book6", livrosEmprestados.contains(book6));
    assertFalse("Não consta book4", livrosEmprestados.contains(book4));

    assertEquals(1, livrosEmprestados.size());
  }

//---------------------------------------------------------------------------------------
  @Test
  public void q4() throws Exception {
    // verificar se um determinado livro, que se encontra emprestado para um dado usuário, 
    //não pode ser emprestado para um outro usuário
    
    lib.adicionarLivroAcervo(book5);
    lib.emprestarLivro(book5, usuario4);

    Exception exception = assertThrows(Exception.class, () -> lib.emprestarLivro(book5, usuario5));
    assertEquals("O Livro já está emprestado!", exception.getMessage());
  }
}