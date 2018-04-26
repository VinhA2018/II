package ii_teste;

import ii_teste.Modbus;
import ii_teste.pedido;


import java.util.concurrent.TimeUnit;

public class cerebro {


   static int Armazem_livre=0;
    
    static int MAQA_celula1 = 2;
    static int MAQA_celula2 = 3;
    static int MAQB_celula3 = 4;
    static int MAQC_celula1 = 5;
    static int MAQC_celula2 = 6;
    static int MAQC_celula3 = 10;
    
    static int Montagem = 11;
   
    static int PM1_int = 13;   //Pusher 1 etc
    static int PM2_int = 14;
    static int PM3_int  = 15;
   
    static int Pedido_efetuado = 10;
    
   

	public static void main(String[] args) {
		// TODO Auto-generated method stub

    	Modbus Modbus = new Modbus();
    	Modbus.Init();
  
    	System.out.println("ta a dar até aqui crlh!!!!!");
    	
    	int aux_max = 5;
    	
    /*	 boolean MAQA1=false;
         boolean MAQC1=false;
         boolean MAQA2=false;
         boolean MAQC2=false;
         boolean MAQB3=false;
         boolean MAQC3=false;
         
         boolean celula_montagem=false;
         
         boolean PM1=false;
         boolean PM2=false;
         boolean PM3=false;
   
 
         int id_ordem=0; 
         
          
          
          */
         int i=1;
         
         boolean[] saidas_boolean = new boolean[16];
         int[] valores = new int[5];
         boolean[] saidas = new boolean[1];
         pedido order = new pedido();
          
          
		
         try
         {
           
         while(true)
         {  
             
             try {
             int id_peca=0;
             		
             System.out.println("ta a dar até aqui crlh");
             TimeUnit.SECONDS.sleep(1);
             
             
             //Verificar se armazemm pronto a receber
            saidas_boolean = Modbus.Ler_Saida_Bool(0,16);
            
            
             
              if(saidas_boolean[Armazem_livre]==true)  
              {
                   i=1;
                     
                 order.tipo="inico";
                  while(i<aux_max /*DB.id_max_processar() */ && i!=0)
                     {    
                 //        order=DB.ler_pedidos_processar(i);
                            if(order.tipo!="sem_tipo")
                            {
                                if(("a_processar".equals(order.estado) && order.pecas_p+order.pecas_fabrica!=order.quantidade) || "por_processar".equals(order.estado))
                                  {  
                                  switch (order.tipo) 
                                     {
                                         case "producao":
                                         {
                                             //MAQB celula 3
                                                  if(saidas_boolean[MAQB_celula3] == false && i!=0)
                                                  {
                                           
                                                    if ((order.p1==2 && order.p2==4)|| (order.p1==4 && order.p2==6) || (order.p1==6 && order.p2==8)) 
                                                      {
                                                             //enviar para o armazem -> p1, p3, celula3
                                                         id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                         valores[0]=1;   			//produçao
                                                         valores[1]=3;				//celula
                                                         valores[2]=id_peca;
                                                         valores[3]=order.p1;		//peça origem
                                                         valores[4]=order.p2;		//peça fim
                                                         Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                         saidas[0]=true;   // a enviar dados, vetor auxiliar
                                                      
                                                         Modbus.Escrever_Saida_Bool(1,saidas);
                                                     //    DB.incrementar_peca_fabrica(order.id);             //base de dados 
                                                      
                                                         saidas[0]=false;
                                                         Modbus.Escrever_Saida_Bool(1, saidas);
                                                
                                                         i=0;
                                                         saidas_boolean[MAQB_celula3]=true;				//maquina B celula 3 ocupada
                                                         saidas_boolean[MAQC_celula3]=true;			
                                                      }
                                                   
                               
                                                  } 
                                                 
                                                    //MAQ A celula 1
                                                  
                                                 if(saidas_boolean[MAQA_celula1] == false && i!=0)
                                                     {     
                                                          if((order.p1==1 && order.p2==3) || (order.p1==3 && order.p2==5) || (order.p1==5 && order.p2==7))
                                                             {
                                                                 id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                                 valores[0]=1;
                                                                 valores[1]=1;
                                                                 valores[2]=id_peca;
                                                                 valores[3]=order.p1;
                                                                 valores[4]=order.p2;
                                                     
                                                                 Modbus.Escrever_Saida_Registos(0, valores);
                                   
                                                                  saidas[0]=true;
                                                      
                                                                 Modbus.Escrever_Saida_Bool(1,saidas);
                                                              //   DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                                 saidas[0]=false;
                                                                 Modbus.Escrever_Saida_Bool(1, saidas);
                                                                 i=0;
                                                                 saidas_boolean[MAQA_celula1]=true;
                                                                 saidas_boolean[MAQC_celula1]=true;      
                                                             }
                                                          
                                                      }
                                                 
                                                   //MAQA celula 2
                                                 
                                                      if(saidas_boolean[MAQA_celula2] == false && i!=0)
                                                      {    
                                                       
                                                          if((order.p1==1 && order.p2==3) || (order.p1==3 && order.p2==5) || (order.p1==5 && order.p2==7))
                                                         {
                                                                
                                                                id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                                valores[0]=1;
                                                                valores[1]=2;
                                                                valores[2]=id_peca;
                                                                valores[3]=order.p1;
                                                                valores[4]=order.p2;
                                                      
                                                                Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                                saidas[0]=true;
                                                                Modbus.Escrever_Saida_Bool(1,saidas);
                                                             //   DB.incrementar_peca_fabrica(order.id); 
                                                     
                                                                saidas[0]=false;
                                                                Modbus.Escrever_Saida_Bool(1, saidas);
                                                                saidas_boolean[MAQA_celula2]=true;
                                                                saidas_boolean[MAQC_celula2]=true;
                                                                i=0;
                                                         }
                                             
                                                      }
                                                      
                                         //MAQc   celula 1
                                                      
                                                      if(saidas_boolean[MAQC_celula1] == false && i!=0)
	                                                      {
	                                                           if((order.p1==7 && order.p2==8) || (order.p1==8 && order.p2==7) || (order.p1==7 && order.p2==9) || (order.p1==8 && order.p2==9))
	                                                           {
	                                                                     id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
	                                                                     valores[0]=1;
	                                                                     valores[1]=1;
	                                                                     valores[2]=id_peca;
	                                                                     valores[3]=order.p1;
	                                                                     valores[4]=order.p2;
	                                                                     Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
	                                   
	                                                                     saidas[0]=true;
	                                                      
	                                                                     Modbus.Escrever_Saida_Bool(1,saidas);
	                                                               //      DB.incrementar_peca_fabrica(order.id); 
	                                                      
	                                                                     saidas[0]=false;
	                                                                     Modbus.Escrever_Saida_Bool(1, saidas);
	                                                                     
	                                                                     i=0;
	                                                                     saidas_boolean[MAQC_celula1]=true;
	                                                           }
	                                                      }
                                                      
                                                      if(saidas_boolean[MAQC_celula2] == false && i!=0)
                                                      {
                                                           if((order.p1==7 && order.p2==8) || (order.p1==8 && order.p2==7) || (order.p1==7 && order.p2==9) || (order.p1==8 && order.p2==9))
                                                           {
                                                                     id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                                     valores[0]=1;
                                                                     valores[1]=2;
                                                                     valores[2]=id_peca;
                                                                     valores[3]=order.p1;
                                                                     valores[4]=order.p2;
                                                                     Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                                     saidas[0]=true;
                                                      
                                                                     Modbus.Escrever_Saida_Bool(1,saidas);
                                                             //        DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                                     saidas[0]=false;
                                                                     Modbus.Escrever_Saida_Bool(1, saidas);
                                                                     
                                                                     i=0;
                                                                     saidas_boolean[MAQC_celula2]=true;
                                                           }
                                                      }
                                                      
                                                      if(saidas_boolean[MAQC_celula3] == false && i!=0)
                                                      {
                                                           if((order.p1==7 && order.p2==8) || (order.p1==8 && order.p2==7) || (order.p1==7 && order.p2==9) || (order.p1==8 && order.p2==9))
                                                           {
                                                                     id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                                     valores[0]=1;
                                                                     valores[1]=3;
                                                                     valores[2]=id_peca;
                                                                     valores[3]=order.p1;
                                                                     valores[4]=order.p2;
                                                                     Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                                     saidas[0]=true;
                                                      
                                                                     Modbus.Escrever_Saida_Bool(1,saidas);
                                                             //        DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                                     saidas[0]=false;
                                                                     Modbus.Escrever_Saida_Bool(1, saidas);
                                                                     
                                                                     i=0;
                                                                     saidas_boolean[MAQC_celula3]=true;
                                                           }
                                                      }
                                                      
                                                    /////  PEDIDOS QUE PRECISAM DE 2 MAQUINAS
                                                      
                                                      // MAQA E MAQC da celula 1
                                                          
                                                 if(saidas_boolean[MAQA_celula1] == false && saidas_boolean[MAQC_celula1]== false && i!=0)
                                                  {
                                                       if((order.p1==1 && order.p2==8) || (order.p1==1 && order.p2==9) || (order.p1==3 && order.p2==8) || (order.p1==3 && order.p2==9)|| (order.p1==5 && order.p2==8) || (order.p1==5 && order.p2==9))
                                                        {
                                                            id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                            valores[0]=1;
                                                            valores[1]=1;
                                                            valores[2]=id_peca;
                                                            valores[3]=order.p1;
                                                            valores[4]=order.p2;
                                                            Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                            saidas[0]=true;
                                                      
                                                            Modbus.Escrever_Saida_Bool(1,saidas);
                                                       //     DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                            saidas[0]=false;
                                                            Modbus.Escrever_Saida_Bool(1, saidas);
                                                            i=0;
                                                            saidas_boolean[MAQA_celula1]=true;
                                                            saidas_boolean[MAQC_celula1]=true;
                                                         }
                                                
                                                 }
                                                 
                                                 
                                                 // MAQA E MAQC da celula 2
                                                 
                                            if((saidas_boolean[MAQA_celula2] == false && saidas_boolean[MAQC_celula2]==false) && i!=0)
                                                  {
                                                       if((order.p1==1 && order.p2==8) || (order.p1==1 && order.p2==9) || (order.p1==3 && order.p2==8) || (order.p1==3 && order.p2==9)|| (order.p1==5 && order.p2==8) || (order.p1==5 && order.p2==9))
                                                        {
                                                            id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                            valores[0]=1;
                                                            valores[1]=2;
                                                            valores[2]=id_peca;
                                                            valores[3]=order.p1;
                                                            valores[4]=order.p2;
                                                            Modbus.Escrever_Saida_Registos(0, valores);
                                   
                                                            saidas[0]=true;
                                                      
                                                            Modbus.Escrever_Saida_Bool(1,saidas);
                                                        //    DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                            saidas[0]=false;
                                                            Modbus.Escrever_Saida_Bool(1, saidas);
                                                            i=0;
                                                            saidas_boolean[MAQA_celula2]=true;
                                                            saidas_boolean[MAQC_celula2]=true;
                                                         }
                                                
                                                 }
                                            
                                             if(saidas_boolean[MAQB_celula3]==false && saidas_boolean[MAQC_celula3]== false && i!=0)
                                             {
                                                     if((order.p1==2 && order.p2==7) || (order.p1==2 && order.p2==9) || (order.p1==4 && order.p2==7) || (order.p1==4 && order.p2==9)|| (order.p1==6 && order.p2==7) || (order.p1==6 && order.p2==9))
                                                      {
                                                      id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                      valores[0]=1;
                                                      valores[1]=3;
                                                      valores[2]=id_peca;
                                                      valores[3]=order.p1;
                                                      valores[4]=order.p2;
                                                //      System.out.print(id_peca);
                                                      Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                      saidas[0]=true;
                                                      
                                                      Modbus.Escrever_Saida_Bool(1,saidas);
                                                  //    DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                      saidas[0]=false;
                                                      Modbus.Escrever_Saida_Bool(1, saidas);
                                           
                                                      i=0;
                                                      saidas_boolean[MAQB_celula3]=true;
                                                      saidas_boolean[MAQC_celula3]=true;
                                                      }
                                              
                                             }
                                             if (i!=0)
                                             {
                                                 
                                                 i++;
                                             }
                                             
                                          break;
                                         }
    	
    	
                                         case "montagem":
                                         {
                                             if(saidas_boolean[Montagem] == false && i!=0)
                                             {
                                             
                                                      id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                      valores[0]=2;
                                                      valores[1]=0;
                                                      valores[2]=id_peca;
                                                      valores[3]=order.p1;
                                                      valores[4]=order.p2;
                                                 //     System.out.print(id_peca);
                                                      Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                      saidas[0]=true;
                                                      
                                                      Modbus.Escrever_Saida_Bool(1,saidas);
                                                      
                                               //       DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                      saidas[0]=false;
                                                      Modbus.Escrever_Saida_Bool(1, saidas);
                                           
                                                      i=0;
                                                      saidas_boolean[Montagem]=true;
                                               
                                             }
                                             if (i!=0)
                                             {
                                                 i++;
                                             }
                                             
                                             break;
                                         }
                      
                                         case "descarga":
                                         {  
                                             if(saidas_boolean[PM1_int]==false && order.destino==1 && i!=0)
                                             {
                                                      id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                      valores[0]=3;
                                                      valores[1]=1;
                                                      valores[2]=id_peca;
                                                      valores[3]=order.p1;
                                                      
                                                      System.out.print(id_peca);
                                                      Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                      saidas[0]=true;
                                                      
                                                      Modbus.Escrever_Saida_Bool(1,saidas);
                                                      
                                             //         DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                      saidas[0]=false;
                                                      Modbus.Escrever_Saida_Bool(1, saidas);
                                           
                                                      i=0;
                                                      saidas_boolean[PM1_int]=true;
                                             }
                          
                                             if(saidas_boolean[PM2_int] == false && order.destino==2 && i!=0)
                                              {
                                                         //enviar para o aramazem -> p, destino
                                                      id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                      valores[0]=3;
                                                      valores[1]=2;
                                                      valores[2]=id_peca;
                                                      valores[3]=order.p1;
                                                      
                                                      System.out.print(id_peca);
                                                      Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                      saidas[0]=true;
                                                      
                                                      Modbus.Escrever_Saida_Bool(1,saidas);
                                                      
                                                //      DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                      saidas[0]=false;
                                                      Modbus.Escrever_Saida_Bool(1, saidas);
                                           
                                                      i=0;
                                                      saidas_boolean[PM2_int]=true;
                                              
                                              }
                                             if(saidas_boolean[PM3_int]==false && order.destino==3 && i!=0)
                                             {
                                                      id_peca=order.id*10+(order.pecas_p+order.pecas_fabrica)%2;
                                                      valores[0]=3;
                                                      valores[1]=3;
                                                      valores[2]=id_peca;
                                                      valores[3]=order.p1;
                                                      
                                                      System.out.print(id_peca);
                                                      Modbus.Escrever_Saida_Registos(0, valores);// tipo enviar para o armazem -> p1, p2, celula1
                                   
                                                      saidas[0]=true;
                                                      
                                                      Modbus.Escrever_Saida_Bool(1,saidas);
                                                      
                                           //           DB.incrementar_peca_fabrica(order.id); 
                                                      
                                                      saidas[0]=false;
                                                      Modbus.Escrever_Saida_Bool(1, saidas);
                                           
                                                      i=0;
                                                      saidas_boolean[PM3_int]=true;//enviar para o aramazem -> p, destino
                                              }
                                             if(i!=0)
                                             {
                                                 i++;
                                             }
                                          break;
                                         }  
                  
                                     }
                                 }
                                else i++;
                            }
                            else i++;
                     }
                 }
                 }
                 catch(Exception ex)
                 {
                  System.out.print(ex);
                 }
         }
         }
        catch(Exception ex)
         {
            System.out.print(ex);
         }
     }	
		
	}

