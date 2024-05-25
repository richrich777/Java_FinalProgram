import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
public class Book {
    private String name="測試";
    PageFrame page=new PageFrame(name);
    Book(String name){
        this.name =name;
    }
    public void createDirectory() {
        try {
            // 獲取當前類的路徑
            String path = Book.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");

            // 獲取當前類所在資料夾的 File 對象
            File currentDir = new File(decodedPath).getParentFile();
            System.out.println("當前類的路徑: " + currentDir.getAbsolutePath());

            // 獲取上一層資料夾的 File 對象
            File parentDir = currentDir.getParentFile();
            System.out.println("上一層資料夾的路徑: " + parentDir.getAbsolutePath());

            // 在上一層資料夾中建立一個新子資料夾
            File newDir = new File(parentDir, name);
            if (newDir.exists()) {
                System.out.println("資料夾已經存在：" + newDir.getAbsolutePath());
            } else {
                // 在上一層資料夾中建立一個新子資料夾
                if (newDir.mkdirs()) {
                    System.out.println("成功建立子資料夾：" + newDir.getAbsolutePath());
                } else {
                    System.out.println("建立子資料夾失敗");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) {
        Book test1=new Book("測試1");
        test1.createDirectory();
        Book test2=new Book("測試2");
        test2.createDirectory();
    }*/
}
