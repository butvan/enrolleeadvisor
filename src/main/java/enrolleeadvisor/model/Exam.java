package enrolleeadvisor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Exam {
    MATHEMATICS("Математика"),
    RUSSIAN("Русский язык"),
    INFORMATICS("Информатика", "Информатика и ИКТ"),
    SOCIAL("Обществознание"),
    HISTORY("История"),
    BIOLOGY("Биология"),
    CHEMISTRY("Химия"),
    GEOGRAPHY("География"),
    FOREIGN("Иностранный язык", "Английский язык"),
    LITERATURE("Литература"),
    PHYSICS("Физика"),
    CREATIVE("Творческий конкурс.*");

    private List<String> aliases;

    Exam(String... aliases) {
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
    }

    public static Exam get(String alias) throws EnumNotFoundException {
        for (Exam exam : Exam.values())
            for (String regex : exam.aliases)
                if (alias.matches(regex))
                    return exam;
        throw new EnumNotFoundException("Can't find exam by alias: " + alias);
    }

    public String getAlias() {
        return aliases.get(0);
    }
}
