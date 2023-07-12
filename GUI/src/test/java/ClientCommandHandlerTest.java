import org.GUI.ClientCommandHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientCommandHandlerTest {
    ClientCommandHandler CH = new ClientCommandHandler();

    @Test
    void SingleMessageTest(){

        CH.StoreM("User1", new String[]{"NewMSG"});
        assertArrayEquals(new String[]{"NewMSG"},CH.GetStoredM("User1"));
    }
    @Test
    void AddingUser(){

        CH.refresh(new String[]{"User1"});
        assertArrayEquals(new String[]{"User1"}, CH.getConnectedUsers());
    }
    @Test
    void AddingMultipleUsers(){

        CH.refresh(new String[]{"User1,NotUser2"});
        assertArrayEquals(new String[]{"User1,User2"}, CH.getConnectedUsers());
    }
}