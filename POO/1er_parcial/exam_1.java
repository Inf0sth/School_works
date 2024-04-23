public class TAREA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hello = " Ho2ll1o M7und4o";
		String j = "";
		for (int i = 0; i < hello.length(); i++) {
			char x = hello.charAt(i);
			if (!Character.isDigit(x)){
				j += x;
			}
		}
		System.out.println(j);
	}
}