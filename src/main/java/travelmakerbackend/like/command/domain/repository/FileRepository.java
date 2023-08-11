package travelmakerbackend.like.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.like.command.domain.aggregate.entity.FileEntity;

@Repository
public
interface FileRepository extends JpaRepository<FileEntity, Integer> {

}

