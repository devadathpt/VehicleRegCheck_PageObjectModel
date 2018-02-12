package DirectoryScan;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;


    public class GetFileList extends SimpleFileVisitor<Path> {

        public static Stream<Path> listFiles(final String uri) throws IOException {
            Path source = Paths.get(uri);

            Stream<Path> list = Files.list(source).filter(Files::isRegularFile);

            return list;
        }


        public static Path getDataFileUri(String uri) throws IOException {


            Iterator<Path> iterator = listFiles(uri).iterator();

            while (iterator.hasNext()) {
                Path fileName = iterator.next();
                if (fileName.toString().contains(".xlsx"))
                    return fileName;
            }
            throw new RuntimeException("No XLSX file found in the path");
        }

        public static Map<String, List<String>> getFilenameAndDetails(String uri) throws IOException {
            Path source = Paths.get(uri);
            Map<String, List<String>> fileData = new HashMap<String, List<String>>();
            Stream<Path> result = Files.walk(source).filter(Files::isRegularFile);
            Iterable<Path> iterable = result::iterator;
            for (Path p : iterable) {
                BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
                List<String> fileProperties = new ArrayList<>();
                fileProperties.add("MimeTye  : "+Files.probeContentType(p));
                fileProperties.add("FileExtension :"+getFileExtension(p));
                fileProperties.add("FileSize :"+attr.size() + "  bytes)");
                fileData.put(p.getFileName().toString(),fileProperties);
            }
            return fileData;
        }
        public static String getFileExtension(Path file) {
            String fileName = file.getFileName().toString();
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
                return fileName.substring(fileName.lastIndexOf(".")+1);
            else return "";
        }

    }
