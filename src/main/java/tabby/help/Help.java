package tabby.help;

import org.neo4j.graphdb.Transaction;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;
import tabby.result.HelpResult;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.neo4j.internal.helpers.collection.MapUtil.map;


public class Help {

    @Context
    public Transaction tx;

    private static final Set<String> extended = new HashSet<>();

    @Procedure("tabby.help")
    @Description("Provides descriptions of available procedures. To narrow the results, supply a search string. To also search in the description text, append + to the end of the search string.")
    public Stream<HelpResult> info(@Name("proc") String name) throws Exception {
        boolean searchText = false;
        if (name != null) {
            name = name.trim();
            if (name.endsWith("+")) {
                name = name.substring(0, name.lastIndexOf('+')).trim();
                searchText = true;
            }
        }
        String filter = " WHERE name starts with 'tabby.' " +
                " AND ($name IS NULL  OR toLower(name) CONTAINS toLower($name) " +
                " OR ($desc IS NOT NULL AND toLower(description) CONTAINS toLower($desc))) " +
                "RETURN type, name, description, signature ";

        String query = "WITH 'procedure' as type CALL dbms.procedures() yield name, description, signature " + filter +
                " UNION ALL " +
                "WITH 'function' as type CALL dbms.functions() yield name, description, signature " + filter;
        return tx.execute(query, map("name", name, "desc", searchText ? name : null))
                .stream().map(row -> new HelpResult(row, !extended.contains((String) row.get("name"))));
    }
}
