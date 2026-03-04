package com.paulo.gestortarefas.shared.converter;

import com.paulo.gestortarefas.shared.utils.Priority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Priority priority) {
        if (priority == null) return null;
        return priority.getCode();  // BAIXA → 1
    }

    @Override
    public Priority convertToEntityAttribute(Integer code) {
        if (code == null) return null;
        return Priority.fromCode(code);  // 1 → BAIXA
    }
}
