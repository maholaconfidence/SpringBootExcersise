package org.example.springbootexcersise.service;

import jakarta.transaction.Transactional;
import org.example.springbootexcersise.DTO.StudentRequest;
import org.example.springbootexcersise.DTO.StudentResponse;
import org.example.springbootexcersise.Entity.Students;
import org.example.springbootexcersise.repositry.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentService {
  private  final StudentRepository studentRepository;

  public StudentService (StudentRepository studentRepository)
  {
      this.studentRepository = studentRepository;
  }
  //CREATE
    @Transactional
    public StudentResponse createStudent (StudentRequest request)
    {
        if (studentRepository.existsByEmail(request.email())){
            throw new DuplicateResourseException("A student with this email already exists");
        }

        Students student = new Students();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());

        Students savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }
//READ ALL
    @Transactional
    public  List<StudentResponse> getAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    //READ ONE
    @Transactional
    public StudentResponse getStudentById(long id){

        Students student = findStudentsOrThrow(id);

        return mapToResponse(student);

    }
    //UPDATE
    @Transactional
    public  StudentResponse updateStudent(long id, StudentRequest request){
      Students student = findStudentsOrThrow(id);

      boolean emailUsedByAnotherStudent = studentRepository.existsByEmailAndIdNot(request.email(), id);


        if (emailUsedByAnotherStudent) {
            throw new DuplicateResourceException(
                    "Another student is already using this email"
            );
        }
student.setFirstName((request.firstName()));

student.setLastName((request.lastName()));
student.setEmail(request.email());

Students updatedStudent = studentRepository.save(student);
return  mapToResponse(updatedStudent);

}
//DELETE
@Transactional
public  void deleteStudent (long id){
      Students student = findStudentsOrThrow(id);
      studentRepository.delete(student);
  }
  private  Students findStudentsOrThrow (long id){
      return studentRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException(
                      "Student not found with ID: " + id
              ));

  }
    private StudentResponse mapToResponse(Students student) {

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}

