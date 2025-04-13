package com.example.BinaryTreeFS;
import com.example.BinaryTreeFS.BinaryTreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BinaryTreeRepository extends JpaRepository<BinaryTreeRecord, Long> {
    List<BinaryTreeRecord> findAll();
}