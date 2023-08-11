package travelmakerbackend.comment.command.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.comment.command.domain.aggregate.entity.CommentEntity;

@Repository
public
interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}

