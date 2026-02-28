package com.student.crud.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void testNoArgsConstructor() {
        Student student = new Student();
        assertNull(student.getId());
        assertNull(student.getFirstName());
        assertNull(student.getLastName());
        assertEquals(0, student.getAge());
        assertNull(student.getGrade());
    }

    @Test
    void testParameterizedConstructor() {
        Student student = new Student("Ivan", "Petrov", 18, "12A");
        assertNull(student.getId());
        assertEquals("Ivan", student.getFirstName());
        assertEquals("Petrov", student.getLastName());
        assertEquals(18, student.getAge());
        assertEquals("12A", student.getGrade());
    }

    @Test
    void testSettersAndGetters() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Maria");
        student.setLastName("Ivanova");
        student.setAge(17);
        student.setGrade("11B");

        assertEquals(1L, student.getId());
        assertEquals("Maria", student.getFirstName());
        assertEquals("Ivanova", student.getLastName());
        assertEquals(17, student.getAge());
        assertEquals("11B", student.getGrade());
    }

    @Test
    void testEqualsSameObject() {
        Student student = new Student("Ivan", "Petrov", 18, "12A");
        assertEquals(student, student);
    }

    @Test
    void testEqualsEqualObjects() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        student1.setId(1L);
        Student student2 = new Student("Ivan", "Petrov", 18, "12A");
        student2.setId(1L);
        assertEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentId() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        student1.setId(1L);
        Student student2 = new Student("Ivan", "Petrov", 18, "12A");
        student2.setId(2L);
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        Student student2 = new Student("Georgi", "Petrov", 18, "12A");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentLastName() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        Student student2 = new Student("Ivan", "Georgiev", 18, "12A");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentAge() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        Student student2 = new Student("Ivan", "Petrov", 19, "12A");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsDifferentGrade() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        Student student2 = new Student("Ivan", "Petrov", 18, "12B");
        assertNotEquals(student1, student2);
    }

    @Test
    void testEqualsNull() {
        Student student = new Student("Ivan", "Petrov", 18, "12A");
        assertFalse(student.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        Student student = new Student("Ivan", "Petrov", 18, "12A");
        assertFalse(student.equals("not a student"));
    }

    @Test
    void testHashCodeEqual() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        student1.setId(1L);
        Student student2 = new Student("Ivan", "Petrov", 18, "12A");
        student2.setId(1L);
        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testHashCodeDifferent() {
        Student student1 = new Student("Ivan", "Petrov", 18, "12A");
        student1.setId(1L);
        Student student2 = new Student("Maria", "Ivanova", 17, "11B");
        student2.setId(2L);
        assertNotEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testToString() {
        Student student = new Student("Ivan", "Petrov", 18, "12A");
        student.setId(1L);
        String result = student.toString();
        assertNotNull(result);
        assertTrue(result.contains("Ivan"));
        assertTrue(result.contains("Petrov"));
        assertTrue(result.contains("18"));
        assertTrue(result.contains("12A"));
        assertTrue(result.contains("1"));
    }
}
