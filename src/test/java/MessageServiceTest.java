import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Severity;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.repository.MessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import com.tcs.edu.service.ProcessException;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.UUID;

import static com.tcs.edu.enumeration.Severity.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Nested
public class MessageServiceTest {

    private MessageRepository messageRepository;
    MessageService service;

    @BeforeEach
    public void create() {
        messageRepository = new HashMapMessageRepository();
        service = new OrderedDistinctedMessageService(messageRepository);
    }

    @Nested
    @DisplayName("Create object message")
    public class CreateMessageObject {
        @Test
        public void CreateMessageWithParameterNull() {
            Assertions.assertThrows(ProcessException.class, () -> new Message(MINOR, null));
        }

        @Test
        public void CreateMessageWithParameterExist() {
            Message message = new Message(MINOR, "message");

            Assertions.assertEquals(MINOR, message.getSeverityLevel());
            Assertions.assertEquals("message", message.getBody());
            Assertions.assertNull(message.getId());

        }
    }

    @Nested
    @DisplayName("Create Message in Repository")
    public class CreateMessageTest {

        @Test
        public void createMessageInRepository() {
            Message message = new Message(MINOR, "hello1");
            UUID createdId = service.create(message);

            assertThat(messageRepository.findByPrimaryKey(createdId), is(message));
            assertThat(messageRepository.findAll(), hasSize(1));
        }
    }


    @Nested
    @DisplayName("Find Message in Repository")
    public class FindMessageTest {

        @Test
        public void getAllMessageInRepository() {
            UUID messageId1 = messageRepository.create(new Message(MINOR, "message"));
            UUID messageId2 = messageRepository.create(new Message(MINOR, "Hello2"));
            UUID messageId3 = messageRepository.create(new Message(MAJOR, "Hello3"));

            Collection<Message> messages = service.get();

            assertThat(messages, hasSize(3));
            assertThat(messages, allOf(
                    hasItem(messageRepository.findByPrimaryKey(messageId1)),
                    hasItem(messageRepository.findByPrimaryKey(messageId2)),
                    hasItem(messageRepository.findByPrimaryKey(messageId3))
            ));
        }

        @Test
        public void getMessageOnlyId() {
            Message message = new Message(MINOR, "message");
            UUID messageId = messageRepository.create(message);

            Message mess = service.getById(messageId);

            assertThat(message, equalTo(mess));
        }

        @Test
        public void getMessageOnlySeverity() {

            UUID messageId1 = messageRepository.create(new Message(MINOR, "message"));
            UUID messageId2 = messageRepository.create(new Message(REGULAR, "Hello2"));
            UUID messageId3 = messageRepository.create(new Message(MAJOR, "Hello3"));

            Collection<Message> messages = service.get(MINOR);

            assertThat(messages, hasSize(1));
            assertThat(messages, allOf(
                    hasItem(messageRepository.findByPrimaryKey(messageId1)),
                    not(hasItem(messageRepository.findByPrimaryKey(messageId2))),
                    not(hasItem(messageRepository.findByPrimaryKey(messageId3)))
            ));
        }

        @Test
        public void getMessageOnlyBody() {

            UUID messageId1 = messageRepository.create(new Message(MINOR, "message"));
            UUID messageId2 = messageRepository.create(new Message(MINOR, "Hello2"));
            UUID messageId3 = messageRepository.create(new Message(MAJOR, "Hello2"));

            Collection<Message> messages = service.get("Hello2");

            assertThat(messages, hasSize(2));
            assertThat(messages, allOf(
                    hasItem(messageRepository.findByPrimaryKey(messageId2)),
                    hasItem(messageRepository.findByPrimaryKey(messageId3)),
                    not(hasItem(messageRepository.findByPrimaryKey(messageId1)))
            ));
        }

        @Test
        public void getMessageBodyAndSeverity() {

            UUID messageId1 = messageRepository.create(new Message(MINOR, "message"));
            UUID messageId2 = messageRepository.create(new Message(MINOR, "Hello2"));
            UUID messageId3 = messageRepository.create(new Message(MAJOR, "message"));

            Collection<Message> messages = service.get(MINOR, "message");

            assertThat(messages, hasSize(1));
            assertThat(messages, allOf(
                    hasItem(messageRepository.findByPrimaryKey(messageId1)),
                    not(hasItem(messageRepository.findByPrimaryKey(messageId2))),
                    not(hasItem(messageRepository.findByPrimaryKey(messageId3)))
            ));
        }

        @Test
        public void getMessageByNullSeverity() {
            messageRepository.create(new Message(MINOR, "Hello"));
            messageRepository.create(new Message(REGULAR, "Hello2"));

            Collection<Message> messages = service.get((Severity) null);

            assertThat(messages, hasSize(0));

        }

        @Test
        public void getMessageByNullBody() {
            messageRepository.create(new Message(MINOR, "Hello"));
            messageRepository.create(new Message(REGULAR, "Hello2"));

            Collection<Message> messages = service.get((String) null);

            assertThat(messages, hasSize(0));

        }

        @Test
        public void getTrowsWhenSeverityIsNull() {
            assertThrows(ProcessException.class, () -> service.get(null, "Body"));
        }

        @Test
        public void getTrowsWhenBodyIsNull() {
            assertThrows(ProcessException.class, () -> service.get(MAJOR, null));
        }
    }

    @Nested
    @DisplayName("Update message in Repository")
    public class updateMessageTest {
        @Test
        public void updateMessageWhenIdExist() {
            Message message = new Message(MINOR, "hello1");
            UUID createdId = messageRepository.create(message);

            Message messageForUpdate = new Message(REGULAR, "UpdateMessage");
            messageForUpdate.setId(createdId);

            Message updatedMessage = service.put(messageForUpdate);

            assertThat(updatedMessage, equalTo(message));
            assertThat(messageRepository.findByPrimaryKey(createdId), equalTo(messageForUpdate));

        }

        @Test
        public void updateMessageWhenIdIsNull() {
            Message message = new Message(MINOR, "hello1");
            UUID messageId = messageRepository.create(message);

            Message messageForUpdate = new Message(REGULAR, "UpdateMessage");

            Message updatedMessage = service.put(messageForUpdate);

            assertThat(updatedMessage, nullValue());
            assertThat(messageRepository.findByPrimaryKey(messageId), is(message));
            assertThat(messageRepository.findAll(), hasSize(1));
        }

        @Test
        public void updateMessageWhenIdDoesntExist() {
            Message message = new Message(REGULAR, "message");
            Message messageAfterUpdate = new Message(MAJOR, "Now i am updated!!!");

            UUID messageId = messageRepository.create(message);

            UUID randomId = UUID.randomUUID();
            messageAfterUpdate.setId(randomId);

            Message updatedMessage = service.put(messageAfterUpdate);

            assertThat(messageRepository.findByPrimaryKey(messageId), is(message));
            assertThat(messageRepository.findAll(), hasSize(1));
            assertThat(updatedMessage, nullValue());
            assertThat(messageId, not(is(randomId)));
        }

    }

    @Nested
    @DisplayName("Delete Message In Repository")
    public class deleteMessageTest {
        @Test
        public void deleteMessageWhenIdExist() {
            Message message = new Message(REGULAR, "message");
            UUID createdId = messageRepository.create(message);

            Message deleteMessage = service.delete(createdId);

            assertThat(deleteMessage, equalTo(message));
            assertThat(messageRepository.findAll(), hasSize(0));


        }

        @Test
        public void deleteMessageWhenIdDoesntExist() {
            Message message = new Message(REGULAR, "message");
            messageRepository.create(message);

            UUID randomId = UUID.randomUUID();
            Message deleteMessage = service.delete(randomId);

            assertThat(deleteMessage, nullValue());
            assertThat(messageRepository.findAll(), hasSize(1));
        }

        @Test
        public void deleteMessageWhenIdIsNull() {
            Message message = new Message(REGULAR, "message");
            messageRepository.create(message);

            Message deleteMessage = service.delete(null);

            assertThat(deleteMessage, nullValue());
            assertThat(messageRepository.findAll(), hasSize(1));
        }
    }

}
