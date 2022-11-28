package by.grsu.aandrushko.todolist.web;

public class ValidationUtils {


		private ValidationUtils() {
		}

		public static boolean isInteger(String str) {
			if (str == null) {
				return false;
			}
			try {
				Integer.parseInt(str);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}
	
}
