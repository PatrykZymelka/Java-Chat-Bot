import org.GUI.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UserTest {
    User U = new User();

    @Test
    void SetUserTest(){
        String Sender = "User1";
        U.SetSender(Sender);
        assertEquals(Sender, U.GetSender());
    }

    @Test
    void SetPortTest(){
        int Port = 1234;
        U.SetPort(Port);
        assertEquals(Port, U.GetPort());
    }


}