package com.nmt.groceryfinderv2.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinder
 * @date 10/1/2024
 * Utility class for file operations like creating directories, writing, and reading files.
 */
@Slf4j
public class FileUtil {

    public static File createDirectory(String basePath, String dirName) {
        String fullPath = Paths.get(basePath, dirName).toString();
        File directory = new File(fullPath);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdir();
            if (isCreated) {
                log.info("Directory created: {}", fullPath);
            } else {
                log.error("Failed to create directory: {}", fullPath);
            }
        }
        return directory;
    }

    /**
     * Writes content to a file. Creates the file if it does not exist.
     *
     * @param filePath Path of the file to write.
     * @param content  Content to write into the file.
     * @param append   If true, content will be appended to the file; if false, it will overwrite the file.
     */
    public static void writeFile(String filePath, String content, boolean append) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, append))) {
            writer.println(content);
            log.info("Successfully wrote to file: {}", filePath);
        } catch (IOException e) {
            log.error("Failed to write to file: {}. Error: {}", filePath, e.getMessage());
        }
    }


    /**
     * Reads all lines from a file.
     *
     * @param filePath Path of the file to read.
     * @return List of strings representing lines in the file, or null if reading fails.
     */
    public static List<String> readFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            log.error("Failed to read file: {}. Error: {}", filePath, e.getMessage());
            return null;
        }
    }

    /**
     * Creates a CSV file with the specified header if it doesn't exist.
     *
     * @param filePath Path of the CSV file to create.
     * @param header   The header to write into the CSV file.
     */
    public static void createCsvFileWithHeader(String filePath, String header) {
        File file = new File(filePath);
        if (!file.exists()) {
            writeFile(filePath, header, false);
        }
    }


    public static List<CSVRecord> readCsvFile(MultipartFile file) {
        List<CSVRecord> records = new ArrayList<>();
        try {
            Reader reader =  new InputStreamReader(file.getInputStream());
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            Iterable<CSVRecord> csvRecords = format.parse(reader);
            for (CSVRecord record : csvRecords) {
                records.add(record);
            }

            log.info("CSV file has been successfully read.");
        }catch (Exception e) {
            log.error("Failed to read CSV file: {}", e.getMessage());
        }
        return records;
    }

}
