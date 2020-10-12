

        import java.io.File;
        public class ReadFileInformation {
            public static void main(String[] args) {
                //Если указанный путь не указывает на файл, он выведет следующее:
                //File Exists: false

                //Если указанный путь указывает на допустимый файл, он выведет что-то похожее на следующее:
                //
                //File Exists: true
                //Absolute Path: C:\data\zoo.txt
                //Parent Path: C:\data
                //Is Directory: false
                //File size: 12382
                //File LastModified: 1420070400000
                
                //Наконец, если указанный путь указывает на действительный каталог,
                // например C:\data, он выведет что-то похожее на следующее:
                //
                //File Exists: true
                //Absolute Path: C:\data
                //Parent Path: C:\
                //Is Directory: true
                //  employees.txt
                //   zoo.txt
                //   zoo-backup.txt
                File file = new File("C:\\data\\zoo.txt");
                System.out.println("File Exists: "+file.exists());
                if(file.exists()) {
                    System.out.println("Absolute Path: "+file.getAbsolutePath());
                    System.out.println("Is Directory: "+file.isDirectory());
                    System.out.println("Parent Path: "+file.getParent());
                    if(file.isFile()) {
                        System.out.println("File size: "+file.length());
                        System.out.println("File LastModified: "+file.lastModified());
                    } else {
                        for(File subfile: file.listFiles()) {
                            System.out.println("\t"+subfile.getName());
                        }
                    }
                }
            }}


