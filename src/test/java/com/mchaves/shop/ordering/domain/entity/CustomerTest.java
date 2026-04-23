package com.mchaves.shop.ordering.domain.entity;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.mchaves.shop.ordering.domain.utility.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingCustomerWithInvalidEmail() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        UUID id = IdGenerator.generateTimeBaseUUID();
        String fullName = "Customer " + random.nextInt(1_000, 9_999);
        LocalDate birthDate = LocalDate.now().minusDays(random.nextLong(6_000, 20_000));
        String email = "invalid-email";
        String phone = "+55" + random.nextLong(10_000_000_000L, 99_999_999_999L);
        String document = String.valueOf(random.nextLong(10_000_000_000L, 99_999_999_999L));
        Boolean promotionNotificationsAllowed = random.nextBoolean();
        Boolean archived = random.nextBoolean();
        OffsetDateTime registeredAt = OffsetDateTime.now().minusDays(random.nextLong(1, 365));
        OffsetDateTime archivedAt = archived ? OffsetDateTime.now().minusDays(random.nextLong(0, 30)) : null;
        Integer loyaltyPoints = random.nextInt(0, 20_000);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Customer(
                id,
                fullName,
                birthDate,
                email,
                phone,
                document,
                promotionNotificationsAllowed,
                archived,
                registeredAt,
                archivedAt,
                loyaltyPoints));
    }

    @Test
    public void testingCustomer() {
        Customer customer = createRandomCustomer();

        Assertions.assertNotNull(readField(customer, "id", UUID.class));
        Assertions.assertNotNull(readField(customer, "fullName", String.class));
        Assertions.assertNotNull(readField(customer, "birthDate", LocalDate.class));
        Assertions.assertNotNull(readField(customer, "email", String.class));
        Assertions.assertNotNull(readField(customer, "phone", String.class));
        Assertions.assertNotNull(readField(customer, "document", String.class));
        Assertions.assertNotNull(readField(customer, "promotionNotificationsAllowed", Boolean.class));
        Assertions.assertNotNull(readField(customer, "archived", Boolean.class));
        Assertions.assertNotNull(readField(customer, "registeredAt", OffsetDateTime.class));
        Assertions.assertNotNull(readField(customer, "loyaltyPoints", Integer.class));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenChangingEmailWithInvalidValue() {
        Customer customer = createRandomCustomer();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> customer.changeEmail("invalid-email"));
    }

    private Customer createRandomCustomer() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        UUID id = IdGenerator.generateTimeBaseUUID();
        String fullName = "Customer " + random.nextInt(1_000, 9_999);
        LocalDate birthDate = LocalDate.now().minusDays(random.nextLong(6_000, 20_000));
        String email = "customer" + random.nextInt(1_000, 9_999) + "@mail.com";
        String phone = "+55" + random.nextLong(10_000_000_000L, 99_999_999_999L);
        String document = String.valueOf(random.nextLong(10_000_000_000L, 99_999_999_999L));
        Boolean promotionNotificationsAllowed = random.nextBoolean();
        Boolean archived = random.nextBoolean();
        OffsetDateTime registeredAt = OffsetDateTime.now().minusDays(random.nextLong(1, 365));
        OffsetDateTime archivedAt = archived ? OffsetDateTime.now().minusDays(random.nextLong(0, 30)) : null;
        Integer loyaltyPoints = random.nextInt(0, 20_000);

        return new Customer(
                id,
                fullName,
                birthDate,
                email,
                phone,
                document,
                promotionNotificationsAllowed,
                archived,
                registeredAt,
                archivedAt,
                loyaltyPoints);
    }

    private <T> T readField(Customer customer, String fieldName, Class<T> fieldType) {
        try {
            Field field = Customer.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(customer);
            return fieldType.cast(value);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Could not read field: " + fieldName, e);
        }
    }
}
