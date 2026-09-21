package OOP_BTL_develop.demo.dto.request;

public class CategoryCreationRequest {
    private String name;
    private String description;

    public CategoryCreationRequest() {}

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
}