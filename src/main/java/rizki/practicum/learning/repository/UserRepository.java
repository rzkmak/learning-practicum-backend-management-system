package rizki.practicum.learning.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rizki.practicum.learning.entity.User;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String>{
    User findByEmailAndPassword(String email, String password) throws Exception;
    User findByEmail(String email);
}
