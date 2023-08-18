package iss.ad.project.spotify.repo;

import iss.ad.project.spotify.model.Feedback;
import iss.ad.project.spotify.model.LogEntry;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepo extends JpaRepository<LogEntry, Long> {
    @Query("SELECT DISTINCT le.name FROM LogEntry le")
    List<String> findDistinctNames();

    List<LogEntry> findByNameAndModelIdAndTaskId(String name, int modelId, int taskId);

    List<LogEntry> findByModelIdAndTaskId(int modelId, int taskId);

    @Query("SELECT DISTINCT le.taskId FROM LogEntry le")
    List<Integer> findDistinctTaskId();

    @Query("SELECT DISTINCT le.modelId FROM LogEntry le")
    List<Integer> findDistinctModelId();

    @Query("SELECT DISTINCT le.modelId FROM LogEntry le WHERE le.name = :userName")
    List<Integer> findDistinctModelIdByUserName(@Param("userName") String userName);
    
    @Query("SELECT DISTINCT le.taskId FROM LogEntry le WHERE le.name = :userName AND le.modelId = :modelId")
    Integer findDistanceTaskIdByNameAndModel(@Param("userName") String userName,
    	                                     @Param("modelId") Integer modelId);

    @Query("SELECT l.successValue FROM LogEntry l WHERE l.name = :name AND l.modelId = :modelId AND l.taskId = :taskId ORDER BY l.orderValue DESC")
    List<Integer> findSuccessValueByCriteriaOrderedByOrderValueDesc(@Param("name") 
        String name, @Param("modelId") int modelId, @Param("taskId") int taskId);

}
