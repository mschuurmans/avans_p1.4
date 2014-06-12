import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;




public class TestClass
{
	public static BufferedReader input;
	public static OutputStream output;

	public static synchronized void writeData(String data) {
		System.out.println("Sent: " + data);
		try {
			output.write(data.getBytes());
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}

	public static void main(String[] ag)
	{

		try
		{
			SerialClass obj = new SerialClass();
			int c=0;
			obj.initialize();
			input = SerialClass.input;
			output = SerialClass.output;
			InputStreamReader Ir = new InputStreamReader(System.in);
			BufferedReader Br = new BufferedReader(Ir);
			while (c!=6)
			{
				System.out.print("Enter your choice: ");
				c = Integer.parseInt(Br.readLine());
				
				System.out.println((char)c);
				System.out.println(c);
		
				writeData("" + (char)c);

			}


			String inputLine=input.readLine();
			System.out.println(inputLine);

			obj.close();

		}
		catch(Exception e){}

	}
}