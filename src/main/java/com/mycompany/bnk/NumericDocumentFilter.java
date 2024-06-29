package com.mycompany.bnk;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, string);

        if (isNumeric(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } else {
            // Handle non-numeric input here (optional)
            // For example, you could ignore the input or beep
            // Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        if (isNumeric(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // Handle non-numeric input here (optional)
            // For example, you could ignore the input or beep
            // Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean isNumeric(String s) {
        return s.isEmpty() || s.matches("\\d*");  
    }
}
