package mvc.controller;

import mvc.dao.UsoDeVagaDAO;
import mvc.dao.VagaDAO;
import mvc.model.UsoDeVaga;
import mvc.model.Vaga;
import mvc.model.VagaDefault;
import mvc.model.VagaIdoso;
import mvc.model.VagaPcd;
import mvc.model.VagaVip;
import mvc.model.Veiculo;
import mvc.view.EstacionarCarroView;

public class EstacionarCarrroController {
    private UsoDeVagaDAO usoDeVagaDAO;
    private EstacionarCarroView view;

public EstacionarCarrroController(){
    this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
    this.view = new EstacionarCarroView();

    this.view.btnEstacionar().addActionListener((e)->{
        estacionar();
    });

    this.view.setVisible(true);
}

public void estacionar(){
    String IdCliente = view.getTxtIdCliente().getText();
    String Placa = view.getTxtPlaca().getText();
    String numeroVaga = view.getTxtNumeroVaga().getText();

    int id = Integer.parseInt(IdCliente);

    Vaga vaga = VagaDAO.getInstance().pesquisarVagaPorNumero(numeroVaga);
    Veiculo veiculo = new Veiculo(Placa, id);

    if (vaga instanceof VagaDefault) {
        VagaDefault vagaDefault = new VagaDefault(numeroVaga);
        UsoDeVaga u = new UsoDeVaga(veiculo, vagaDefault);
    } else if (vaga instanceof VagaIdoso) {
        VagaIdoso vagaIdoso = new VagaIdoso(numeroVaga);
        UsoDeVaga u2 = new UsoDeVaga(veiculo, vagaIdoso);
    } else if (vaga instanceof VagaVip) {
        VagaVip vagaVip = new VagaVip(numeroVaga);
        UsoDeVaga u3 = new UsoDeVaga(veiculo, vagaVip);
    } else if (vaga instanceof VagaPcd) {
        VagaPcd vagaPcd = new VagaPcd(numeroVaga);
        UsoDeVaga u4 = new UsoDeVaga(veiculo, vagaPcd);
    }else{
        System.out.println("Nenhum tipo salvo");
    }



}
}
