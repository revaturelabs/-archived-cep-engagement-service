package com.cepengagementservice.Repositories;

// import java.util.List;

import com.cepengagementservice.Models.Batch;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
    /*
     * WRONG WAY OF DOING A JOIN See HQL ERROR: Path expected for join
     * 
     * @Query(
     * value="SELECT b FROM Batch b INNER JOIN USER_BATCH ub ON b.batch_id= ub.BATCH_ID WHERE ub.USER_ID= :userId"
     * ) List<Batch> findByUserId(@Param("userId") Integer userId);
     */

    /* In JPA need a entity that holds the association */
    /*
     * In this case, is b.user ub, ub actually points to the model that is
     * associated with!
     */
    // TODO:
    // @Query(value = "SELECT b FROM Batch b INNER JOIN b.user ub WHERE ub.userId=
    // :userId")
    // List<Batch> findByUserId(@Param("userId") Integer userId);

    Batch findByBatchId(String batchId);
}