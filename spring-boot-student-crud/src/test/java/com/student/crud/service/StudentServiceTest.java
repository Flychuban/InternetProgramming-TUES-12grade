package com.student.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.student.crud.model.Student;
import com.student.crud.repository.StudentRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Ivan", "Petrov", 18, "12A");
        student.setId(1L);
    }

    @Test
    void testGetAllStudents() {
        Student student2 = new Student("Maria", "Ivanova", 17, "11B");
        student2.setId(2L);
        when(repository.findAll()).thenReturn(Arrays.asList(student, student2));

        List<Student> result = service.getAllStudents();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetStudentByIdFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> result = service.getStudentById(1L);

        assertTrue(result.isPresent());
        assertEquals("Ivan", result.get().getFirstName());
        verify(repository).findById(1L);
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Student> result = service.getStudentById(99L);

        assertFalse(result.isPresent());
        verify(repository).findById(99L);
    }

    @Test
    void testCreateStudent() {
        when(repository.save(any(Student.class))).thenReturn(student);

        Student result = service.createStudent(student);

        assertNotNull(result);
        assertEquals("Ivan", result.getFirstName());
        verify(repository).save(student);
    }

    @Test
    void testUpdateStudentFound() {
        Student updated = new Student("Georgi", "Georgiev", 19, "12B");
        when(repository.findById(1L)).thenReturn(Optional.of(student));
        when(repository.save(any(Student.class))).thenReturn(student);

        Optional<Student> result = service.updateStudent(1L, updated);

        assertTrue(result.isPresent());
        verify(repository).findById(1L);
        verify(repository).save(any(Student.class));
    }

    @Test
    void testUpdateStudentNotFound() {
        Student updated = new Student("Georgi", "Georgiev", 19, "12B");
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Student> result = service.updateStudent(99L, updated);

        assertFalse(result.isPresent());
        verify(repository).findById(99L);
        verify(repository, never()).save(any(Student.class));
    }

    @Test
    void testDeleteStudentFound() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean result = service.deleteStudent(1L);

        assertTrue(result);
        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteStudentNotFound() {
        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.deleteStudent(99L);

        assertFalse(result);
        verify(repository).existsById(99L);
        verify(repository, never()).deleteById(any());
    }
}
