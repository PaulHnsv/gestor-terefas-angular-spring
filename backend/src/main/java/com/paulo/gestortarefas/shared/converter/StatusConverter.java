package com.paulo.gestortarefas.shared.converter;

import com.paulo.gestortarefas.shared.utils.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null) return null;
        return status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(Integer code) {
        if (code == null) return null;
        return Status.fromCode(code);
    }
}
