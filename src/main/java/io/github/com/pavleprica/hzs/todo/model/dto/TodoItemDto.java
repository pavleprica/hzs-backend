package io.github.com.pavleprica.hzs.todo.model.dto;

import io.github.com.pavleprica.hzs.todo.model.TodoItemType;
import io.github.com.pavleprica.hzs.todo.model.entity.TodoItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TodoItemDto {

    @Min(1)
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String description;

    @NotNull
    private TodoItemType todoItemType;

}
