package requestViews;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Size;
import java.util.UUID;

public class UserRequestView {

    private Long id;

    @SafeHtml(message = "Username contains unsafe html")
    @Size(min=2, max=25, message="Username should be between 2 - 25 characters")
    private String username;

    @Email(message="Email should be valid")
    @NotEmpty(message="Email should not be empty")
    private String email;

    @Size(min=5, max=50, message="Password should be between 5 - 50 characters")
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
