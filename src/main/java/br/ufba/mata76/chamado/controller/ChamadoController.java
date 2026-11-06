package br.ufba.mata76.chamado.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import br.ufba.mata76.chamado.model.Chamado; 
import br.ufba.mata76.chamado.model.Prioridade; 
import br.ufba.mata76.chamado.model.Status; 

@RestController
@RequestMapping("/chamados")
public class ChamadoController{
   private final List<Chamado> chamados = new ArrayList<>(
       List.of(
           new Chamado(
                1,
               "Esqueci minha senha",
               "Adriana Lima",
               "RH",
               Prioridade.MEDIA,
               Status.ABERTO
           ),
           new Chamado(
                2,
               "Mouse não funciona",
               "Camila Santana",
               "Financeiro",
               Prioridade.BAIXA,
               Status.EM_ANDAMENTO
           ),
           new Chamado(
                3,
               "Impressora não conecta",
               "Danilo Mesquita",
               "Marketing",
               Prioridade.ALTA,
               Status.ABERTO
           ),
           new Chamado(
                4,
               "Computador não liga",
               "Evandro Mesquita",
               "RH",
               Prioridade.ALTA,
               Status.RESOLVIDO
           ),
           new Chamado(
                5,
               "Computador não conecta com wifi",
               "Fernanda Gentil",
               "Redação",
               Prioridade.MEDIA,
               Status.EM_ANDAMENTO
           ),
           new Chamado(
                6,
               "Recuperar senha",
               "Gustavo Kirten",
               "Financeiro",
               Prioridade.MEDIA,
               Status.ABERTO
           ),
           new Chamado(
                7,
               "Teclado não funciona",
               "Humberto Leão",
               "Operações",
               Prioridade.BAIXA,
               Status.RESOLVIDO
           ),
           new Chamado(
                8,
               "Substituição cartão de acesso",
               "Iana Marques",
               "Marketing",
               Prioridade.ALTA,
               Status.EM_ANDAMENTO
           ),
           new Chamado(
                9,
               "Projeto não liga",
               "Julia Lins",
               "Marketing",
               Prioridade.MEDIA,
               Status.RESOLVIDO
           ),
           new Chamado(
                10,
               "Substitução carregador do computador",
               "Livia Magnavita",
               "Redação",
               Prioridade.BAIXA,
               Status.ABERTO
           )
       )
   );

   @GetMapping
   public List<Chamado> listar(){
       return chamados;
   }

   }