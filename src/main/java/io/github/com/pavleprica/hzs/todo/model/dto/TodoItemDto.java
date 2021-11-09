package io.github.com.pavleprica.hzs.todo.model.dto;

import io.github.com.pavleprica.hzs.todo.model.TodoItemType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TodoItemDto(
        @Min(1)
        Long id,

        @NotBlank
        @NotNull
        String title,

        @NotBlank
        @NotNull
        String description,

        @NotNull
        TodoItemType todoItemType
) {
}
