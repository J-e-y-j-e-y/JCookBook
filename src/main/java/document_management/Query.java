package document_management;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class Query implements Predicate<Document>{
    @Getter
    private Map<String, String> clauses;

    static Query parse(String query){
        return new Query(Arrays.stream(query.split(","))
                .map(str -> str.split(":"))
                .collect(toMap(x -> x[0], x -> x[1])));
    }

    @Override
    public boolean test(Document document) {
        return clauses.entrySet()
                .stream()
                .allMatch(entry ->
                {
                   String attrName = entry.getKey();
                   String docValue = document.getAttribute(attrName);
                   String expectedValue = entry.getValue();
                   return docValue != null && docValue.contains(expectedValue);
                });
    }
}
