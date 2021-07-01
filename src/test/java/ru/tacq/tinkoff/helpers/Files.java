package ru.tacq.tinkoff.helpers;

import org.apache.commons.io.FileUtils;
import com.codeborne.pdftest.PDF;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Files {

    public static String readTextFromFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public static PDF getPdf(File file) throws IOException {
        return new PDF(file);
    }

    public static File getFile(String path) {
        return new File(path);
    }
}
