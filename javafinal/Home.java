
public class Home
{
    public static void main(String[] args)
    {
        BookCase bookCase=new BookCase();
        PageFrame pageFrame=new PageFrame("");
        creatingPage createPage=new creatingPage();
        pageFrame.setVisible(false);
        createPage.setVisible(false);
        bookCase.setVisible(false);
        Homeframe homeFrame= new Homeframe("My Diary");
        homeFrame.setHomeListener(new Homeframe.HomeListener() {
            @Override
            public void caseCreate() {
                bookCase.setVisible(true);
                homeFrame.setVisible(false);
            }
        });
        bookCase.setToMainListener(new BookCase.toMainListener() {
            @Override
            public void GotoMain() {
                bookCase.setVisible(false);
                homeFrame.setVisible(true);
            }
        });
        bookCase.settoCreateListener(new BookCase.toCreateListener() {
            @Override
            public void goToCreate() {
                bookCase.setVisible(false);
                createPage.setVisible(true);
            }
        });
        createPage.setCreateListener(new creatingPage.CreateListener() {
            @Override
            public void onCreate(String name) {
                // Create and show the PageFrame
                pageFrame.setName(name);
                pageFrame.setVisible(true);
                createPage.setVisible(false);
            }
        });
        pageFrame.setBookcaseListener(new PageFrame.goToBookcaseListener() {
            @Override
            public void goToBookcase() {
                pageFrame.setVisible(false);
                bookCase.setVisible(true);
            }
        });
    }
}