/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

public class Veiculo {

    private String placa;
    private int IdCliente;

    public Veiculo(String placa, int IdCliente) {
        this.placa = placa;
        this.IdCliente = IdCliente;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdCliente(){
        return this.IdCliente;
    }

}
