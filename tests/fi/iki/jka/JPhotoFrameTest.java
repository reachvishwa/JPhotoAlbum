package fi.iki.jka;

import fi.iki.jka.IPhotoShow;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

import org.junit.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JPhotoFrameTest {
    @Test
    public void callSlideshowWhenThereIsAtLeastAPhotoTest() throws Exception  {
        JPhotoFrame testPhotoframe = new JPhotoFrame();
        JPhotoCollection photoCollection = new JPhotoCollection();
        JPhoto photo = new JPhoto();
        photoCollection.add(0,photo);
        IPhotoShow show = mock(IPhotoShow.class);
        testPhotoframe.StartSlideShow( photoCollection,show,null,5000);

        verify(show).Initialize(eq(photoCollection), eq(5000), any(JList.class));
        verify(show).setVisible(true);
    }

    @Test
    public void slideshowMenuItemTriggersShowingSlideShow() throws Exception  {
        JPhotoFrame testPhotoframe = new JPhotoFrame();
        JPhotoCollection photoCollection = new JPhotoCollection();
        JPhoto photo = new JPhoto();
        photoCollection.add(0,photo);
        IPhotoShow show = mock(IPhotoShow.class);
        testPhotoframe.init(null, photoCollection,show);

        testPhotoframe.actionPerformed(new ActionEvent(new Object(), 0, JPhotoMenu.A_SLIDESHOW));

        verify(show).Initialize(eq(photoCollection), eq(5000), any(JList.class));
        verify(show).setVisible(true);
    }


    @Test
    public void verifyAErrorMessageIsShowWhenThereIsNoPhotos() throws Exception  {
        JPhotoFrame testPhotoframe = new JPhotoFrame();
        JPhotoCollection photoCollection = new JPhotoCollection();

        IPhotoShow show = mock(IPhotoShow.class);
        IShowMessage showMessage = mock(IShowMessage.class);

        testPhotoframe.StartSlideShow( photoCollection,show,showMessage,5000);

        verify(showMessage).ShowErrorMessage(any(JPhotoFrame.class),eq("No photos to show!"));
    }
}