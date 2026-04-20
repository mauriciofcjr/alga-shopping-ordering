package com.mchaves.shop.ordering.domain.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    
    @Test
    public void testingCustomer(){
        Customer customer = createRandomCustomer();

        Assertions.assertNotNull(customer.getId());
        Assertions.assertNotNull(customer.getFullName());
        Assertions.assertNotNull(customer.getBirthDate());
        Assertions.assertNotNull(customer.getEmail());
        Assertions.assertNotNull(customer.getPhone());
        Assertions.assertNotNull(customer.getDocument());
        Assertions.assertNotNull(customer.getPromotionNotificationsAllowed());
        Assertions.assertNotNull(customer.getArchived());
        Assertions.assertNotNull(customer.getRegisteredAt());
        Assertions.assertNotNull(customer.getLoyaltyPoints());
    }

    private Customer createRandomCustomer() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFullName("Customer " + random.nextInt(1_000, 9_999));
        customer.setBirthDate(LocalDate.now().minusDays(random.nextLong(6_000, 20_000)));
        customer.setEmail("customer" + random.nextInt(1_000, 9_999) + "@mail.com");
        customer.setPhone("+55" + random.nextLong(10_000_000_000L, 99_999_999_999L));
        customer.setDocument(String.valueOf(random.nextLong(100_000_00000L, 999_999_99999L)));
        customer.setPromotionNotificationsAllowed(random.nextBoolean());
        customer.setArchived(random.nextBoolean());
        customer.setRegisteredAt(OffsetDateTime.now().minusDays(random.nextLong(1, 365)));
        customer.setArchivedAt(customer.getArchived() ? OffsetDateTime.now() : null);
        customer.setLoyaltyPoints(random.nextInt(0, 20_000));
        return customer;
    }
}
