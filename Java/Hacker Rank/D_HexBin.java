/*
* Given a string representing a number in hexadecimal format, convert it into its equivalent binary string. 
* For e.g. if the input if "1F1" then its binary equivalent is "111110001". If the input is "13AFFFF", 
* the output should be "1001110101111111111111111".
*/

public class D_HexBin {
	static String test1 = "13AFFFF";
	static String test2 = "1F1";
	static String test3 = "02A6CF0E";

	static String testcase = test3;

	public static void main(String args[]) {
		D_HexBin inst = new D_HexBin();
		String v = inst.toBin(testcase);
		System.out.println(v);
	}

	// Write your code in the function below
	public String toBin(String hexStr) {
		int n = hexStr.length(), i;
		String Arr = new String("");
		for (i = 0; i < n; i++) {
			switch (hexStr.charAt(i)) {
			case '0':
				if (!(Arr.equals("")))
					Arr = Arr + "0000";
				break;
			case '1':
				if (Arr.equals(""))
					Arr = Arr + "1";
				else
					Arr = Arr + "0001";
				break;
			case '2':
				if (Arr.equals(""))
					Arr = Arr + "10";
				else
					Arr = Arr + "0010";
				break;
			case '3':
				if (Arr.equals(""))
					Arr = Arr + "11";
				else
					Arr = Arr + "0011";
				break;
			case '4':
				if (Arr.equals(""))
					Arr = Arr + "100";
				else
					Arr = Arr + "0100";
				break;
			case '5':
				if (Arr.equals(""))
					Arr = Arr + "101";
				else
					Arr = Arr + "0101";
				break;
			case '6':
				if (Arr.equals(""))
					Arr = Arr + "110";
				else
					Arr = Arr + "0110";
				break;
			case '7':
				if (Arr.equals(""))
					Arr = Arr + "111";
				else
					Arr = Arr + "0111";
				break;
			case '8':
				Arr = Arr + "1000";
				break;
			case '9':
				Arr = Arr + "1001";
				break;
			case 'A':
				Arr = Arr + "1010";
				break;
			case 'B':
				Arr = Arr + "1011";
				break;
			case 'C':
				Arr = Arr + "1100";
				break;
			case 'D':
				Arr = Arr + "1101";
				break;
			case 'E':
				Arr = Arr + "1110";
				break;
			case 'F':
				Arr = Arr + "1111";
				break;
			}
		}
		return Arr;
	}
}
