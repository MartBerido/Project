package com.example.pawsome;

import com.example.pawsome.entities.Payment;
import com.example.pawsome.entities.User;
import com.example.pawsome.entities.Dog;
import com.example.pawsome.repositories.DogRepository;
import com.example.pawsome.repositories.PaymentRepository;
import com.example.pawsome.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PawsomeApplicationTests {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DogRepository dogRepository;

    @Test
    public void testSavePayment() {
        Payment payment = new Payment();
        payment.setCreditCardNumber("1234567812345678");
        payment.setExpirationMonth("12");
        payment.setExpirationYear("2025");
        payment.setCvc("123");
        payment.setAmount(100.0);

        Payment savedPayment = paymentRepository.save(payment);
        assertNotNull(savedPayment.getId());
        assertEquals(payment.getCreditCardNumber(), savedPayment.getCreditCardNumber());
        assertEquals(payment.getExpirationMonth(), savedPayment.getExpirationMonth());
        assertEquals(payment.getExpirationYear(), savedPayment.getExpirationYear());
        assertEquals(payment.getCvc(), savedPayment.getCvc());
        assertEquals(payment.getAmount(), savedPayment.getAmount());
    }

    @Test
    public void testNewUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("1234567890");
        user.setStreet("Street Address");
        user.setCity("City");
        user.setPostalCode("12345");
        user.setAccount("Owner");
        user.setEnabled(true);

        user = userRepository.save(user);

        assertNotNull(user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("1234567890", user.getPhone());
        assertEquals("Street Address", user.getStreet());
        assertEquals("City", user.getCity());
        assertEquals("12345", user.getPostalCode());
        assertEquals("Owner", user.getAccount());
        assertTrue(user.isEnabled());
    }


    @Test
    public void testNewDog() {
        Dog dog = new Dog();
        dog.setDogName("Buddy");
        dog.setDogBreed("Labrador");
        dog.setDogAge(3);
        dog.setAvailable(true);
        dog.setPayed(false);

        dog = dogRepository.save(dog);

        assertNotNull(dog.getId());
        assertEquals("Buddy", dog.getDogName());
        assertEquals("Labrador", dog.getDogBreed());
        assertEquals(3, dog.getDogAge());
        assertTrue(dog.isAvailable());
        assertFalse(dog.isPayed());
    }

}
