package ii_teste;

public class pedido {

	String tipo=null;
    int id=0;
    int p1=0;
    int p2=0;
    int destino=0;
    int quantidade=0;
    int pecas_p=0;
    int pecas_fabrica=0;
    String H_pedido=null;
    String H_inicio=null;
    String H_fim=null;
    String estado=null;
    
    public void pedido_producao(int id, int po, int pf, int quantidade, int pecas_p, int pecas_fabrica, String estado, String H_pedido, String H_inicio, String H_fim){
        this.id=id;
        p1=po;
        p2=pf;
        this.quantidade=quantidade;
        this.pecas_p=pecas_p;
        this.pecas_fabrica=pecas_fabrica;
        this.estado=estado;
        this.H_pedido=H_pedido;
        this.H_inicio=H_inicio;
        this.H_fim=H_fim;
        tipo="producao";
    }
    
    public void pedido_montagem(int id,int pb, int pc, int quantidade, int pecas_p, int pecas_fabrica, String estado, String H_pedido, String H_inicio, String H_fim){
        this.id=id;
        p1=pb;  //peça baixo
        p2=pc;	//peca cima
        this.quantidade=quantidade;
        this.pecas_p=pecas_p;
        this.pecas_fabrica=pecas_fabrica;
        this.estado=estado;
         this.H_pedido=H_pedido;
        this.H_inicio=H_inicio;
        this.H_fim=H_fim;
        tipo="montagem";
    }
   
    public void pedido_descarga(int id,int po, int destino, int quantidade, int pecas_p, int pecas_fabrica, String estado, String H_pedido, String H_inicio, String H_fim){
        this.id=id;
        p1=po;
        this.destino=destino;
        this.quantidade=quantidade;
        this.pecas_p=pecas_p;
        this.pecas_fabrica=pecas_fabrica;
        this.estado=estado;
        this.H_pedido=H_pedido;
     //   System.out.print("\n"+H_pedido+"assd\n");  //nao sei o que é isto
        this.H_inicio=H_inicio;
        this.H_fim=H_fim;
        tipo="descarga";
    }
	

}
