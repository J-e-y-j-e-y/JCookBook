package document_management;

import lombok.Getter;

import java.util.Map;

public class Document {
    @Getter
    private final Map<String, String> attributes;

    Document(Map<String, String> attributes){
        this.attributes = attributes;
    }

    String getAttribute(String attributeName){
        return attributes.get(attributeName);
    }
}
