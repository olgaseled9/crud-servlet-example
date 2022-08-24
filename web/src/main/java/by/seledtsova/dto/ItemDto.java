package by.seledtsova.dto;

import java.util.List;

/**
 * Container for Item representing data, getter and setter methods.
 * @see ItemDto
 */
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private List<String> errors;

    public ItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if an object is new.
     * @return true, if object is new
     */
    public boolean isNew() {
        return this.getId() == null;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", errors=" + errors +
            '}';
    }
}
