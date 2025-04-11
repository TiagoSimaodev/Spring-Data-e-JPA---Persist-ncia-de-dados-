package projeto.spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;
import projeto.spring.data.dao.InterfaceTelefone;
import projeto.spring.data.model.Telefone;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTeste {
	
	@Autowired
	private  InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
		
	
	@Test
	public void testeInsert() {
		UsuarioSpringData  usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("javaspring@gmail.com");
		usuarioSpringData.setIdade(19);
		usuarioSpringData.setLogin("teste123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("raul pedro");
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuários cadastrados: "+ interfaceSpringDataUser.count());
		
	}
	
	@Test
	public void testeConsulta() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		
		System.out.println("Nome: " + usuarioSpringData.get().getNome());
		System.out.println("Idade: " + usuarioSpringData.get().getIdade());
		System.out.println("Email: " + usuarioSpringData.get().getEmail());
		
		for (Telefone telefone : usuarioSpringData.get().getTelefone()) {
			System.out.println("Número: " + telefone.getNumero());
			System.out.println("Tipo: " + telefone.getTipo());
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getId());
			System.out.println("Usuários cadastrados: "+ interfaceSpringDataUser.count());
			
		}
		
	}
	
	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("francisco Rodrigues");
		
		interfaceSpringDataUser.save(data);
	}
	
	@Test
	public void testeDelete() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		interfaceSpringDataUser.delete(usuarioSpringData.get());
	}
	
	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Tiago");
		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getId());

			
		}
		
	} 
	@Test
	public void testeConsultaNomeParam() {
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("francisco");
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getId());

			
		}
	
	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("raul pedro");
	}
	
	@Test
	public void testeUpadateEmailPorNome() {
		 interfaceSpringDataUser.updateEmailPorNome("testeemailspringdata@gmail.com", "Tiago Simao");
	}
		
	

	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("casa");
		telefone.setNumero("376171721");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		interfaceTelefone.save(telefone);
	}
	
}

