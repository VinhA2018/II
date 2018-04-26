package ii_teste;

import java.io.IOException;

import de.re.easymodbus.modbusclient.ModbusClient;

public class Modbus {
public static ModbusClient modbusClient = new ModbusClient();
	
	public void Init() {
		
	
	
	try
	{
	
		modbusClient.Connect("172.30.15.185",502);
		System.out.println("funcionou");
	//	return 1;
				
	}
	catch (IOException e)
	{
		System.out.println("Erro de conexão");
	
	}
	//	return 0;
	}

	/*
	 * Função com o objetivo de ler registos, etc
	 */
	public static int[] Ler_Saida_Registos(int registo, int n) throws Exception 
	{
		
		int[] saida = modbusClient.ReadInputRegisters(registo,  n);
		return saida;
	}
	
	
	public static void Escrever_Saida_Registos(int registo, int[] n) throws Exception 
	{
		modbusClient.WriteMultipleRegisters(registo, n);		
	}
	
	public static boolean[] Ler_Saida_Bool(int input, int n) throws Exception 
	{
		
		boolean[] saida = modbusClient.ReadDiscreteInputs(input,  n);
		return saida;		
	}
	
	public static void Escrever_Saida_Bool(int coil, boolean[] n) throws Exception 
	{	
		modbusClient.WriteMultipleCoils(coil,n);		
	}
	
	
	/*  for (int i = 0; i < saida.length; i++)
	System.out.println("Input Register #"+i+": "+saida[i]);   */
		
	

}
