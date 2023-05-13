package document_management;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class TextFile {
    @Getter
    private final Map<String, String> attributes;
    private final List<String> lines;


    public TextFile(File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(Attributes.PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }

    void addLineSuffix(final String prefix, final String attributeName){
        for(String line : lines){
            if(line.startsWith(prefix)){
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }

    int addLines(final int start, Predicate<String> isEnd, String attributeName){
        int lineNumber;
        StringBuilder builder = new StringBuilder();

        for(lineNumber = start; lineNumber < lines.size(); lineNumber++){
            String line = lines.get(lineNumber);
            if(isEnd.test(line))
                break;
            builder.append(line);
            builder.append("\n");
        }

        attributes.put(attributeName, builder.toString().trim());
        return lineNumber;
    }
}
