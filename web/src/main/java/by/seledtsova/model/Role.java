package by.seledtsova.model;

/**
 * Container for Role entity data, getter and setter methods.
 * @see Role
 */
public class Role {

    private Long id;
    private RoleValue name;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleValue getName() {
        return name;
    }

    public void setName(RoleValue name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
            "id=" + id +
            ", name=" + name +
            '}';
    }


}
