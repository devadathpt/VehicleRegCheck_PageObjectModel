package DirectoryScan;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;


    public class GetFileList extends SimpleFileVisitor<Path> {

        private static Stream<Path> listFiles(final Path uri) throws IOException {
           Stream<Path> list  = Files.list(uri).filter(Files::isRegularFile);
            return list;
        }


        public static Path getDataFileUri(Path uri) throws IOException {

            Iterator<Path> iterator = listFiles(uri).iterator();
            while(iterator.hasNext())
            {
                Path fileName = iterator.next();
                if (fileName.toString().contains(".xlsx"))
                    return fileName;
            }
            throw new RuntimeException("No XLSX file found in the path");
        }

        public static Map<String, List<String>> getFilenameAndDetails(Path uri) throws IOException {

            Map<String, List<String>> fileData = new HashMap<String, List<String>>();
            Iterable<Path> iterable = listFiles(uri)::iterator;

            for (Path p : iterable) {
                fileData.put(p.getFileName().toString(),getFileProperties(p));
            }
            return fileData;
        }

        public static String getFileExtension(Path file) {

            String fileName = file.getFileName().toString();
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
                return fileName.substring(fileName.lastIndexOf(".")+1);
            else return "";
        }


        private static List<String> getFileProperties(Path p) throws  IOException
        {
            BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
            List<String> fileProperties = new ArrayList<>();
            fileProperties.add("MimeTye  : "+Files.probeContentType(p));
            fileProperties.add("FileExtension :"+getFileExtension(p));
            fileProperties.add("FileSize :"+attr.size() + "  bytes)");
            return fileProperties;
        }

    }
