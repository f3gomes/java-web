import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name = "JogoBean")
public class JogoBean {
    private List<Jogo> jogos = new ArrayList<>();
    private Jogo jogo = new Jogo();
    private Integer proximoId = 1;
    private Random random = new Random();

    public List<Jogo> getJogos() {
        return jogos;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public void salvarJogo() {
        jogo.setId(proximoId++);
        jogo.setData(new Date());
        jogo.setNumeroSecreto(random.nextInt(5) + 1);
        jogo.setResultado(jogo.getNumeroAposta() == jogo.getNumeroSecreto() ? "acertou" : "não acertou");

        jogos.add(jogo);
        jogo = new Jogo();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo cadastrado."));
    }

    public void excluirJogo(Jogo jogoExcluir) {
        jogos.remove(jogoExcluir);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo excluído."));
    }

    public String getQuantidadeJogos() {
        return jogos != null ? String.valueOf(jogos.size()) : "0" ;
    }
}