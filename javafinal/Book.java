import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
public class Book {
    public static void main(String[] args){
        creatingPage create = new creatingPage();
        create.setCreateListener(new creatingPage.CreateListener() {
            @Override
            public void onCreate(String name) {
                // Create and show the PageFrame
                PageFrame pageFrame = new PageFrame(name);
                pageFrame.setVisible(true);
                pageFrame.animatePageOpen();
            }
        });

    }
}
