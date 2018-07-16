package enrolleeadvisor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ContestType {
    CONTEST_FREE("в/к"), EXAMS_FREE("б/э"), COMMON("общ.");

    private List<String> aliases;

    ContestType(String... aliases) {
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
    }

    public static ContestType get(String alias) throws EnumNotFoundException {
        for (ContestType contestType : ContestType.values())
            if (contestType.aliases.contains(alias))
                return contestType;
        throw new EnumNotFoundException("Can't find ContestType by alias: " + alias);
    }

    public String getAlias() {
        return aliases.get(0);
    }
}
