package com.fred.cursoomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fred.cursoomc.domain.Categoria;
import com.fred.cursoomc.domain.Cidade;
import com.fred.cursoomc.domain.Cliente;
import com.fred.cursoomc.domain.Endereco;
import com.fred.cursoomc.domain.Estado;
import com.fred.cursoomc.domain.ItemPedido;
import com.fred.cursoomc.domain.Pagamento;
import com.fred.cursoomc.domain.PagamentoComBoleto;
import com.fred.cursoomc.domain.PagamentoComCartao;
import com.fred.cursoomc.domain.Pedido;
import com.fred.cursoomc.domain.Produto;
import com.fred.cursoomc.domain.enums.EstadoPagamento;
import com.fred.cursoomc.domain.enums.TipoCliente;
import com.fred.cursoomc.repositories.CategoriaRepository;
import com.fred.cursoomc.repositories.CidadeRepository;
import com.fred.cursoomc.repositories.ClienteRepository;
import com.fred.cursoomc.repositories.EnderecoRepository;
import com.fred.cursoomc.repositories.EstadoRepository;
import com.fred.cursoomc.repositories.ItemPedidoRepository;
import com.fred.cursoomc.repositories.PagamentoRepository;
import com.fred.cursoomc.repositories.PedidoRepository;
import com.fred.cursoomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 =  new Categoria(null,"Informatica");
		Categoria cat2 =  new Categoria(null,"Escritório");
        Produto p1 = new Produto(null,"computador",2000);
        Produto p2 = new Produto(null,"impressora",800);
        Produto p3 = new Produto(null,"mouse",80);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");
        
        Cidade c1 = new Cidade(null,"Uberlândia",est1);
        Cidade c2 = new Cidade(null,"São Paulo",est2);
        Cidade c3 = new Cidade(null,"Campinas",est2);
         
        
        
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva" ,"maria@gmail.com","12312313",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1231312321","112321312312"));
		Endereco e1 = new Endereco(null, "Rua flores", "330", "casa", "g1", "asdfsd", cli1, c1);
		cli1.getEnderecos().add(e1);
		
		clienteRepository.save(cli1);
		enderecoRepository.save(e1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("10/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/09/2018 10:32"), cli1, e1);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, 
				EstadoPagamento.PENDENTE, ped2, sdf.parse("10/09/2018 10:32"), null);
		
		ped2.setPagamento(pag2);
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		ItemPedido itemPedido = new ItemPedido(ped1, p1, 0, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(ped1, p3, 0, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        
		ped1.getItens().addAll(Arrays.asList(itemPedido,itemPedido2));
		ped2.getItens().addAll(Arrays.asList(itemPedido3));
		
	   p1.getItens().addAll(Arrays.asList(itemPedido));
	   p2.getItens().addAll(Arrays.asList(itemPedido3));
	   p3.getItens().addAll(Arrays.asList(itemPedido2));
 
	    itemPedidoRepository.saveAll(Arrays.asList(itemPedido,itemPedido2,itemPedido3));
	   
		
		
	}

	
}

