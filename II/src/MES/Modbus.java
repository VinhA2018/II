package MES;
import java.io.IOException;
import java.net.UnknownHostException;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.*;


public class Modbus {
	



	public static void main(String[] args) throws UnknownHostException, IOException, ModbusException

	{
	

		ModbusClient modbusClient = new ModbusClient();
		modbusClient.Connect("172.22.129.102",502);
	

		int[] inputRegisters = modbusClient.ReadInputRegisters(0,  10);
		
		for (int i = 0; i < inputRegisters.length; i++)
			
		System.out.println("Input Register #"+i+": "+inputRegisters[i]);
		
		modbusClient.WriteSingleRegister(3,100);

	
}
}