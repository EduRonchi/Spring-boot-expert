package io.github.eduronchi;

import io.github.eduronchi.domain.entity.Cliente;
import io.github.eduronchi.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args ->  {
            System.out.println("Salvando clientes");
            clientes.salvar(new Cliente("Eduardo"));
            clientes.salvar(new Cliente("Outro Cliente"));

           List<Cliente> todosClientes = clientes.obterTodos();
           todosClientes.forEach(System.out::println);

           System.out.println("Atualizando clientes");
           todosClientes.forEach(c -> {
               c.setNome(c.getNome() + " atualizado.");
               clientes.atualizar(c);
           });

            if(todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            }else {
                todosClientes.forEach(System.out::println);
           }
        };
   }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);


    }
}
