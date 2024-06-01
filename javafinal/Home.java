import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URLDecoder;

public class Home
{
    public static void main(String[] args)
    {
        BookCase bookCase=new BookCase();
        PageFrame pageFrame=new PageFrame("");
        getData(bookCase,pageFrame);
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
                pageFrame.draw.setCoverColor(createPage.coverColor);
                pageFrame.draw.setPageColor(createPage.pageColor);
                bookCase.addBooksToBookCase(name,createPage.coverColor,createPage.pageColor,createPage.fontColor);
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
    public static void getData(BookCase bookCase,PageFrame pageFrame){
        System.out.println(getSourcesDir());
        File directory = new File(getSourcesDir());
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 打印子目錄的絕對路徑
                        System.out.println("子資料夾: " + file.getAbsolutePath());
                        System.out.println(file.getName());
                        File coverColorData=new File(file,"coverColor.txt");
                        File pageColorData=new File(file,"pageColor.txt");
                        File fontColorData=new File(file,"fontColor.txt");
                        if(coverColorData.exists()){
                            int coverRed=102,coverGreen=51,coverBlue=0,pageRed=229,pageGreen=255,pageBlue=204,fontRed=0,fontGreen=0,fontBlue=0;
                            try (BufferedReader br = new BufferedReader(new FileReader(coverColorData))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    // 將讀取到的每一行內容按逗號分割
                                    String[] numbers = line.split(",");

                                    // 確保有三個數字
                                    if (numbers.length == 3) {
                                        try {
                                            // 將字符串轉換為整數
                                            coverRed = Integer.parseInt(numbers[0].trim());
                                            coverGreen = Integer.parseInt(numbers[1].trim());
                                            coverBlue = Integer.parseInt(numbers[2].trim());
                                        } catch (NumberFormatException e) {
                                            System.out.println("無效的數字格式: " + line);
                                        }
                                    } else {
                                        System.out.println("行格式錯誤: " + line);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try (BufferedReader br = new BufferedReader(new FileReader(pageColorData))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    // 將讀取到的每一行內容按逗號分割
                                    String[] numbers = line.split(",");

                                    // 確保有三個數字
                                    if (numbers.length == 3) {
                                        try {
                                            // 將字符串轉換為整數
                                            pageRed = Integer.parseInt(numbers[0].trim());
                                            pageGreen = Integer.parseInt(numbers[1].trim());
                                            pageBlue = Integer.parseInt(numbers[2].trim());
                                        } catch (NumberFormatException e) {
                                            System.out.println("無效的數字格式: " + line);
                                        }
                                    } else {
                                        System.out.println("行格式錯誤: " + line);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try (BufferedReader br = new BufferedReader(new FileReader(fontColorData))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    // 將讀取到的每一行內容按逗號分割
                                    String[] numbers = line.split(",");

                                    // 確保有三個數字
                                    if (numbers.length == 3) {
                                        try {
                                            // 將字符串轉換為整數
                                            fontRed = Integer.parseInt(numbers[0].trim());
                                            fontGreen = Integer.parseInt(numbers[1].trim());
                                            fontBlue = Integer.parseInt(numbers[2].trim());
                                        } catch (NumberFormatException e) {
                                            System.out.println("無效的數字格式: " + line);
                                        }
                                    } else {
                                        System.out.println("行格式錯誤: " + line);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            bookCase.addBooksToBookCase(file.getName(),new Color(coverRed,coverGreen,coverBlue),new Color(pageRed,pageGreen,pageBlue),new Color(fontRed,fontGreen,fontBlue));
                        }
                    }
                }
            }
        } else {
            System.out.println("指定的路徑不是一個有效的資料夾: " + getSourcesDir());
        }
        for (Books book : bookCase.books) {
            book.setGoToRead(new Books.goToReadListener() {
                @Override
                public void goToReading(String name) {
                    bookCase.setVisible(false);
                    pageFrame.setVisible(true);
                    pageFrame.draw.setCoverColor(book.coverColor);
                    pageFrame.draw.setPageColor(book.pageColor);

                    pageFrame.setName(name);
                    System.out.println("Opening book: " + name);

                }
            });
        }
    }
    public static String getSourcesDir(){
        File sourcesDir = null;
        try {
            // 獲取當前類的路徑
            String path = PageText.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");

            // 獲取當前類所在資料夾的 File 對象
            File currentDir = new File(decodedPath).getParentFile();
            System.out.println("當前類的路徑: " + currentDir.getAbsolutePath());

            // 獲取上一層資料夾的 File 對象
            sourcesDir = new File(currentDir.getParentFile().getParentFile(),"sources");
            System.out.println("上一層資料夾的路徑: " + sourcesDir.getAbsolutePath());

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return sourcesDir != null ? sourcesDir.getAbsolutePath() : null;
    }
}