# OCP8File

1)Описание примера из репо

Ниже приведен пример программы, которая по заданному пути к файлу выводит информацию о файле или каталоге, например, существует ли он, какие файлы содержатся в нем и т. Д.

import java.io.File;
public class ReadFileInformation { 
  public static void main(String[] args) { 
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
Если указанный путь не указывает на файл, он выведет следующее:
File Exists: false
Если указанный путь указывает на допустимый файл, он выведет что-то похожее на следующее:

File Exists: true
Absolute Path: C:\data\zoo.txt
Parent Path: C:\data
Is Directory: false
File size: 12382
File LastModified: 1420070400000
Наконец, если указанный путь указывает на действительный каталог, например C:\data, он выведет что-то похожее на следующее:

File Exists: true
Absolute Path: C:\data
Parent Path: C:\
Is Directory: true 
  employees.txt
   zoo.txt
   zoo-backup.txt
В этих примерах вы видите, что вывод программы на основе ввода / вывода полностью зависит от каталогов и файлов, доступных во время выполнения в базовой файловой системе.
Обратите внимание, что в предыдущем примере мы использовали путь на основе Windows, который требует двойной обратной косой черты в Stringлитерале для разделителя пути.
Из главы 5 «Даты, строки и локализация» вы можете помнить, что обратная косая черта \является зарезервированным символом в Stringлитерале и должна 
быть экранирована другой обратной косой чертой для использования внутри String.

2)Представляем класс File
Создание Файлового Объекта

Первый класс, который мы обсудим, является одним из наиболее часто используемых в java.ioAPI, java.io.Fileклассом или Fileклассом для краткости. Этот Fileкласс используется для чтения информации о существующих файлах и каталогах, составления списка содержимого каталога и создания / удаления файлов и каталогов.
Экземпляр Fileкласса представляет путь к конкретному файлу или каталогу в файловой системе. FileКласс не может считывать или записывать данные в файл, хотя он может быть передан в качестве ссылки на многие классы потоков для чтения или записи данных, как вы увидите в следующем разделе.
Одна распространенная ошибка, которую делают новые разработчики Java, - забывать, что Fileкласс может использоваться для представления каталогов, а также файлов.

Создание Файлового Объекта

FileОбъект часто инициализируются Stringсодержащим либо абсолютный или относительный путем к файлу или каталогу в файловой системе. absolute pathФайл или каталог полный путь от корневого каталога к файлу или каталогу, включая все подкаталоги , которые содержат файл или каталог. Альтернативно, relative pathфайл или каталог - это путь от текущего рабочего каталога к файлу или каталогу. Например, ниже приведен абсолютный путь к zoo.txtфайлу:
/home/smith/data/zoo.txt
Ниже приведен относительный путь к тому же файлу, при условии, что текущий каталог пользователя был установлен в /home/smith.
data/zoo.txt
Различные операционные системы различаются по формату имен путей. Например, системы на основе Unix используют косую черту /для путей, тогда как системы на основе Windows используют \символ обратной косой черты. Тем не менее, многие языки программирования и файловые системы поддерживают оба типа слешей при написании операторов пути. Для удобства Java предлагает два варианта получения символа локального разделителя: системное свойство и staticпеременную, определенную в Fileклассе. Оба следующих примера будут выводить символ разделителя:

System.out.println(System.getProperty("file.separator"));
System.out.println(java.io.File.separator);
Следующий код создает Fileобъект и определяет, существует ли путь, на который он ссылается, в файловой системе:

import java.io.File;
public class FileSample { 
  public static void main(String[] args) {  
    File file = new File("/home/smith/data/zoo.txt");    
  System.out.println(file.exists()); 
  }}
В этом примере используется абсолютный путь к файлу и выводится trueили false, в зависимости от того, существует ли файл. Наиболее распространенный Fileконструктор, который мы будем использовать в этой главе, принимает Stringв качестве аргумента единичное значение, представляющее относительный или абсолютный путь. Существуют другие конструкторы, такие как тот, который соединяет существующий Fileпуть с относительным дочерним путем, как показано в следующем примере:

File parent = new File("/home/smith");
File child = new File(parent,"data/zoo.txt");
В этом примере мы создаем путь, эквивалентный нашему предыдущему примеру, используя комбинацию дочернего пути и родительского пути. Если parentобъект окажется null, то он будет пропущен, и метод вернется к нашему единственному Stringконструктору. 
 
 3) Методы.Работа с файловым объектом
 
FileКласс содержит множество полезных методов для взаимодействия с файлами и каталогами в файловой системе. Мы представляем наиболее часто используемые в таблице 8.1 . Хотя эта таблица может показаться многим методам изучения, многие из них говорят сами за себя. Например, exists()возвращает, trueесли путь к файлу или каталогу существует, и в falseпротивном случае.
Таблица 8.1 Часто используемыеjava.io.Fileметоды

Method Name	Описание
exists()	Возвращает, trueесли файл или каталог существует.
getName()	Возвращает имя файла или каталога, обозначенного этим путем.
getAbsolutePath()	Возвращает строку абсолютного пути этого пути.
isDirectory()	Возвращает, trueесли файл, обозначенный этим путем, является каталогом.
isFile()	Возвращает, trueесли файл, обозначенный этим путем, является файлом.
length()	Возвращает количество байтов в файле. По соображениям производительности файловая система может выделить больше байтов на диске, чем файл фактически использует.
lastModified()	Возвращает количество миллисекунд с момента последней модификации файла.
delete()	Удаляет файл или каталог. Если этот путь обозначает каталог, то каталог должен быть пустым для удаления.
renameTo(File)	Переименовывает файл, обозначенный этим путем.
mkdir()	Создает каталог с именем по этому пути.
mkdirs()	Создает каталог с именем по этому пути, включая любые несуществующие родительские каталоги.
getParent()	Возвращает абстрактный путь родителя этого абстрактного пути или, nullесли этот путь не называет родительский каталог.
listFiles()	Возвращает File[]массив, обозначающий файлы в каталоге.


