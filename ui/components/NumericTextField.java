package inventarium.ui.components;

/**
 * @author mhoffman
 * NumericTextField creates a text field from
 * the TextField class but only allows for
 * integer input.
 * 
 * Code authored by StockOverflow user Damienknight
 * url: http://stackoverflow.com/questions/19167750
 * with some modifications made by mhoffman
 */

import javafx.scene.control.TextField;

public class NumericTextField extends TextField{
	private final int MAX_DIGITS = 6;

    public NumericTextField() {
        setMinWidth(75);
        setMaxWidth(75);
    }

    public void replaceText(int start, int end, String text) {
        String oldValue = getText();
        if (!text.matches("[a-z]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceText(start, end, text);
        }
        if (getText().length() > MAX_DIGITS ) {
            setText(oldValue);
        }
    }

    public void replaceSelection(String text) {
        String oldValue = getText();
        if (!text.matches("[a-z]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceSelection(text);
        }
        if (getText().length() > MAX_DIGITS ) {
            setText(oldValue);
        }
    }
}
