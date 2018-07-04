package fi.iki.jka;

import javax.swing.*;

public interface IPhotoShow {
    void Initialize(JPhotoCollection photos, int interval, JList list);

    void setVisible(boolean b);
}
