package mes;
import java.io.IOException;
import java.net.UnknownHostException;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.*;


public class Modbus {
	
	ModbusClient modbusClient = new ModbusClient();

	public static int main(String[] args) throws UnknownHostException, IOException, ModbusException
	{
	ModbusClient modbusClient = new ModbusClient();
	
	try
	{
	
		modbusClient.Connect("172.22.129.102",502);
	
		return 1;
				
	}
	catch (IOException e)
	{
		System.out.println("Erro de conexão");
	
	}
		return 0;
	}

	/*
	 * Função com o objetivo de ler registos
	 */
	public static int[] Ler_Saida_Bool(int registo, int n) throws Exception {
	
		ModbusClient modbusClient = new ModbusClient();

		int[] saida = modbusClient.ReadInputRegisters(registo,  n);
		
		for (int i = 0; i < saida.length; i++)
			
		System.out.println("Input Register #"+i+": "+saida[i]);
		
		modbusClient.WriteSingleRegister(3,100);
		
		return saida;
}
}
