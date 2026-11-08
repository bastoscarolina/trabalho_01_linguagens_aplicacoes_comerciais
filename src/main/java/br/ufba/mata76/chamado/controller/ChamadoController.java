package br.ufba.mata76.chamado.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import br.ufba.mata76.chamado.model.Chamado; 
import br.ufba.mata76.chamado.model.Prioridade; 
import br.ufba.mata76.chamado.model.Status; 
import org.springframework.web.server.ResponseStatusException;

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

   @GetMapping("/{id}")
   public Chamado buscarChamadozPorId(@PathVariable Integer id){
       return chamados.stream()
       .filter(chamado -> chamado.id().equals(id))
       .findFirst()
       .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
   }

   @GetMapping("/setor")
   public List<Chamado> filtrarChamadoPorSetor(@RequestParam("nome") String setor){
       return chamados.stream()
       .filter(chamado -> chamado.setor()
                        .toLowerCase()
                        .contains(setor.toLowerCase()))
       .toList();
   }

   @GetMapping("/prioridade")
   public List<Chamado> filtrarChamadoPorPrioridade(@RequestParam("nivel") String prioridade){
       return chamados.stream()
       .filter(chamado -> chamado.prioridade()
                        .name()
                        .toUpperCase()
                        .contains(prioridade.toUpperCase()))
       .toList();
   }

   @GetMapping("/busca")
   public List<Chamado> filtrarChamadoPorTitulo(@RequestParam("texto") String titulo){
       return chamados.stream()
       .filter(chamado -> chamado.titulo()
                        .toLowerCase()
                        .contains(titulo.toLowerCase()))
       .toList();
   }

   @GetMapping("/criticos")
   public List<Chamado> filtrarChamadosCriticos(){
       return chamados.stream()
       .filter(chamado -> chamado.prioridade()
                        .name() == "ALTA" && 
                        chamado.status()
                        .name() != "RESOLVIDO")
       .toList();
   }

   @GetMapping("/resumo")
   public Map<String, Long> resumirChamados(){
        Map<String, Long> resumo = new HashMap<>();
        resumo.put("ABERTOS",chamados.stream().filter(chamado -> 
                        chamado.status()
                        .name() == "ABERTO").count());
        resumo.put("EM_ANDAMENTO",chamados.stream().filter(chamado -> 
                        chamado.status()
                        .name() == "EM_ANDAMENTO").count());  
        resumo.put("RESOLVIDOS",chamados.stream().filter(chamado -> 
                        chamado.status()
                        .name() == "RESOLVIDO").count());  
         resumo.put("TOTAL",(long)chamados.size());             
       return resumo;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Chamado cadastrar(@RequestBody Chamado chamado){
        Integer id = chamados.stream()
                            .mapToInt(Chamado :: id)
                            .max()
                            .orElse(0) + 1;
        Chamado novoChamado = new Chamado(
            id,
            chamado.titulo(),
            chamado.solicitante(),
            chamado.setor(),
            chamado.prioridade(),
            Status.ABERTO
        );
       chamados.add(novoChamado);
       return novoChamado;
   }

   }