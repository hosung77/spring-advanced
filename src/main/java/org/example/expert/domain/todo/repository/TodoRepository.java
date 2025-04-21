package org.example.expert.domain.todo.repository;

import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 원래 그냥 조인이 아닌 fetch를 사용하여 n+1 문제를 해결)
    @EntityGraph(attributePaths = {"user"})
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Optional<Todo> findById(Long todoId);

    int countById(Long todoId);

    default Todo findByIdOrElseThrow(Long todoId){
        return findById(todoId)
                .orElseThrow(() -> new InvalidRequestException("Todo not found"));
    }

}
