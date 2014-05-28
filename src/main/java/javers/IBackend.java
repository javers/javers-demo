package javers;

import java.util.List;

public interface IBackend {

    public List<User> getUsers();
    public void storePerson(User person);
    public void deletePerson(User person);
}
