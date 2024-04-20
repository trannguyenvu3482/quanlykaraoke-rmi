package iuh.fit.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormatUtil {
	public static String format(double d) {
		@SuppressWarnings("deprecation")
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return formatter.format(d);
	}
}
