package CreateCharacter;

/*
this class was written by Stackoverflow user Boann
it can be found here:
https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.util.Objects;

/*
 * Installs a listener to receive notification when the text of any
 * {@code JTextComponent} is changed. Internally, it installs a
 * {@link DocumentListener} on the text component's {@link Document},
 * and a {@link PropertyChangeListener} on the text component to detect
 * if the {@code Document} itself is replaced.
 *
 * @param texts any text component, such as a {@link JTextField}
 *        or {@link JTextArea}
 * @param changeListener a listener to receive {@link ChangeEvent}s
 *        when the text is changed; the source object for the events
 *        will be the text component
 * @throws NullPointerException if either parameter is null
 */



public class DefineChangeListener {
    public static void addChangeListener(JTextComponent text, ChangeListener changeListener) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(changeListener);
        DocumentListener dl = new DocumentListener() {
            private int lastChange = 0, lastNotifiedChange = 0;

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lastChange++;
                SwingUtilities.invokeLater(() -> {
                    if (lastNotifiedChange != lastChange) {
                        lastNotifiedChange = lastChange;
                        changeListener.stateChanged(new ChangeEvent(text));
                    }
                });
            }
        };
        text.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
            Document d1 = (Document) e.getOldValue();
            Document d2 = (Document) e.getNewValue();
            if (d1 != null) d1.removeDocumentListener(dl);
            if (d2 != null) d2.addDocumentListener(dl);
            dl.changedUpdate(null);
        });
        Document d = text.getDocument();
        if (d != null) d.addDocumentListener(dl);
    }
}