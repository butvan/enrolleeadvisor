package enrolleeadvisor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Financing {
    BUDGET("Госбюджетная"), CONTRACT("Договорная");
    private List<String> aliases;

    Financing(String... aliases) {
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
    }

    public static Financing get(String alias) throws EnumNotFoundException {
        for (Financing financing : Financing.values())
            if (financing.aliases.contains(alias))
                return financing;
        throw new EnumNotFoundException("Can't find Financing by alias: " + alias);
    }

    public String getAlias() {
        return aliases.get(0);
    }
}
