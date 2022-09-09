public class Banco {
    //Classe para alguns testes manuais rápidos

    public static void main(String[] args) {
        Correntista diego = new Correntista(123456,"Diego");
        Conta contaDoDiego = new Conta(777,diego);
        contaDoDiego.setSenha(1234);
        try {
            contaDoDiego.sacar(100, 1234);
            System.out.printf("Sque bem sucedido!");
        }

        catch (SenhaInvalidaException e){
            int contTentativas = e.getNumeroDeTentativasInvalidas();
            System.out.printf("\nEssa foi a sua %d tentativa", contTentativas);
        }
        catch (SaldoInsuficienteException e)
        {
            float saldoFaltante = e.getSaldoFaltante();
            if (saldoFaltante < 1000)
            {
                System.out.printf("Saldo insuficiente.");
            }
        }
        //se for qualquer outro tipo de exceção cai nessa aqui. Como
        //se fosse um tratamento default
        catch (Exception e)
        {
            System.out.println("exceção não lista");
        }

    }


}
